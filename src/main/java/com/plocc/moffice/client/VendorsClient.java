/**
 * 宝龙电商
 * com.plocc.moffice.client
 * ProductClient.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import java.util.List;
import java.util.Map;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.VendorEntity;

/**
 * 供应商接口
 * 
 * @author xing_biao
 * 2016年2月23日
 * @version 1.0.0
 *
 */
public interface VendorsClient {
    
    /**
     * 添加供应商
     * 
     * @param credential
     * @param vendorInfoEntity
     */
    public void add(CredentialEntity credential, VendorEntity vendorInfoEntity) throws Exception;
    
    /**
     * 根据ID删除供应商
     * 
     * @param credential
     * @param vendorInfoEntity
     */
    public void delete(CredentialEntity credential, VendorEntity vendorInfoEntity) throws Exception;
    
    /**
     * 根据ID查询供应商
     * 
     * @param credential
     * @param id
     * @return
     */
    public VendorEntity findById(CredentialEntity credential, Integer id) throws Exception;
    
    /**
     * 根据供应商code查询是否已存在该供应商
     * @param credential
     * @param vendorCode
     * @return
     * @exception
     */
    public VendorEntity findVendorByCode(CredentialEntity credential, String vendorCode) throws Exception;
    
    /**
     * 根据ID更新供应商
     * 
     * @param credential
     * @param vendorInfoEntity
     */
    public void update(CredentialEntity credential, VendorEntity vendorInfoEntity) throws Exception;
    
    /**
     * 查询所有供应商
     * 
     * @param credential
     * @param params
     */
    public List<VendorEntity> getAll(CredentialEntity credential, Map params) throws Exception;

}
