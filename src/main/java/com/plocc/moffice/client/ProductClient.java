/**
 * 宝龙电商
 * com.plocc.moffice.client
 * ProductClient.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.plocc.moffice.entity.AttachmentEntity;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.ProductEntity;

/**
 * ProductClient
 * @author chenyong
 * @version 1.0.0
 * @time 10:33
 * @email chenyong2@powerlong.com
 * @description 产品接口
 */
public interface ProductClient {
    
    /**
     * 商品分页
     * @param credential
     * @param pager
     * @param params
     */
    Page<ProductEntity> pager(CredentialEntity credential, Map<String, Object> params, Pageable pager);
    
    /**
     * 添加商品
     * @param credential
     * @param productEntity
     * @param id
     */
    Integer add(CredentialEntity credential, ProductEntity productEntity);
    
    /**
     * 查询产品库存数量
     * @param credential
     * @param params 查询条件
     * @return
     */
    Integer count(CredentialEntity credential, Map<String, Object> params);
    
    /**
     * 根据ID删除商品
     * @param credential
     * @param productEntity
     * @param productId
     */
    void delete(CredentialEntity credential, String productId);
    
    /**
     * 根据ID查询商品
     * @param credential
     * @param productId
     * @return
     */
    ProductEntity findById(CredentialEntity credential, String productId);
    
    /**
     * 根据ID更新商品
     * @param credential
     * @param productEntity
     * @param productId
     */
    void update(CredentialEntity credential, ProductEntity productEntity, String productId);

    
    /************************************** 附件 **************************************/
    /**
     * 根据产品id获取附件ids
     * 
     * @param credential
     * @param productId
     */
    List<Integer> getAttachmentsByProductId(CredentialEntity credential, String productId);
    
    /**
     * 添加商品附件
     * 
     * @param credentialMaster
     * @param attachmentEntity
     * @return
     * @exception
     */
    Integer addAttachment(CredentialEntity credentialMaster, AttachmentEntity attachmentEntity);
    
    /**
     * 根据产品id和 附件id删除附件
     * 
     * @param credential
     * @param productId
     * @param attachmentId
     * @exception
     */
    void deleteAttachment(CredentialEntity credentialMaster, String productId, String attachmentId);
    
    /**
     * 根据产品id和附件id查询 附件详情
     * 
     * @param credential
     * @param productId
     * @param attachmentId
     * @return
     * @exception
     */
    AttachmentEntity findAttachmentById(CredentialEntity credential, String productId, String attachmentId);
    
    
    /************************************** 图片 **************************************/
    /**
     * 获取图片ids
     * 
     * @param credential
     * @param productId
     * @return
     * @exception
     */
    List<Integer> getImagesByProductId(CredentialEntity credential, String productId);
    
    /**
     * 设置主图片
     * 
     * @param credential
     * @param productId
     * @param attachmentEntity
     * @return
     * @exception
     */
    Integer appendImagesAndSetMainImage(CredentialEntity credentialMaster, String productId, AttachmentEntity attachmentEntity);
    
    /**
     * 获取图片附件详情
     * 
     * @param credential
     * @param productId
     * @param attachmentId
     * @return
     * @exception
     */
    AttachmentEntity getImageDetailByProductIdAndAttachmentId(CredentialEntity credential, String productId, String attachmentId);
    
    /**
     * 根据产品分类查询产品
     * 
     * @param credential
     * @param categoryId
     * @param params
     * @return
     * @throws Exception
     */
    public List<ProductEntity> findByCategoryId(CredentialEntity credential, String categoryId, Map<String, Object> params) throws Exception;
    
}
