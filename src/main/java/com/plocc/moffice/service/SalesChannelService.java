package com.plocc.moffice.service;

import java.util.List;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesChannelsEntity;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月29日 下午5:05:46 
 * @email 1129290218@qq.com
 * @description  
 */
public interface SalesChannelService {
	SalesChannelsEntity selectByAppId(CredentialEntity credential,String appId) throws Exception;
	List<SalesChannelsEntity> selectSalesChannelsEntityList(CredentialEntity credential) throws Exception;
}
