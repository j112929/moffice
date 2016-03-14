package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.plocc.moffice.client.VendorsClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.VendorEntity;
import com.plocc.moffice.support.Constants;

/**
 * 
 * @author xing_biao
 * 2016年2月23日
 * @version 1.0.0
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class VendorsClientImpl extends BaseClient implements VendorsClient {
    
    /**
     * @see com.plocc.moffice.client.VendorsClient#add(com.plocc.moffice.entity.CredentialEntity, com.plocc.moffice.entity.VendorEntity)
     */
    @Override
    public void add(CredentialEntity credential, VendorEntity vendorInfoEntity) throws Exception {
        Map params = checkParams(credential, new HashMap());
        postForObject(Constants.CLIENT_API_URL_VENDORS, new HashMap(vendorInfoEntity), Integer.class, params);
    }

    /**
     * @see com.plocc.moffice.client.VendorsClient#delete(com.plocc.moffice.entity.CredentialEntity, com.plocc.moffice.entity.VendorEntity)
     */
    @Override
    public void delete(CredentialEntity credential, VendorEntity vendorInfoEntity) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", vendorInfoEntity.getId());
        delete(Constants.CLIENT_API_URL_VENDOR_ID, vendorInfoEntity, String.class, params);
    }
    
    /**
     * @see com.plocc.moffice.client.VendorsClient#update(com.plocc.moffice.entity.CredentialEntity, com.plocc.moffice.entity.VendorEntity)
     */
    @Override
    public void update(CredentialEntity credential, VendorEntity vendorInfoEntity) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", vendorInfoEntity.getId());
        patchForObject(Constants.CLIENT_API_URL_VENDOR_ID, vendorInfoEntity, String.class, params);
    }
    
    /**
     * @see com.plocc.moffice.client.VendorsClient#findById(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer)
     */
    @Override
    public VendorEntity findById(CredentialEntity credential, Integer id) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", id);
        VendorEntity vendorInfoEntity = getForObject(Constants.CLIENT_API_URL_VENDOR_ID, VendorEntity.class, params);
        return vendorInfoEntity;
    }
    
    /**
     * 根据供应商编号查询
     *
     * @param code
     * @return
     */
    @Override
    public VendorEntity findVendorByCode(CredentialEntity credential, String vendorCode) throws Exception {
        Map params = new HashMap();
        params = checkParams(credential, params);
        params.put("vendorCode", vendorCode);
        List<Map> results = getForObject(Constants.CLIENT_API_URL_VENDORS + "&filter=vendorCode eq '{vendorCode}'", List.class, params);
        if (null != results && results.size() > 0) {
            return new VendorEntity(results.get(0));
        }
        return null;
    }

    /**
     * @see com.plocc.moffice.client.VendorsClient#getAll(com.plocc.moffice.entity.CredentialEntity, java.util.Map)
     */
    @Override
    public List<VendorEntity> getAll(CredentialEntity credential, Map params) throws Exception {
        params = checkParams(credential, params);
        List<VendorEntity> results = getForObject(Constants.CLIENT_API_URL_VENDORS, List.class, params);
        return results;
    }

}
