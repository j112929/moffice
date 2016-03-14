package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.WarehouseClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.WarehouseInfoEntity;
import com.plocc.moffice.support.Constants;


@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class WarehouseClientImpl extends BaseClient implements WarehouseClient {

    @Override
    public List<WarehouseInfoEntity> findAll(CredentialEntity credential) {
        Map<String, Object> params = checkParams(credential, new HashMap<String, Object>());
        List results = getForObject(Constants.CLIENT_API_URL_WAREHOUSES_GET, List.class, params);
        List<WarehouseInfoEntity> list = JsonpHelper.toObject(JsonpHelper.toString(results), new TypeReference<List<WarehouseInfoEntity>>() {});
        return list;
    }

    @Override
    public WarehouseInfoEntity findById(CredentialEntity credential, String warehouseId) throws Exception {
        Map params = checkParams(credential, new HashMap());
        params.put("id", warehouseId);
        WarehouseInfoEntity warehouseInfoEntity = getForObject(Constants.CLIENT_API_URL_WAREHOUSES_ID_GET, WarehouseInfoEntity.class, params);
        return warehouseInfoEntity;
    }
    
    @Override
    public WarehouseInfoEntity findByCode(CredentialEntity credential, String warehouseCode) {
        Map params = checkParams(credential, new HashMap());
        params.put("code", warehouseCode);
        WarehouseInfoEntity warehouseInfoEntity = getForObject(Constants.CLIENT_API_URL_WAREHOUSES_ID_GET + "&filter=code eq '{code}'", WarehouseInfoEntity.class, params);
        return warehouseInfoEntity;
    }

}
