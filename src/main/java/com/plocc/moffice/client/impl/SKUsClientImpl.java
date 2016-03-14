package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.SKUsClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SKUAllWarehouseInfoEntity;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.support.Constants;

@SuppressWarnings("unchecked")
@Service
public class SKUsClientImpl extends BaseClient implements SKUsClient {

    @Override
    public Integer add(CredentialEntity credential, SKUInfoEntity skuInfoEntity) throws Exception {
        Map<String, Object> params = checkParams(credential, new HashMap<String, Object>());
        ResponseEntity<Integer> resp = postForEntity(Constants.CLIENT_API_URL_SKUS_CREATE_POST, new HashMap<String, Object>(skuInfoEntity), Integer.class, params);
        return resp.getBody();
    }

    @Override
    public void delete(CredentialEntity credential, String skuId) throws Exception {
        Map<String, Object> params = checkParams(credential, new HashMap<String, Object>());
        params.put("id", skuId);
        delete(Constants.CLIENT_API_URL_SKUS_ID_DELETE, String.class, params);
    }

    @Override
    public SKUInfoEntity findById(CredentialEntity credential, String skuId) {
        Map<String, Object> params = checkParams(credential, new HashMap<String, Object>());
        params.put("id", skuId);
        ResponseEntity<SKUInfoEntity> resp = getForEntity(Constants.CLIENT_API_URL_SKUS_ID_GET, SKUInfoEntity.class, params);
        return resp.getBody();
    }

    @Override
    public void update(CredentialEntity credential, SKUInfoEntity skuInfoEntity) {
        Map<String, Object> params = checkParams(credential, new HashMap<String, Object>());
        params.put("id", skuInfoEntity.getId());
        patchForObject(Constants.CLIENT_API_URL_SKUS_ID_GET, skuInfoEntity, String.class, params);
    }

    @Override
    public SKUAllWarehouseInfoEntity findSKUALLWarehouseInfosById(CredentialEntity credential, String skuId) {
        Map<String, Object> params = checkParams(credential, new HashMap<String, Object>());
        params.put("id", skuId);
        ResponseEntity<SKUAllWarehouseInfoEntity> resp = getForEntity(Constants.CLIENT_API_URL_SKUS_ID_ALLWAREHOUSEINFOS_GET, SKUAllWarehouseInfoEntity.class, params);
        return resp.getBody();
    }

}
