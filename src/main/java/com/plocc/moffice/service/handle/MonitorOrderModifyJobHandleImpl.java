package com.plocc.moffice.service.handle;

import com.plocc.framework.support.StringHelper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.Monitor;
import com.plocc.moffice.entity.SalesOrderEntity;
import com.plocc.moffice.entity.TaskEntity;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.MonitorJobHandle;
import com.plocc.moffice.service.MonitorRefreshService;
import com.plocc.moffice.support.ChannelEnum;
import com.plocc.moffice.support.TaskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单增加 具体job 逻辑
 */
public class MonitorOrderModifyJobHandleImpl implements MonitorJobHandle {
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    @Autowired
    private MonitorRefreshService monitorRefreshService;

    public void handle(Monitor monitor, CredentialEntity credential, Monitor.Task task) throws Exception {
        String lastUpdate = task.getValue();
        String newLastUpdate = task.getNewValue();
        //拼装filter
        String filter = "(updateTime le '" + newLastUpdate + "')";
        if (!ValidHelper.isNull(lastUpdate)) {
            filter += "and (updateTime ge '" + lastUpdate + "')";
        }
        Map params = new HashMap();
        params.put("filter", filter);
        params.put("orderBy", "updateTime desc");
        List<SalesOrderEntity> list = monitorRefreshService.getList(credential, params, monitor.getRsUrl(), SalesOrderEntity.class);
        for (SalesOrderEntity salesOrderEntity : list) {
            //过滤新增数据
            if (!StringHelper.equalsIgnoreCase(salesOrderEntity.getUpdateTime(), salesOrderEntity.getCreationTime())) {
                //发布消息
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setAppId(credential.getAppId());
                taskEntity.setType(TaskTypeEnum.MODIFY);
                taskEntity.setResult(salesOrderEntity);
                mofficeRedisRepository.createTask(ChannelEnum.ORDER_CHANNEL, taskEntity);
            }
        }
    }
}
