package com.plocc.moffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesOrderEntity;

/** 
 * SalesOrderService
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月19日 下午3:07:01 
 * @email 1129290218@qq.com
 * @description  订单接口
 */
public interface SalesOrderService {
	/**
	 * 创建主订单
	 * @param credential
     * @param orderArray
	 */
	String createParentOrder(CredentialEntity credential,List<?> paramsList) throws Exception;
	
	/**
	 * 创建子订单
	 * @param paramsMap
	 */
	List<?> createChildrenOrder(Map<?, ?> paramsMap) throws Exception;
	
	/**
	 * 查询单个订单信息
	 * @param credential
	 * @param orderId
	 */
	SalesOrderEntity selectById(CredentialEntity credential,Long orderId) throws Exception;
	
	/**
	 * 订单分页
	 * @param credential
	 * @param params
	 * @param pager
	 */
	Page<SalesOrderEntity> pager(CredentialEntity credential,Map<?, ?> params, Pageable pager) throws Exception;
	
	/**
	 * 据客户Id查订单列表
	 * @param credential
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	Page<SalesOrderEntity> findByCustomerId(CredentialEntity credential,Map<?, ?> params, Pageable pager) throws Exception;
	/**
	 * 订单更新 OPEN,CLOSED
     * @param paramsMap
	 */
	void update(Map<?, ?> paramsMap) throws Exception;
	
	/**
	 * 取消订单  CANCEL,
     * @param paramsMap
	 */
	void cancel(Map<?, ?> paramsMap) throws Exception;
	
	/**
	 * 订单同步
	 * @param paramsMap
	 */
	void orderSync(Map<?, ?> paramsMap) throws Exception;
}
