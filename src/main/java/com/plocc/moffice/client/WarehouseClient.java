package com.plocc.moffice.client;

import java.util.List;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.WarehouseInfoEntity;

/**
 * 仓库管理
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
public interface WarehouseClient {
    
    /**
     * 查询所有的子仓库信息
     * 
     * @param credential
     * @return
     */
    public List<WarehouseInfoEntity> findAll(CredentialEntity credential);
    
    /**
     * 根据仓库code查询仓库信息
     * 
     * @param credential
     * @return
     */
    public WarehouseInfoEntity findByCode(CredentialEntity credential, String warehouseCode);
    
    /**
     * 根据Id获取详情
     * 
     * @param credential
     * @param warehouseId
     * @throws Exception
     */
    public WarehouseInfoEntity findById(CredentialEntity credential, String warehouseId) throws Exception;
    
}
