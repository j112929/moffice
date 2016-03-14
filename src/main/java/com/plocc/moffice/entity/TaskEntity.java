package com.plocc.moffice.entity;

import com.plocc.moffice.support.TaskTypeEnum;

/**
 * Created by yanghuan on 2016/3/1.
 */
public class TaskEntity<T> {
    private String appId;
    private TaskTypeEnum type;
    private Object result;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public TaskTypeEnum getType() {
        return type;
    }

    public void setType(TaskTypeEnum type) {
        this.type = type;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
