package com.plocc.moffice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yanghuan on 2016/2/23.
 */
@Entity
@Table(name = "tb_mo_sku")
public class MoSKUEntity {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    @Column(name = "SKU_ID")
    private Long skuId;
    @Column(name = "SKU_MD5_STR")
    private String skuMd5Str;
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "VERSION")
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuMd5Str() {
        return skuMd5Str;
    }

    public void setSkuMd5Str(String skuMd5Str) {
        this.skuMd5Str = skuMd5Str;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
