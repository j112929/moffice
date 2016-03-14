package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.VariantClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.entity.VariantInfoEntity;
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
public class VariantClientImpl extends BaseClient implements VariantClient {

    @Override
    public List<VariantInfoEntity> getAll(CredentialEntity credential, Map<String, Object> params) throws Exception {
        params = checkParams(credential, params);
        String resultStr = getForObject(Constants.CLIENT_API_URL_VARIANTS_ALL_GET, String.class, params);
        List<VariantInfoEntity> results = JsonpHelper.toObject(resultStr, new TypeReference<List<VariantInfoEntity>>() {
        });
        return results;
    }

    @Override
    public VariantInfoEntity getVariantByName(CredentialEntity credential, String name) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("name", name);
        String resultStr = getForObject(Constants.CLIENT_API_URL_VARIANTS_ALL_GET + "&filter=name eq '{name}'", String.class, params);
        List<ProductEntity> results = JsonpHelper.toObject(resultStr, new TypeReference<List<ProductEntity>>() {
        });
        if (null != results && results.size() > 0) {
            return new VariantInfoEntity(results.get(0));
        }
        return null;
    }

    @Override
    public Integer add(CredentialEntity credentialMaster, VariantInfoEntity variantInfoEntity) throws Exception {
        Map<String, Object> params = checkParams(credentialMaster, new HashMap<String, Object>());
        ResponseEntity<Integer> resp = postForEntity(Constants.CLIENT_API_URL_VARIANTS_CREATE_POST, new HashMap<String, Object>(variantInfoEntity), Integer.class, params);
        return resp.getBody();
    }

    @Override
    public VariantInfoEntity getById(CredentialEntity credential, String variantId) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", variantId);
        VariantInfoEntity variantInfoEntity = getForObject(Constants.CLIENT_API_URL_VARIANTS_ID_GET, VariantInfoEntity.class, params);
        return variantInfoEntity;
    }

    @Override
    public void update(CredentialEntity credentialMaster, VariantInfoEntity variantInfoEntity) throws Exception {
        Map params = checkParams(credentialMaster, new HashMap());
        params.put("id", variantInfoEntity.getId());
        patchForObject(Constants.CLIENT_API_URL_VARIANTS_ID_PATCH, variantInfoEntity, String.class, params);
    }

}
