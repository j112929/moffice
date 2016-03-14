package com.plocc.moffice.client;

import java.util.List;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesDeliveryEntity;

/** 
 * SalesDeliveryClient
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 上午11:43:20 
 * @email 1129290218@qq.com
 * @description  
 */
public interface SalesDeliveryClient {
	/**
	 * 查询发货信息列表
	 * @return
	 */
	List<SalesDeliveryEntity> selectSalesDeliveryList(CredentialEntity credential) throws Exception;
	/**
	 * 创建发货信息
	 * @param salesDeliveryEntity
	 */
	String createSalesDelivery(CredentialEntity credential,SalesDeliveryEntity salesDeliveryEntity) throws Exception;
    /**
     * 查询单个发货信息
     * @param id
     * @return
     */
    SalesDeliveryEntity selectById(CredentialEntity credential,int deliveryId) throws Exception;
    /**
     * 更新单个发货信息，确认送达
     * @param salesDeliveryEntity
     */
    void update(CredentialEntity credential,SalesDeliveryEntity salesDeliveryEntity) throws Exception;
    
    /**
     * 取消发货
     * @param credential
     * @param salesDeliveryEntity
     */
    void cancel(CredentialEntity credential,SalesDeliveryEntity salesDeliveryEntity) throws Exception;
    
    /**
     * 确认送达
     * @param credential
     * @param salesDeliveryEntity
     */
    void confirmShip(CredentialEntity credential,SalesDeliveryEntity salesDeliveryEntity) throws Exception;
}
