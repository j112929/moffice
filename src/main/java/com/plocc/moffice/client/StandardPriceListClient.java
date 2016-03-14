package com.plocc.moffice.client;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.StandardPriceListEntity;

/**
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
public interface StandardPriceListClient {
    
    /**
     * 根据Id获取详情
     * 
     * @param credential
     * @param skuId
     * @throws Exception
     */
    public StandardPriceListEntity findBySKUId(CredentialEntity credential, String skuId) throws Exception;

    /**
     * 根据SKUId更新SKU价格
     * 
     * @param credentialMaster
     * @param standardPriceList
     * @param skuId
     * @return
     * @throws Exception
     * @exception
     */
    public void updateBySKUId(CredentialEntity credentialMaster, StandardPriceListEntity standardPriceList, String skuId) throws Exception;
    
}
