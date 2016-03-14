package com.plocc.moffice.service;

import java.util.List;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.InventoryCountingEntity;

/**
 * 库存接口
 *
 * @author xing_biao
 *         2016年2月24日
 * @version 1.0.0
 */
// @Todo 定义 Service 时根据 需求 先想清楚 潜在需要使用的方法、定义了不实现空着有什么用。
public interface InventoryCountingService {

    /**
     * 查询所有的库存
     *
     * @param credentialMaster
     * @return
     */
    public List<InventoryCountingEntity> findAll(CredentialEntity credentialMaster);

    /**
     * 添加库存
     *
     * @param credentialMaster
     * @param json
     * @throws Exception
     * @throws
     */
    public void add(CredentialEntity credentialMaster, InventoryCountingEntity inventoryCountingEntity) throws Exception;

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
