package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.plocc.moffice.client.InventoryCountingClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.InventoryCountingEntity;
import com.plocc.moffice.support.Constants;

/**
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class InventoryCountingClientImpl extends BaseClient implements InventoryCountingClient {

    /**
     * @see com.plocc.moffice.client.InventoryCountingClient#add(com.plocc.moffice.entity.CredentialEntity, com.plocc.moffice.entity.InventoryCountingEntity)
     */
    @Override
    public Integer add(CredentialEntity credential, InventoryCountingEntity inventoryCounting) throws Exception {
        Map params = checkParams(credential, new HashMap());
        Integer result = postForObject(Constants.CLIENT_API_URL_PRODUCT_CREATE, new HashMap(inventoryCounting), Integer.class, params);
        return result;
    }

    @Override
    public List<InventoryCountingEntity> findAll(CredentialEntity credentialMaster) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryCountingEntity findById(CredentialEntity credentialMaster, String inventoryCountingId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cancelById(CredentialEntity credentialMaster, String inventoryCountingId) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void confilmById(CredentialEntity credentialMaster, String inventoryCountingId) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
