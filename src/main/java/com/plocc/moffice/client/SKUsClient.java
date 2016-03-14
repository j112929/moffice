/**
 * 宝龙电商
 * com.plocc.moffice.client
 * ProductClient.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SKUAllWarehouseInfoEntity;
import com.plocc.moffice.entity.SKUInfoEntity;

/**
 * SKU接口
 * 
 * @author Administrator
 *
 */
public interface SKUsClient {

    /**
     * 添加SKU
     * 
     * @param credential
     * @param skuInfoEntity
     */
    Integer add(CredentialEntity credential, SKUInfoEntity skuInfoEntity) throws Exception;
    
    /**
     * 根据skuID删除SKU
     * 
     * @param credential
     * @param skuId
     */
    void delete(CredentialEntity credential, String skuId) throws Exception;
    
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
     * @param credential
     * @param skuInfoEntity
     */
    void update(CredentialEntity credential, SKUInfoEntity skuInfoEntity) throws Exception;
    
    /**
     * 查询SKU库存所有信息
     * 
     * @param credential
     * @param skuId
     * @return
     */
    SKUAllWarehouseInfoEntity findSKUALLWarehouseInfosById(CredentialEntity credential, String skuId) throws Exception;
    
}
