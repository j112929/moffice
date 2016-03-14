package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.VariantValueClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.VariantValueInfoEntity;
import com.plocc.moffice.support.Constants;

/**
 * 
 * @author xing_biao
 * 2016年2月23日
 * @version 1.0.0
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class VariantValueClientImpl extends BaseClient implements VariantValueClient {

    @Override
    public List<VariantValueInfoEntity> getAll(CredentialEntity credential, Map<String, Object> params) throws Exception {
        params = checkParams(credential, params);
        String resultStr = getForObject(Constants.CLIENT_API_URL_VARIANTVALUES_ALL_GET, String.class, params);
        List<VariantValueInfoEntity> results = JsonpHelper.toObject(resultStr, new TypeReference<List<VariantValueInfoEntity>>() {
        });
        return results;
    }

    @Override
    public VariantValueInfoEntity getVariantValueByCode(CredentialEntity credential, String variantValueCode) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("code", variantValueCode);
        String resultStr = getForObject(Constants.CLIENT_API_URL_VARIANTVALUES_ALL_GET + "&filter=code eq '{code}'", String.class, params);
        List<ProductEntity> results = JsonpHelper.toObject(resultStr, new TypeReference<List<ProductEntity>>() {
        });
        if (null != results && results.size() > 0) {
            return new VariantValueInfoEntity(results.get(0));
        }
        return null;
    }

    @Override
    public Integer add(CredentialEntity credentialMaster, VariantValueInfoEntity variantValueInfoEntity) throws Exception {
        Map<String, Object> params = checkParams(credentialMaster, new HashMap<String, Object>());
        ResponseEntity<Integer> resp = postForEntity(Constants.CLIENT_API_URL_VARIANTVALUES_CREATE_POST, new HashMap<String, Object>(variantValueInfoEntity), Integer.class, params);
        return resp.getBody();
    }

    @Override
    public VariantValueInfoEntity getById(CredentialEntity credential, String variantValueId) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", variantValueId);
        VariantValueInfoEntity variantValueInfoEntity = getForObject(Constants.CLIENT_API_URL_VARIANTVALUES_ID_GET, VariantValueInfoEntity.class, params);
        return variantValueInfoEntity;
    }

    @Override
    public void update(CredentialEntity credentialMaster, VariantValueInfoEntity variantValueInfoEntity) throws Exception {
        Map params = checkParams(credentialMaster, new HashMap());
        params.put("id", variantValueInfoEntity.getId());
        patchForEntity(Constants.CLIENT_API_URL_VARIANTVALUES_ID_PATCH, variantValueInfoEntity, String.class, params);
    }

}
