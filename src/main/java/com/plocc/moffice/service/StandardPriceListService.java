package com.plocc.moffice.service;

import java.util.Map;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.StandardPriceListEntity;

/**
 * 
 * @author xing_biao
 * 2016年2月24日
 * @version 1.0.0
 *
 */
public interface StandardPriceListService {
    
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
    
    /**
     * 更新SKU价格信息
     * 
     * @param map
     * @throws Exception
     */
    public void productSync(Map<String, Object> map) throws Exception;
    
}
