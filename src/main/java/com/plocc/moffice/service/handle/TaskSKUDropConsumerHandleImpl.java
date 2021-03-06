package com.plocc.moffice.service.handle;

import org.springframework.beans.factory.annotation.Autowired;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.entity.SKUInfoEntity;
import com.plocc.moffice.service.SKUsService;
import com.plocc.moffice.service.TaskConsumerHandle;

/**
 * SKU删除handle
 * @author Administrator
 *
 */
public class TaskSKUDropConsumerHandleImpl implements TaskConsumerHandle {
    
    @Autowired
    private SKUsService skusService;

    @Override
    public void handle(String appId, Object result) throws Exception {
        SKUInfoEntity skuInfoEntity = JsonpHelper.toObject(JsonpHelper.toString(result), SKUInfoEntity.class);
        skusService.delete(appId, skuInfoEntity.getId());
    }
    
}
