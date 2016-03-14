/**
 * 宝龙电商
 * com.plocc.moffice.client
 * ProductClient.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.ProductEntity;

/**
 * ProductService
 * @author chenyong
 * @version 1.0.0
 * @time 18:33
 * @email chenyong2@powerlong.com
 * @description 产品接口
 */
public interface ProductService {
	
	/**
     * 查询产品库存数量
     * @param credential
     * @param params 查询条件
     * @return
     */
    public Integer count(CredentialEntity credential, Map<String, Object> params) throws Exception;
    
    /**
     * 根据ID查询商品
     * 
     * @param productId
     * @return
     */
    public ProductEntity findById(String productId) throws Exception;
    
    /**
     * 根据ID更新商品
     * 
     * @param appId
     * @param productEntity
     * @throws Exception
     * @exception
     */
    public void update(String appId, ProductEntity productEntity) throws Exception;
    
    /**
     * 根据ID删除商品
     * @param credential
     * @param productEntity
     * @param id
     */
    public void delete(String appId, String productId) throws Exception;
    
    /**
     * 添加商品
     * 
     * @param appId
     * @param productEntity
     * @throws Exception
     */
    public void add(String appId, ProductEntity productEntity) throws Exception;
    
    /**
     * 商品分页
     * @param credential
     * @param pager
     * @param params
     */
    public Page<ProductEntity> pager(CredentialEntity credential, Map<String, Object> params, Pageable pager) throws Exception;
    
    /**
     * 按照产品类别查询产品
     * 
     * @param categoryId
     * @return
     * @throws Exception
     */
    public List<ProductEntity> findByCategoryId(String categoryId) throws Exception;

}
