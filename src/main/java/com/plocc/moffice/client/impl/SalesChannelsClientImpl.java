package com.plocc.moffice.client.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.SalesChannelsClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesChannelsEntity;
import com.plocc.moffice.entity.SalesDeliveryEntity;
import com.plocc.moffice.entity.VendorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.mapping.support.JsonHeaders;
import org.springframework.stereotype.Service;

import com.plocc.moffice.support.Constants;


/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月29日 下午4:56:31 
 * @email 1129290218@qq.com
 * @description  
 */
@Service
public class SalesChannelsClientImpl extends BaseClient implements SalesChannelsClient {
	private Logger LOGGER = LoggerFactory.getLogger(SalesChannelsClientImpl.class);
	@Override
	public List<SalesChannelsEntity> selectSalesChannelsEntityList(CredentialEntity credential) throws Exception{
		// TODO Auto-generated method stub
		List list = new ArrayList();
		Map params = new HashMap();
        params.put("offset", 0);
        params.put("limit", 10);
        String result = "";
        try {
        	ResponseEntity resp = getForEntity(Constants.CLIENT_API_URL_SALES_CHANNELS, String.class, params);
        	if(resp.getStatusCode()==HttpStatus.OK){
        		result = resp.getBody().toString();
        	}else if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS) {
        		LOGGER.warn("查询销售渠道请求过多，请3秒后重试！", new Exception("查询销售渠道请求过多，请3秒后重试！"));
        	}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("查询销售渠道请求失败！", e);
			throw e;
		}
        List<SalesChannelsEntity> channels = JsonpHelper.toObject(result, new TypeReference<List<SalesChannelsEntity>>() {});
		return channels;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SalesChannelsEntity selectByName(CredentialEntity credential,String channelName) throws Exception{
		Map params = new HashMap();
        params = checkParams(credential, params);
        params.put("name", channelName);
        List<Map> results = new ArrayList();
        try {
        	ResponseEntity resp = getForEntity(Constants.CLIENT_API_URL_SALES_CHANNEL_NAME + "&filter=name eq '{name}'", List.class, params);
            if(resp.getStatusCode()==HttpStatus.OK){
        		results = JsonpHelper.toObject(JsonpHelper.toString(resp.getBody()),new TypeReference<List>(){});
        	}else if(resp.getStatusCode()==HttpStatus.TOO_MANY_REQUESTS) {
        		LOGGER.warn("查询销售渠道请求过多，请3秒后重试！", new Exception("查询销售渠道请求过多，请3秒后重试！"));
        	}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("查询销售渠道请求失败！", e);
		}		
        
        if (null != results && results.size() > 0) {
            return new SalesChannelsEntity(results.get(0));
        }
        return null;
	}

}
