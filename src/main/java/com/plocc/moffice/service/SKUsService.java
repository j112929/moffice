package com.plocc.moffice.service;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SKUAllWarehouseInfoEntity;
import com.plocc.moffice.entity.SKUInfoEntity;

/**
 * Created by yanghuan on 2016/2/29.
 */
public interface SKUsService {

    /**
     * 添加SKU
     * 
     * @param credential
     * @param skuInfoEntity
     */
    Integer add(String appId, SKUInfoEntity skuInfoEntity) throws Exception;
    
    /**
     * 根据skuID删除SKU
     * 
     * @param appId
     * @param skuId
     */
    void delete(String appId, String skuId) throws Exception;
    
    /**
     * 根据skuID 查询SKU
     * 
     * @param credential
     * @param skuId
     * @return
     */
    SKUInfoEntity findById(CredentialEntity credential, String skuId) throws Exception;

    /**
     * 更新SKU
     * 
     * @param appId
     * @param skuInfoEntity
     */
    void update(String appId, SKUInfoEntity skuInfoEntity) throws Exception;
    
    /**
     * 查询SKU库存所有信息
     * 
     * @param credential
     * @param skuId
     * @return
     */
    SKUAllWarehouseInfoEntity findSKUALLWarehouseInfosById(CredentialEntity credential, String skuId) throws Exception;
    
}
