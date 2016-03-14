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
import com.plocc.moffice.entity.VariantValueInfoEntity;

/**
 * 属性类别值值接口
 * 
 * @author Administrator
 *
 */
public interface VariantValueService {
    
    /**
     * 查询所有属性类别值
     * 
     * @param credential
     * @param params
     */
    public List<VariantValueInfoEntity> getAll(CredentialEntity credential, Map<String, Object> params) throws Exception;
    
    /**
     * 根据属性类别值code查询是否已存在该属性类别值
     * @param credential
     * @param variantValueCode
     * @return
     * @exception
     */
    public VariantValueInfoEntity getVariantValueByCode(CredentialEntity credential, String variantValueCode) throws Exception;
    
    /**
     * 添加属性类别值
     * 
     * @param credentialMaster
     * @param variantValueInfoEntity
     */
    public Integer add(VariantValueInfoEntity variantValueInfoEntity) throws Exception;
    
    /**
     * 根据ID查询属性类别值
     * 
     * @param credential
     * @param variantValueId
     * @return
     */
    public VariantValueInfoEntity getById(CredentialEntity credential, String variantValueId) throws Exception;

    
    /**
     * 根据ID更新属性类别值
     * 
     * @param credentialMaster
     * @param variantValueInfoEntity
     */
    public void update(CredentialEntity credentialMaster, VariantValueInfoEntity variantValueInfoEntity) throws Exception;
    
    /**
     * 重新组装VariantValue
     * 
     * @param credential
     * @param listVariantValues
     * @return
     * @throws Exception
     */
    public List<VariantValueInfoEntity> getVariantValueListNew(String appId, List<VariantValueInfoEntity> listVariantValues) throws Exception;

}
