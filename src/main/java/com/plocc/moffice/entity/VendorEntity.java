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
public class VendorEntity extends Pojo {
    
    // 供应商编码
    private String vendorCode;
    // 供应商名称
    private String vendorName;
    // 状态  ACTIVE INACTIVE
    private String status;
    // 纳税组别  LIABLE EXEMPT
    private String taxType;
    
    private Object customerFields;
    
    
    public VendorEntity() {
    }

    public VendorEntity(Map entity) {
        super(entity);
    }
    
    /**
     * @return the vendorCode
     */
    public String getVendorCode() {
        return getString("vendorCode");
    }
    /**
     * @param vendorCode the vendorCode to set
     */
    public void setVendorCode(String vendorCode) {
        set("vendorCode", vendorCode);
    }
    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return getString("vendorName");
    }
    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        set("vendorName", vendorName);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return getString("status");
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        set("status", status);
    }

    /**
     * @return the taxType
     */
    public String getTaxType() {
        return getString("taxType");
    }

    /**
     * @param taxType the taxType to set
     */
    public void setTaxType(String taxType) {
        set("taxType", taxType);
    }

    /**
     * @return the customFields
     */
    public Object getCustomFields() {
        return get("customFields");
    }

    /**
     * @param customFields the customFields to set
     */
    public void setCustomFields(Object customFields) {
        set("customerFields", customFields);
    }

}
