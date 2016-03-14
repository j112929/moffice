package com.plocc.moffice.support;

/**
 * Created by yang on 2016/3/1.
 */
public enum TaskTypeEnum {
    
    MODIFY("modify", "更新"),
    DROP("drop", "删除"),
    ADD("add", "新增");

    private String desc;

    private String value;

    TaskTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
