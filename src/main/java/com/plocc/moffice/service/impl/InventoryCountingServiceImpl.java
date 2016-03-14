package com.plocc.moffice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.InventoryCountingClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.InventoryCountingEntity;
import com.plocc.moffice.service.InventoryCountingService;

/**
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
@Service
public class InventoryCountingServiceImpl implements InventoryCountingService {

    private final Logger LOGGER = LoggerFactory.getLogger(InventoryCountingServiceImpl.class);
    
    @Autowired
    private InventoryCountingClient inventoryCountingClient;
    
    /**
     * @see com.plocc.moffice.service.InventoryCountingService#add(com.plocc.moffice.entity.CredentialEntity, java.lang.Object)
     */
    @Override
    public void add(CredentialEntity credentialMaster, InventoryCountingEntity inventoryCountingEntity) throws Exception {
        try {
            Integer inventoryCountingId = inventoryCountingClient.add(credentialMaster, inventoryCountingEntity);
            // 确认库存盘点才能加上库存
            this.confilmById(credentialMaster, String.valueOf(inventoryCountingId));
        }
        catch (Exception e) {
            LOGGER.error("add 出错：credentialMaster=" + credentialMaster + ", inventoryCountingEntity=" + inventoryCountingEntity, e);
            throw new Exception(e);
        }
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
        try {
            inventoryCountingClient.confilmById(credentialMaster, inventoryCountingId);
        }
        catch (Exception e) {
            LOGGER.error("confilmById 出错：credentialMaster=" + credentialMaster + ", inventoryCountingId=" + inventoryCountingId, e);
            throw new Exception(e);
        }
    }

}
