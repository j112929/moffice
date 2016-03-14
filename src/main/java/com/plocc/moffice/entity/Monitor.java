package com.plocc.moffice.entity;

import com.plocc.framework.support.StringHelper;

import java.util.List;

/**
 * Created by yanghuan on 2016/2/19.
 */
public class Monitor {

    /**
     * 任务类型
     */
    private String type;
    /**
     * 检测周期
     */
    private String interval;
    /**
     * 获取总数 api
     */
    private String countUrl;
    /**
     * 获取内容 api
     */
    private String rsUrl;

    private List<Task> tasks;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getCountUrl() {
        return countUrl;
    }

    public void setCountUrl(String countUrl) {
        this.countUrl = countUrl;
    }

    public String getRsUrl() {
        return rsUrl;
    }

    public void setRsUrl(String rsUrl) {
        this.rsUrl = rsUrl;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class Task {
        /**
         * 任务类型
         */
        private String type;
        /**
         * 任务执行周期
         */
        private String interval;
        /**
         * 任务依赖URL
         */
        private String url;

        /**
         * 执行结果
         */
        private String value;

        private String newValue;

        private String extraParam;

        private String newExtraParam;

        private String triggerJob;

        private String desc;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getNewValue() {
            return newValue;
        }

        public void setNewValue(String newValue) {
            this.newValue = newValue;
        }

        public String getExtraParam() {
            return extraParam;
        }

        public void setExtraParam(String extraParam) {
            this.extraParam = extraParam;
        }

        public String getNewExtraParam() {
            return newExtraParam;
        }

        public void setNewExtraParam(String newExtraParam) {
            this.newExtraParam = newExtraParam;
        }

        public String getTriggerJob() {
            return triggerJob;
        }

        public void setTriggerJob(String triggerJob) {
            this.triggerJob = triggerJob;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean isChange() {
            return (null != newValue && !StringHelper.equalsIgnoreCase(value, newValue)) || (null != newExtraParam && !StringHelper.equalsIgnoreCase(extraParam, newExtraParam));
        }
    }
}

