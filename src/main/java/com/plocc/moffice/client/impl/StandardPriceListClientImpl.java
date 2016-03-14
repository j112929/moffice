package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.plocc.moffice.client.StandardPriceListClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.StandardPriceListEntity;
import com.plocc.moffice.support.Constants;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class StandardPriceListClientImpl extends BaseClient implements StandardPriceListClient {

    @Override
    public StandardPriceListEntity findBySKUId(CredentialEntity credential, String skuId) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", skuId);
        StandardPriceListEntity standardPriceListEntity = getForObject(Constants.CLIENT_API_URL_STANDARDPRICELIST_SKUPRICE_ID_GET, StandardPriceListEntity.class, params);
        return standardPriceListEntity;
    }

    @Override
    public void updateBySKUId(CredentialEntity credentialMaster, StandardPriceListEntity standardPriceList, String skuId) throws Exception {
        Map params = checkParams(credentialMaster, new HashMap());
        params.put("id", skuId);
        patchForObject(Constants.CLIENT_API_URL_INVENTORYCOUNTING_SKUPRICE_ID_PATCH, new HashMap(standardPriceList), String.class, params);
    }

}
