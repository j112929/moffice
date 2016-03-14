/**
 * 宝龙电商
 * com.plocc.moffice.service.impl
 * MonitorServiceImpl.java
 * <p/>
 * 2016-02-20
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service.impl;

import com.plocc.framework.support.DateHelper;
import com.plocc.framework.support.ProfileHelper;
import com.plocc.framework.support.StringHelper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.Monitor;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.MonitorJobHandle;
import com.plocc.moffice.service.MonitorRefreshService;
import com.plocc.moffice.support.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * MonitorServiceImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 22:53
 * @email zhanggj@powerlong.com
 * @description 监控任务
 */
@Service
@Scope("singleton")
public class MonitorServiceImpl implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);
    @Autowired
    private CredentialService credentialService;
    @Resource(name = "monitorsJobs")
    private Map<String, Map<String, MonitorJobHandle>> monitorsJobs;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private MonitorRefreshService refreshService;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    private ApplicationContext applicationContext;

    /**
     * 初始化任务、入口
     */
    @PostConstruct
    private void runMonitorTask() {
        // 30 秒后 开始执行任务、防止Token未刷新完毕
        taskScheduler.schedule(new Runnable() {
            public void run() {

                List<CredentialEntity> credentials = credentialService.getCredentials();
                for (final CredentialEntity credential : credentials) {
                    //子账户才能继续操作
                    if (!credential.getMaster()) {
                        // 为每个子账户生成任务
                        for (final Monitor monitor : (List<Monitor>) applicationContext.getBean("monitors")) {
                            // 状态检测任务
                            taskScheduler.scheduleWithFixedDelay(new Runnable() {

                                public void run() {
                                    //获取最新认证信息
                                    CredentialEntity monitorCredential = credentialService.getCredentialByAppId(credential.getAppId());
                                    if (!ProfileHelper.isDev() && !mofficeRedisRepository.getServerLock(String.format(Constants.REDIS_LOCK_KEY, "monitor"), DateHelper.toMillis("30m"))) {
                                        // 加锁成功的服务器才能执行以下逻辑
                                        // 启用分布式锁、特殊任务防止任务重复执行、锁的有效期为30分钟。30分钟后 如果加锁服务器宕机不影响其他服务器加锁
                                        return;
                                    }

                                    for (Monitor.Task task : monitor.getTasks()) {
                                        String valueKey = String.format("%s:%s:%s", monitor.getType(), task.getType(), monitorCredential.getAppId());
                                        String extraParamKey = String.format("%s:%s:%s:%s", monitor.getType(), task.getType(), monitorCredential.getAppId(), "extra");
                                        //如果value为空则从redis取
                                        if (ValidHelper.isNull(task.getValue())) {
                                            task.setValue(mofficeRedisRepository.getMonitorValue(valueKey));
                                        }
                                        //如果task类型为count且value为空则从redis取
                                        if (StringHelper.equalsIgnoreCase(task.getType(), "count") && ValidHelper.isNull(task.getExtraParam())) {
                                            task.setExtraParam(mofficeRedisRepository.getMonitorValue(extraParamKey));
                                        }
                                        // 检测结果是否发生改变、触发任务
                                        if (task.isChange()) {
                                            try {
                                                logger.debug(String.format("状态检测任务被触发：%s %s %s %s %s ", monitorCredential.getTitle(), monitor.getType(), task.getType(), task.getTriggerJob(), task.getDesc()));
                                                monitorsJobs.get(monitor.getType()).get(task.getTriggerJob()).handle(monitor, monitorCredential, task);
                                                //将同步结果放到redis中，防止服务挂掉丢失结果
                                                mofficeRedisRepository.setMonitorValue(valueKey, task.getNewValue());
                                                // 同步结果、标识已经出发过该任务
                                                task.setValue(task.getNewValue());
                                                //如果类型为count，同步掉
                                                if (StringHelper.equalsIgnoreCase(task.getType(), "count")) {
                                                    mofficeRedisRepository.setMonitorValue(extraParamKey, task.getNewExtraParam());
                                                    task.setExtraParam(task.getNewExtraParam());
                                                }
                                            } catch (Exception e) {
                                                logger.error(String.format("状态检测任务触发失败：%s %s %s %s %s ", monitorCredential.getTitle(), monitor.getType(), task.getType(), task.getTriggerJob(), task.getDesc()), e);
                                            }
                                        }
                                    }
                                }
                            }, DateHelper.toMillis(monitor.getInterval()));

                            // 数据刷新任务
                            for (final Monitor.Task task : monitor.getTasks()) {
                                // 数据刷新任务 根据配置时间执行
                                taskScheduler.scheduleWithFixedDelay(new Runnable() {
                                    public void run() {
                                        //获取最新认证信息
                                        CredentialEntity taskCredential = credentialService.getCredentialByAppId(credential.getAppId());
                                        if (!ProfileHelper.isDev() && !mofficeRedisRepository.getServerLock(String.format(Constants.REDIS_LOCK_KEY, "monitor"), DateHelper.toMillis("30m"))) {
                                            // 加锁成功的服务器才能执行以下逻辑
                                            // 启用分布式锁、特殊任务防止任务重复执行、锁的有效期为30分钟。30分钟后 如果加锁服务器宕机不影响其他服务器加锁
                                            return;
                                        }
                                        try {
                                            if (StringHelper.equalsIgnoreCase(task.getType(), "count")) {
                                                logger.debug(String.format("数据刷新任务被触发：%s %s %s %s ", taskCredential.getTitle(), "获取总数", monitor.getType(), task.getType()));
                                                String count = refreshService.getCount(taskCredential, monitor.getCountUrl());
                                                String maxId = refreshService.getMaxId(taskCredential, monitor.getRsUrl());
                                                if (null != count && null != maxId) {
                                                    task.setNewValue(count);
                                                    task.setNewExtraParam(maxId);
                                                }
                                            }
                                            if (StringHelper.equalsIgnoreCase(task.getType(), "lastUpdate")) {
                                                logger.debug(String.format("数据刷新任务被触发：%s %s %s %s ", taskCredential.getTitle(), "最后时间", monitor.getType(), task.getType()));
                                                String lastUpdate = refreshService.getLastUpdate(taskCredential, monitor.getRsUrl());
                                                if (null != lastUpdate) {
                                                    task.setNewValue(lastUpdate);
                                                }
                                            }
                                            if (StringHelper.equalsIgnoreCase(task.getType(), "maxId")) {
                                                logger.debug(String.format("数据刷新任务被触发：%s %s %s %s ", taskCredential.getTitle(), "获取最大主键", monitor.getType(), task.getType()));
                                                String maxId = refreshService.getMaxId(taskCredential, monitor.getRsUrl());
                                                if (null != maxId) {
                                                    task.setNewValue(maxId);
                                                }
                                            }
                                            if (StringHelper.equalsIgnoreCase(task.getType(), "ids")) {
                                                logger.debug(String.format("数据刷新任务被触发：%s %s %s %s ", taskCredential.getTitle(), "获取全部记录编号", monitor.getType(), task.getType()));
                                                refreshService.getIds(taskCredential, monitor.getRsUrl(), monitor.getType());
                                                task.setNewValue(DateHelper.format(DateHelper.FORMAT_FULL, new Date()));
                                            }
                                        } catch (Exception e) {
                                            logger.error(String.format("数据刷新任务触发失败：%s %s %s ", taskCredential.getTitle(), monitor.getType(), task.getType()), e);
                                        }

                                    }
                                }, DateHelper.toMillis(task.getInterval()));
                            }
                        }
                    }
                }
            }
        }, DateHelper.add(Calendar.SECOND, 30));
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
