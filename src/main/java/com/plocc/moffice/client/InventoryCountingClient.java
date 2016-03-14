package com.plocc.moffice.client;

import java.util.List;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.InventoryCountingEntity;

/**
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
public interface InventoryCountingClient {
    
    /**
     * 查询所有的库存
     * 
     * @param credentialMaster
     * @return
     */
    public List<InventoryCountingEntity> findAll(CredentialEntity credentialMaster);
    
    /**
     * 新增库存
     * 
     * @param credential
     * @param inventoryCounting
     * @return
     * @throws Exception
     * @exception
     */
    public Integer add(CredentialEntity credentialMaster, InventoryCountingEntity inventoryCounting) throws Exception;
    
    /**
     * 根据Id获取详情
     * 
     * @param credentialMaster
     * @param inventoryCountingId
     * @throws Exception
     */
    public InventoryCountingEntity findById(CredentialEntity credentialMaster, String inventoryCountingId) throws Exception;
    
    /**
     * 取消库存
     * 
     * @param credentialMaster
     * @param inventoryCountingId
     * @return
     * @throws Exception
     */
    public void cancelById(CredentialEntity credentialMaster, String inventoryCountingId) throws Exception;
    
    /**
     * 确认库存
     * 
     * @param credentialMaster
     * @param inventoryCountingId
     * @throws Exception
     */
    public void confilmById(CredentialEntity credentialMaster, String inventoryCountingId) throws Exception;
    
}
