package com.plocc.moffice.service.handle;

import org.springframework.beans.factory.annotation.Autowired;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.service.ProductService;
import com.plocc.moffice.service.TaskConsumerHandle;

/**
 *  产品新增handle
 * @author Administrator
 *
 * @param
 */
public class TaskProductDropConsumerHandleImpl implements TaskConsumerHandle {
    
    @Autowired
    private ProductService productService;

    @Override
    public void handle(String appId, Object result) throws Exception {
        ProductEntity productEntity = JsonpHelper.toObject(JsonpHelper.toString(result), ProductEntity.class);
        productService.delete(appId, productEntity.getId());
    }
    
}
