/**
 * 宝龙电商
 * com.plocc.moffice.repository
 * OrderRepositoryImpl.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.SalesOrderClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesOrderEntity;
import com.plocc.moffice.support.Constants;

/**
 * OrderRepositoryImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 12:42
 * @email zhanggj@powerlong.com
 * @description 订单客户端
 */
@Service
public class SalesOrderClientImpl extends BaseClient implements SalesOrderClient {
	private Logger LOGGER = LoggerFactory.getLogger(SalesOrderClientImpl.class);
    @SuppressWarnings("unchecked")
	public Integer count(CredentialEntity credential, Map params) throws Exception {
    	Integer result = 0;
        params = checkParams(credential, params);
        try {
        	ResponseEntity response = getForEntity(Constants.CLIENT_API_URL_SALES_ORDER_COUNT, Integer.class, params);
            if(response.getStatusCode()==HttpStatus.OK){
            	result = (Integer) response.getBody();
            }else if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
            	LOGGER.warn("订单计数请求过多，请等待3秒后操作！", new Exception("订单计数请求过多，请等待3秒后操作！"));
            }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("订单计数请求失败！", new Exception("订单计数请求失败！"));
			throw e;
		}
        
        return result;
    }

    public SalesOrderEntity findById(CredentialEntity credential, Long orderId) throws Exception {
    	SalesOrderEntity salesOrderEntity = new SalesOrderEntity();
        Map params = checkParams(credential, new HashMap());
        params.put("id", orderId);
        try {
        	ResponseEntity response = getForEntity(Constants.CLIENT_API_URL_SALES_ORDER, SalesOrderEntity.class, params);
            if(response.getStatusCode()==HttpStatus.OK){
            	salesOrderEntity = JsonpHelper.toObject(response.getBody().toString(),SalesOrderEntity.class);
            }else if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
            	LOGGER.warn("据ID订单查询请求过多，请等待3秒后操作！", new Exception("据ID订单查询请求过多，请等待3秒后操作！"));
            }
		} catch (Exception e) {
			LOGGER.error("据ID订单查询请求失败！", new Exception("据ID订单查询请求失败！"));
			throw e;
		}
        
        return salesOrderEntity;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(CredentialEntity credential, SalesOrderEntity salesOrderEntity, Map params) throws Exception {
        params = checkParams(credential, new HashMap());
        ResponseEntity response = postForEntity(Constants.CLIENT_API_URL_SALES_ORDER, salesOrderEntity, String.class, params);
        if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
        	LOGGER.warn("订单更新请求过多，请等待3秒后操作！", new Exception());
        }else if(response.getStatusCode()!=HttpStatus.OK){
        	LOGGER.error("订单更新失败!", new Exception("订单更新失败!"));
        }
    }

    public Page<SalesOrderEntity> pager(CredentialEntity credential, Map params, Pageable pager) throws Exception {
        params = checkParams(credential, params);
        Integer count = count(credential, params);
        params.put("limit", pager.getPageSize());
        params.put("offset", (pager.getPageNumber() - 1) * pager.getPageSize());
        String url = Constants.CLIENT_API_URL_SALES_ORDERS;
        if (params.containsKey("select")) {
            url += "&select={select}";
        }
        String results = "";
        try {
        	ResponseEntity response = getForEntity(url, String.class, params);
            if(response.getStatusCode()==HttpStatus.OK){
            	results = response.getBody().toString();
            }else if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
            	LOGGER.warn("订单分页查询请求过多，请等待3秒后操作！", new Exception("订单分页查询请求过多，请等待3秒后操作！"));
            }
		} catch (Exception e) {
			LOGGER.error("订单分页查询请求失败！", e);
			throw e;
		}
        
        List<SalesOrderEntity> salesOrderEntityList = JsonpHelper.toObject(results, new TypeReference<List<SalesOrderEntity>>() {
        });
        return new PageImpl(salesOrderEntityList, pager, count);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createOrder(CredentialEntity credential, SalesOrderEntity salesOrder) throws Exception {
        Map params = checkParams(credential, new HashMap());
        String result = "";
        ResponseEntity<Integer> response;
		try {
			response = postForEntity(Constants.CLIENT_API_URL_SALES_ORDER_CREATE, new HashMap(salesOrder), Integer.class, params);
			if(response.getStatusCode()==HttpStatus.CREATED){
				result = JsonpHelper.toString(response.getBody());
			}else if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
				LOGGER.warn("订单新增请求过多，请等待3秒后操作！", new Exception("订单新增请求过多，请等待3秒后操作！"));
			}
		} catch (RestClientException e) {
			LOGGER.error("订单新增失败!", e);
			throw e;
		}
        return result;
    }

    public void cancelOrder(CredentialEntity credential, SalesOrderEntity salesOrderEntity, Map params) throws Exception {
        params = checkParams(credential, new HashMap());
        try {
        	ResponseEntity response = postForEntity(Constants.CLIENT_API_URL_SALES_ORDER_CANCEL, salesOrderEntity, String.class, params);
            if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
            	LOGGER.warn("订单取消请求过多，请等待3秒后操作！", new Exception());
            }
		} catch (Exception e) {
			LOGGER.error("订单取消失败!", e);
			throw e;
		}
        
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<SalesOrderEntity> findByCustomerId(CredentialEntity credential,
			Map params, Pageable pager) throws Exception {
		// TODO Auto-generated method stub
		params = checkParams(credential, params);
        Integer count = count(credential, params);
        params.put("limit", pager.getPageSize());
        params.put("offset", (pager.getPageNumber() - 1) * pager.getPageSize());
        params.put("orderBy", "creationTime desc");
        params.put("customerId", params.get("customerId"));
        String url = Constants.CLIENT_API_URL_SALES_ORDERS_CUSTOMER;
        url += "&filter=customer.id eq {customerId}";
        String results = "";
        try {
        	ResponseEntity response = getForEntity(url, String.class, params);
        	if(response.getStatusCode()==HttpStatus.OK){
        		results = response.getBody().toString();
        	}else if(response.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
        		LOGGER.warn("订单分页查询请求过多，请等待3秒后操作！", new Exception("订单分页查询请求过多，请等待3秒后操作！"));
        	}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("订单分页查询请求失败！", e);
			throw e;
		}
        List<SalesOrderEntity> salesOrderEntityList = JsonpHelper.toObject(results, new TypeReference<List<SalesOrderEntity>>() {
        });
        return new PageImpl(salesOrderEntityList, pager, count);
	}

}
