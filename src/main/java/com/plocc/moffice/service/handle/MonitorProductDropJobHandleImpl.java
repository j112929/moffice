package com.plocc.moffice.service.handle;

import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.Monitor;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.MonitorJobHandle;
import com.plocc.moffice.service.MonitorRefreshService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品删除 具体job 逻辑
 */
public class MonitorProductDropJobHandleImpl implements MonitorJobHandle {
    @Autowired
    private MonitorRefreshService monitorRefreshService;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;

    public void handle(Monitor monitor, CredentialEntity credential, Monitor.Task task) throws Exception {
        String value = task.getValue();
        String newValue = task.getNewValue();
        String extraParam = task.getExtraParam();
        String newExtraParam = task.getNewExtraParam();
        int count = DataHelper.getInt(value);
        int newCount = DataHelper.getInt(newValue);
        long maxId = DataHelper.getLong(extraParam);
        long newMaxId = DataHelper.getLong(newExtraParam);
        if (count > newCount || maxId > newMaxId || (newCount - count) < (newMaxId - maxId)) {
            //更新最新商品id
            monitorRefreshService.getIds(credential, monitor.getRsUrl(), monitor.getType());
            //生成删除任务
            monitorRefreshService.createProductDeleteTask(credential);
        }
    }

}
