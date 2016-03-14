/**
 * 宝龙电商
 * com.plocc.moffice.client
 * CategoryClient.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import java.util.List;
import java.util.Map;

import com.plocc.moffice.entity.CategoryEntity;
import com.plocc.moffice.entity.CredentialEntity;

/**
 * CategoryClient
 * @author chenyong
 * @version 1.0.0
 * @time 10:33
 * @email chenyong2@powerlong.com
 * @description 产品类别接口
 */
@SuppressWarnings("rawtypes")
public interface CategoryClient {
	
    /**
     * 查询所有的产品类别
     * 
     * @param credential
     * @param params
     * @return
     */
    List<CategoryEntity> findAll(CredentialEntity credential, Map params);
    
}
