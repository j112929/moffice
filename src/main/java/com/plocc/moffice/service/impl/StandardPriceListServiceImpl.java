package com.plocc.moffice.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.StringHelper;
import com.plocc.moffice.client.StandardPriceListClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.StandardPriceListEntity;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.StandardPriceListService;
import com.plocc.moffice.support.Constants;

@Service
public class StandardPriceListServiceImpl implements StandardPriceListService {
    
    @Autowired
    private StandardPriceListClient standardPriceListClient;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public StandardPriceListEntity findBySKUId(CredentialEntity credential, String skuId) throws Exception {
        StandardPriceListEntity standardPriceListEntity = null;
        // redis存取 SKU 价格信息
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String key = Constants.REDIS_BUSINESS_SKU_PRICE_MASTER + skuId;
        String standardPriceSKUStr = operations.get(key);
        // 如果redis里面没有，则放信息进入
        if (null == standardPriceSKUStr) {
            standardPriceListEntity = standardPriceListClient.findBySKUId(credential, skuId);
            standardPriceSKUStr = JsonpHelper.toString(standardPriceListEntity);
            operations.set(key, standardPriceSKUStr);
        }
        return JsonpHelper.toObject(standardPriceSKUStr, StandardPriceListEntity.class);
    }

    
    @Override
    public void updateBySKUId(CredentialEntity credentialMaster, StandardPriceListEntity standardPriceList, String skuId) throws Exception {
        standardPriceListClient.updateBySKUId(credentialMaster, standardPriceList, skuId);
    }

    
    @Override
    public void productSync(Map<String, Object> map) throws Exception {
        String type = (String) map.get("type");
        String skuId = (String) map.get("skuId");
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        StandardPriceListEntity standardPriceList = JsonpHelper.toObject(JsonpHelper.toString(map.get("result")), StandardPriceListEntity.class);
        // 新增方法
        if (StringHelper.equalsIgnoreCase("modify", type)) {
            this.updateBySKUId(credentialMaster, standardPriceList, skuId);
        }
    }
    
}
