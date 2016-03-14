package com.plocc.moffice.service.handle;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.StringHelper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.moffice.entity.*;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.MonitorJobHandle;
import com.plocc.moffice.service.MonitorRefreshService;
import com.plocc.moffice.support.ChannelEnum;
import com.plocc.moffice.support.Constants;
import com.plocc.moffice.support.TaskTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品更新 具体job 逻辑
 */
public class MonitorProductModifyJobHandleImpl implements MonitorJobHandle {
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
        List<ProductEntity> list = monitorRefreshService.getList(credential, params, monitor.getRsUrl(), ProductEntity.class);
        for (ProductEntity productEntity : list) {
            //过滤新增数据
            if (!StringHelper.equalsIgnoreCase(productEntity.getUpdateTime(), productEntity.getCreationTime())) {
                //发布消息
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setAppId(credential.getAppId());
                taskEntity.setType(TaskTypeEnum.MODIFY);
                taskEntity.setResult(productEntity);
                mofficeRedisRepository.createTask(ChannelEnum.PRODUCT_CHANNEL, taskEntity);
            }
        }
    }
}
