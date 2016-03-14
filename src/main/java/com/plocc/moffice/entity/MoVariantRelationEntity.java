package com.plocc.moffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 属性类型值主子帐号关联实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "TB_MO_VARIANT_RELATION")
public class MoVariantRelationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "VARIANT_MASTER_ID")
    private String variantMasterId;
    
    @Column(name = "VARIANT_MASTER_NAME")
    private String variantMasterName;
    
    @Column(name = "VARIANT_CHILDREN_ID")
    private String variantChildrenId;
    
    @Column(name = "VARIANT_CHILDREN_NAME")
    private String variantChildrenName;
    
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

    /**
     * @return the variantMasterName
     */
    public String getVariantMasterName() {
        return variantMasterName;
    }

    /**
     * @param variantMasterName the variantMasterName to set
     */
    public void setVariantMasterName(String variantMasterName) {
        this.variantMasterName = variantMasterName;
    }

    /**
     * @return the variantChildrenName
     */
    public String getVariantChildrenName() {
        return variantChildrenName;
    }

    /**
     * @param variantChildrenName the variantChildrenName to set
     */
    public void setVariantChildrenName(String variantChildrenName) {
        this.variantChildrenName = variantChildrenName;
    }

}
