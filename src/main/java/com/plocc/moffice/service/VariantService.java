/**
 * 宝龙电商
 * com.plocc.moffice.client
 * ProductClient.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service;

import java.util.List;
import java.util.Map;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.VariantInfoEntity;

/**
 * 属性类别接口
 * 
 * @author Administrator
 *
 */
public interface VariantService {
    
    /**
     * 查询所有属性类别
     * 
     * @param credential
     * @param params
     */
    public List<VariantInfoEntity> getAll(CredentialEntity credential, Map<String, Object> params) throws Exception;
    
    /**
     * 根据属性类别name查询是否已存在该属性类别
     * @param credential
     * @param name
     * @return
     * @exception
     */
    public VariantInfoEntity findVariantByName(CredentialEntity credential, String name) throws Exception;
    
    /**
     * 添加属性类别
     * 
     * @param credentialMaster
     * @param variantInfoEntity
     */
    public Integer add(CredentialEntity credentialMaster, VariantInfoEntity variantInfoEntity) throws Exception;
    
    /**
     * 根据ID查询属性类别
     * 
     * @param credential
     * @param variantId
     * @return
     */
    public VariantInfoEntity getById(CredentialEntity credential, String variantId) throws Exception;

    
    /**
     * 根据ID更新属性类别
     * 
     * @param credentialMaster
     * @param variantInfoEntity
     */
    public void update(CredentialEntity credentialMaster, VariantInfoEntity variantInfoEntity) throws Exception;

    /**
     * 根据属性类别name查询本地数据中是否已存在该属性类别
     * @param appId
     * @param variantName
     * @return
     * @exception
     */
    public VariantInfoEntity findVariantByNameFromDB(String appId, String variantName) throws Exception;
    
}
