package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.entity.Model;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.SalesDeliveryClient;
import com.plocc.moffice.client.SalesOrderClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesDeliveryEntity;
import com.plocc.moffice.entity.SalesOrderEntity;
import com.plocc.moffice.support.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.mapping.support.JsonHeaders;
import org.springframework.stereotype.Service;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月18日 下午1:13:58 
 * @email 1129290218@qq.com
 * @description  
 */
@Service
public class SalesDeliveryClientImpl extends BaseClient implements SalesDeliveryClient {
	private Logger LOGGER = LoggerFactory.getLogger(SalesDeliveryClientImpl.class);
	public List<SalesDeliveryEntity> selectSalesDeliveryList(CredentialEntity credential) throws Exception{
		Map params = new HashMap();
        params.put("offset", 0);
        params.put("limit", 10);
        String result = "";
        try {
        	ResponseEntity resp = getForEntity(Constants.CLIENT_API_URL_SALES_DELIVERIES, String.class, params);
    		if(resp.getStatusCode()==HttpStatus.OK){
    			result = resp.getBody().toString();
    		}else if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
            	LOGGER.warn("发货查询请求过多，请等待3秒后操作！", new Exception("发货查询请求过多，请等待3秒后操作！"));
            }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("发货查询请求失败！", new Exception("发货查询请求失败！"));
			throw e;
		}
        
        List<SalesDeliveryEntity> orders = JsonpHelper.toObject(result, new TypeReference<List<SalesDeliveryEntity>>() {});
		return orders;
	}

	public String createSalesDelivery(CredentialEntity credential,SalesDeliveryEntity salesDeliveryEntity) throws Exception{
		Map params = checkParams(credential, new HashMap());
		params.put("id", salesDeliveryEntity.getId());
		String result = "";
		try {
			ResponseEntity resp = postForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY_CREATE, salesDeliveryEntity, String.class, params);
			if(resp.getStatusCode()==HttpStatus.OK){
				result = resp.getBody().toString();
			}else if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
	        	LOGGER.warn("发货新增请求过多，请等待3秒后操作！", new Exception("发货新增请求过多，请等待3秒后操作！"));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("发货新增请求失败！", new Exception("发货新增请求失败！"));
			throw e;
		}
		
		return result;
	}

	public SalesDeliveryEntity selectById(CredentialEntity credential, int deliveryId) throws Exception{
		Map params = checkParams(credential, new HashMap());
        params.put("id", deliveryId);
        SalesDeliveryEntity salesDeliveryEntity = new SalesDeliveryEntity();
        try {
        	ResponseEntity resp = getForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY, SalesDeliveryEntity.class, params);
            if(resp.getStatusCode()==HttpStatus.OK){
            	salesDeliveryEntity = JsonpHelper.toObject(resp.getBody().toString(),SalesDeliveryEntity.class);
    		}else if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
            	LOGGER.warn("据ID发货查询请求过多，请等待3秒后操作！", new Exception("据ID发货查询请求过多，请等待3秒后操作！"));
            }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("据ID发货查询请求失败！", new Exception("据ID发货查询请求失败！"));
		}
        
        return salesDeliveryEntity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(CredentialEntity credential,	SalesDeliveryEntity salesDeliveryEntity) throws Exception{
		Map params = checkParams(credential, new HashMap());
		params.put("id", salesDeliveryEntity.getId());
		try {
			ResponseEntity resp = postForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY, salesDeliveryEntity, String.class, params);
			if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
	        	LOGGER.warn("发货更新请求过多，请等待3秒后操作！", new Exception("发货更新请求过多，请等待3秒后操作！"));
	        }else if(resp.getStatusCode()!=HttpStatus.OK){
	        	
	        }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("发货更新失败!", new Exception("发货更新失败!"));
			throw e;
		}
		
	}

	public void cancel(CredentialEntity credential,	SalesDeliveryEntity salesDeliveryEntity) throws Exception{
		Map params = checkParams(credential, new HashMap());
		params.put("id", salesDeliveryEntity.getId());
		try {
			ResponseEntity resp = postForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY_CANCEL, salesDeliveryEntity, String.class, params);
			if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
	        	LOGGER.warn("发货取消请求过多，请等待3秒后操作！", new Exception("发货取消请求过多，请等待3秒后操作！"));
	        }else if(resp.getStatusCode()!=HttpStatus.OK){
	        	LOGGER.error("发货取消失败!", new Exception("发货取消失败!"));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("发货取消失败!", new Exception("发货取消失败!"));
			throw e;
		}
		
	}

	public void confirmShip(CredentialEntity credential,SalesDeliveryEntity salesDeliveryEntity) throws Exception{
		Map params = checkParams(credential, new HashMap());
		params.put("id", salesDeliveryEntity.getId());
		try {
			ResponseEntity resp = postForEntity(Constants.CLIENT_API_URL_SALES_DELIVERY_CONFIRM, salesDeliveryEntity, String.class, params);
			if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS){
	        	LOGGER.warn("发货确认请求过多，请等待3秒后操作！", new Exception("发货确认请求过多，请等待3秒后操作！"));
	        }else if(resp.getStatusCode()!=HttpStatus.OK){
	        	LOGGER.error("发货确认失败!", new Exception("发货确认失败!"));
	        }
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("发货确认失败!", new Exception("发货确认失败!")); 
			throw e;
		}
		
	}

}
