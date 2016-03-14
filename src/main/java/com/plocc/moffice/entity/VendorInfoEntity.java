/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * VendorInfoEntity.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import java.util.Map;

import com.plocc.framework.entity.Pojo;

/**
 * 产品供应商
 * 
 * @author xing_biao
 * 2016年2月23日
 * @version 1.0.0
 *
 */
@SuppressWarnings({ "unused", "serial", "rawtypes" })
public class VendorInfoEntity extends Pojo {
    
    // 供应商编码
    private String code;
    // 供应商名称
    private String name;
    
    
    public VendorInfoEntity() {
    }

    public VendorInfoEntity(Map entity) {
        super(entity);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return getString("code");
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        set("code", code);
    }

    /**
     * @return the name
     */
    public String getName() {
        return getString("name");
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        set("name", name);
    }

}
