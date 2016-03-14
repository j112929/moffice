package com.plocc.moffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.SalesChannelsClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesChannelsEntity;
import com.plocc.moffice.service.SalesChannelService;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月29日 下午5:11:47 
 * @email 1129290218@qq.com
 * @description  
 */
@Service
public class SalesChannelServiceImpl implements SalesChannelService {
	@Autowired
	private SalesChannelsClient salesChannelsClient;
	public SalesChannelsEntity selectByAppId(CredentialEntity credential,
			String appId) throws Exception {
		// TODO Auto-generated method stub
		SalesChannelsEntity salesChannelsEntity = salesChannelsClient.selectByName(credential, appId);
		return salesChannelsEntity;
	}

	@Override
	public List<SalesChannelsEntity> selectSalesChannelsEntityList(CredentialEntity credential)
			throws Exception {
		// TODO Auto-generated method stub
		List<SalesChannelsEntity> list = salesChannelsClient.selectSalesChannelsEntityList(credential);
		return list;
	}


}
