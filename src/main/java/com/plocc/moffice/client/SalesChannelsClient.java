package com.plocc.moffice.client;

import java.util.List;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesChannelsEntity;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月29日 下午4:51:56 
 * @email 1129290218@qq.com
 * @description  
 */
public interface SalesChannelsClient {
	/**
	 * 销售渠道列表查询
	 * @param credential
	 * @return
	 */
	List<SalesChannelsEntity> selectSalesChannelsEntityList(CredentialEntity credential) throws Exception;
	/**
	 * 根据name查询销售渠道
	 * @param credential
	 * @param channelName
	 * @return
	 */
	SalesChannelsEntity selectByName(CredentialEntity credential, String channelName) throws Exception;
}
