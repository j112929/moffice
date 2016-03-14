package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

import javax.persistence.Entity;

/**
 * FulfillmentMethodInfoEntity
 *
 * @author jzl
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午9:48:41
 * @email 1129290218@qq.com
 * @description 实现方法信息
 */
public class FulfillmentMethodInfoEntity extends Pojo {
    //实现方法编码
    private String code;

    public String getCode() {
        return getString("code");
    }

    public void setCode(String code) {
        set("code", code);
    }
}
