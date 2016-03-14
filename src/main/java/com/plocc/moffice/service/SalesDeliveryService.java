package com.plocc.moffice.service;

import java.util.List;
import java.util.Map;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesDeliveryEntity;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月19日 下午8:39:35 
 * @email 1129290218@qq.com
 * @description  
 */
public interface SalesDeliveryService {
	/**
	 * 查询发货信息列表
	 * @return
	 */
	List<SalesDeliveryEntity> selectSalesDeliveryList(CredentialEntity credential);
	/**
	 * 创建发货信息
	 * @param credential
	 */
	void createSalesDelivery(CredentialEntity credential,Map<?, ?> tmpMap);
    /**
     * 查询单个发货信息
     * @param credential
     * @param deliveryId
     */
    SalesDeliveryEntity selectById(CredentialEntity credential,int deliveryId);
    /**
     * 更新单个发货信息，确认送达
     * OPEN
		CLOSED
		PICK
		PACK
		SHIPPING
		PENDING
		
     * @param tmpMap
     */
    void update(Map<?, ?> tmpMap);
    
    /**
     * 取消发货    CANCELLED
     * @param tmpMap
     */
    void cancel(Map<?, ?> tmpMap);
    
    /**
     * 确认送达
     * @param tmpMap
     */
    void confirmShip(Map<?, ?> tmpMap);

    /**
     * 发货同步
     * @param tmpMap
     */
    void deliverySync(Map<?, ?> tmpMap);
}
