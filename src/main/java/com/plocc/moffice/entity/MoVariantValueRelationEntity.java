package com.plocc.moffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 属性类型主子帐号关联实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "TB_MO_VARIANT_VALUE_RELATION")
public class MoVariantValueRelationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "VARIANT_MASTER_ID")
    private String variantMasterId;
    
    @Column(name = "VARIANT_VALUE_MASTER_ID")
    private String variantValueMasterId;
    
    @Column(name = "VARIANT_VALUE_MASTER_CODE")
    private String variantValueMasterCode;
    
    @Column(name = "VARIANT_VALUE_MASTER_VALUE")
    private String variantValueMasterValue;
    
    @Column(name = "VARIANT_CHILDREN_ID")
    private String variantChildrenId;
    
    @Column(name = "VARIANT_VALUE_CHILDREN_ID")
    private String variantValueChildrenId;
    
    @Column(name = "VARIANT_VALUE_CHILDREN_CODE")
    private String variantValueChildrenCode;
    
    @Column(name = "VARIANT_VALUE_CHILDREN_VALUE")
    private String variantValueChildrenValue;
    
    @Column(name = "APP_ID")
    private String appId;
    
    @Column(name = "MEMO")
    private String memo;

    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the variantMasterId
     */
    public String getVariantMasterId() {
        return variantMasterId;
    }

    /**
     * @param variantMasterId the variantMasterId to set
     */
    public void setVariantMasterId(String variantMasterId) {
        this.variantMasterId = variantMasterId;
    }

    /**
     * @return the variantValueMasterId
     */
    public String getVariantValueMasterId() {
        return variantValueMasterId;
    }

    /**
     * @param variantValueMasterId the variantValueMasterId to set
     */
    public void setVariantValueMasterId(String variantValueMasterId) {
        this.variantValueMasterId = variantValueMasterId;
    }

    /**
     * @return the variantValueMasterCode
     */
    public String getVariantValueMasterCode() {
        return variantValueMasterCode;
    }

    /**
     * @param variantValueMasterCode the variantValueMasterCode to set
     */
    public void setVariantValueMasterCode(String variantValueMasterCode) {
        this.variantValueMasterCode = variantValueMasterCode;
    }

    /**
     * @return the variantValueMasterValue
     */
    public String getVariantValueMasterValue() {
        return variantValueMasterValue;
    }

    /**
     * @param variantValueMasterValue the variantValueMasterValue to set
     */
    public void setVariantValueMasterValue(String variantValueMasterValue) {
        this.variantValueMasterValue = variantValueMasterValue;
    }

    /**
     * @return the variantChildrenId
     */
    public String getVariantChildrenId() {
        return variantChildrenId;
    }

    /**
     * @param variantChildrenId the variantChildrenId to set
     */
    public void setVariantChildrenId(String variantChildrenId) {
        this.variantChildrenId = variantChildrenId;
    }

    /**
     * @return the variantValueChildrenId
     */
    public String getVariantValueChildrenId() {
        return variantValueChildrenId;
    }

    /**
     * @param variantValueChildrenId the variantValueChildrenId to set
     */
    public void setVariantValueChildrenId(String variantValueChildrenId) {
        this.variantValueChildrenId = variantValueChildrenId;
    }

    /**
     * @return the variantValueChildrenCode
     */
    public String getVariantValueChildrenCode() {
        return variantValueChildrenCode;
    }

    /**
     * @param variantValueChildrenCode the variantValueChildrenCode to set
     */
    public void setVariantValueChildrenCode(String variantValueChildrenCode) {
        this.variantValueChildrenCode = variantValueChildrenCode;
    }


    /**
     * @return the variantValueChildrenValue
     */
    public String getVariantValueChildrenValue() {
        return variantValueChildrenValue;
    }

    /**
     * @param variantValueChildrenValue the variantValueChildrenValue to set
     */
    public void setVariantValueChildrenValue(String variantValueChildrenValue) {
        this.variantValueChildrenValue = variantValueChildrenValue;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

}
