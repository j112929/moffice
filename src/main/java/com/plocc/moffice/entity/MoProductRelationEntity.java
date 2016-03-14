package com.plocc.moffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品主子帐号关联实体
 * 
 * @author xing_biao
 * 2016年2月25日
 * @version 1.0.0
 *
 */
@Entity
@Table(name = "TB_MO_PRODUCT_RELATION")
public class MoProductRelationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "PRODUCT_MASTER_ID")
    private String productMasterId;
    
    @Column(name = "PRODUCT_CHILDREN_ID")
    private String productChildrenId;
    
    @Column(name = "APP_ID")
    private String appId;
    
    @Column(name = "MEMO")
    private String memo;

    
    public Integer getId() {
        return id;
    }

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
