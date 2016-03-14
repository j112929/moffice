package com.plocc.moffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品SKU主子帐号关联实体
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "TB_MO_PRODUCT_SKU_RELATION")
public class MoProductSKURelationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "PRODUCT_MASTER_ID")
    private String productMasterId;
    
    @Column(name = "PRODUCT_MASTER_SKU_ID")
    private String productMasterSKUId;
    
    @Column(name = "PRODUCT_CHILDREN_ID")
    private String productChildrenId;
    
    @Column(name = "PRODUCT_CHILDREN_SKU_ID")
    private String productChildrenSKUId;
    
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
     * @return the productMasterId
     */
    public String getProductMasterId() {
        return productMasterId;
    }

    /**
     * @param productMasterId the productMasterId to set
     */
    public void setProductMasterId(String productMasterId) {
        this.productMasterId = productMasterId;
    }

    /**
     * @return the productMasterSKUId
     */
    public String getProductMasterSKUId() {
        return productMasterSKUId;
    }

    /**
     * @param productMasterSKUId the productMasterSKUId to set
     */
    public void setProductMasterSKUId(String productMasterSKUId) {
        this.productMasterSKUId = productMasterSKUId;
    }

    /**
     * @return the productChildrenId
     */
    public String getProductChildrenId() {
        return productChildrenId;
    }

    /**
     * @param productChildrenId the productChildrenId to set
     */
    public void setProductChildrenId(String productChildrenId) {
        this.productChildrenId = productChildrenId;
    }

    /**
     * @return the productChildrenSKUId
     */
    public String getProductChildrenSKUId() {
        return productChildrenSKUId;
    }

    /**
     * @param productChildrenSKUId the productChildrenSKUId to set
     */
    public void setProductChildrenSKUId(String productChildrenSKUId) {
        this.productChildrenSKUId = productChildrenSKUId;
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
