package com.plocc.moffice.service.handle;

import org.springframework.beans.factory.annotation.Autowired;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.service.ProductService;
import com.plocc.moffice.service.TaskConsumerHandle;

/**
 *  产品更新handle
 * @author Administrator
 *
 * @param
 */
public class TaskProductModifyConsumerHandleImpl implements TaskConsumerHandle {
    
    @Autowired
    private ProductService productService;
    
    public void handle(String appId, Object result) throws Exception {
        ProductEntity productEntity = JsonpHelper.toObject(JsonpHelper.toString(result), ProductEntity.class);
        productService.update(appId, productEntity);
    }

}
