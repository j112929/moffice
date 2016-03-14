package com.plocc.moffice.client.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.CategoryClient;
import com.plocc.moffice.entity.CategoryEntity;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.support.Constants;

@SuppressWarnings({"unchecked", "rawtypes"})
@Service
public class CategoryClientImpl extends BaseClient implements CategoryClient {

    @Override
    public List<CategoryEntity> findAll(CredentialEntity credential, Map params) {
        params = checkParams(credential, params);
        String result = getForObject(Constants.CLIENT_API_URL_CATEGORIES, String.class, params);
        List<CategoryEntity> results = JsonpHelper.toObject(result, new TypeReference<List<CategoryEntity>>() {
        });
        return results;
    }

}
