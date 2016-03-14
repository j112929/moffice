package com.plocc.moffice.service.handle;

import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.JsonpHelper;
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
 * 商品增加 具体job 逻辑
 */
public class MonitorProductAddJobHandleImpl implements MonitorJobHandle {
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    @Autowired
    private MonitorRefreshService monitorRefreshService;

    public void handle(Monitor monitor, CredentialEntity credential, Monitor.Task task) throws Exception {
        String maxId = task.getValue();
        String newMaxId = task.getNewValue();
        Map params = new HashMap();
        //拼装filter
        params.put("filter", "id gt " + DataHelper.getLong(maxId) + " and id le " + DataHelper.getLong(newMaxId));
        params.put("orderBy", "id desc");
        //根据id查出新增数据
        List<ProductEntity> list = monitorRefreshService.getList(credential, params, monitor.getRsUrl(), ProductEntity.class);
        for (ProductEntity productEntity : list) {
            //发布消息
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setAppId(credential.getAppId());
            taskEntity.setType(TaskTypeEnum.ADD);
            taskEntity.setResult(productEntity);
            mofficeRedisRepository.createTask(ChannelEnum.PRODUCT_CHANNEL, taskEntity);
        }
    }
}
