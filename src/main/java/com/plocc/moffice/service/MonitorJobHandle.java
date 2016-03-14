package com.plocc.moffice.service;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.Monitor;

/**
 * Created by yanghuan on 2016/2/23.
 */
public interface MonitorJobHandle {
    void handle(Monitor monitor, CredentialEntity credential, Monitor.Task task) throws Exception;
}
