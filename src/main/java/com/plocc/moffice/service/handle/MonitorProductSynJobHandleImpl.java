package com.plocc.moffice.service.handle;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.Monitor;
import com.plocc.moffice.service.MonitorJobHandle;
import com.plocc.moffice.service.MonitorRefreshService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品同步 具体job 逻辑
 */
public class MonitorProductSynJobHandleImpl implements MonitorJobHandle {

    @Autowired
    private MonitorRefreshService monitorRefreshService;

    public void handle(Monitor monitor, CredentialEntity credential, Monitor.Task task) throws Exception {
        //生成删除任务
         monitorRefreshService.createProductDeleteTask(credential);
    }

}
