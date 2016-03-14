package com.plocc.moffice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.WarehouseClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.WarehouseInfoEntity;
import com.plocc.moffice.service.WarehouseService;
import com.plocc.moffice.support.Constants;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);
    
    @Autowired
    private WarehouseClient warehouseClient;
    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    @Qualifier("mofficeObjectRedisTemplate")
    private RedisOperations redisOperations;

    @Override
    public WarehouseInfoEntity findByCode(CredentialEntity credential, String warehouseCode) throws Exception{
        WarehouseInfoEntity warehouseInfoEntity = null;
        try {
            // 仓库信息放到redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String key = Constants.MOFFICE_REDIS_WAREHOUSE + warehouseCode;
            String warehouseStr = operations.get(key);
            // 如果redis里面没有，则放信息进入
            if (null == warehouseStr) {
                warehouseInfoEntity = warehouseClient.findByCode(credential, warehouseCode);
                operations.set(key, JsonpHelper.toString(warehouseInfoEntity));
            } else {
                warehouseInfoEntity = JsonpHelper.toObject(warehouseStr, WarehouseInfoEntity.class);
            }
        }
        catch (Exception e) {
            LOGGER.error("findByCode出错：credential=" + credential + ", warehouseCode=" + warehouseCode, e);
        }
        return warehouseInfoEntity;
    }
    
    @Override
    public WarehouseInfoEntity findById(CredentialEntity credential, String warehouseId) throws Exception {
        return warehouseClient.findById(credential, warehouseId);
    }

}
