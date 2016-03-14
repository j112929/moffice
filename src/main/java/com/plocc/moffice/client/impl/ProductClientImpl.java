/**
 * 宝龙电商
 * com.plocc.moffice.client.impl
 * CategoryClientImpl.java
 * <p/>
 * 2016-02-18
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.ProductClient;
import com.plocc.moffice.entity.AttachmentEntity;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.ProductEntity;
import com.plocc.moffice.support.Constants;


@SuppressWarnings({"unchecked", "rawtypes"})
@Service
public class ProductClientImpl extends BaseClient implements ProductClient {

    @Override
    public Integer count(CredentialEntity credential, Map params) {
         params = checkParams(credential, params);
        Integer count = getForObject(Constants.CLIENT_API_URL_PRODUCT_COUNT, Integer.class, params);
         return count;
    }

    @Override
    public ProductEntity findById(CredentialEntity credential, String productId) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", productId);
        ProductEntity productEntity = getForObject(Constants.CLIENT_API_URL_PRODUCT_ID_GET, ProductEntity.class, params);
        return productEntity;
    }

    @Override
    public void update(CredentialEntity credential,ProductEntity productEntity, String productId) {
         Map params = checkParams(credential, new HashMap());
         params.put("id", productId);
         patchForObject(Constants.CLIENT_API_URL_PRODUCT_ID_PATCH, new HashMap(productEntity), String.class, params);
    }

    @Override
    public void delete(CredentialEntity credential, String productId) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", productId);
        delete(Constants.CLIENT_API_URL_PRODUCT_ID_DELETE, String.class, params);
    }

    @Override
    public Integer add(CredentialEntity credential, ProductEntity productEntity) {
        Map params = checkParams(credential, new HashMap());
        Integer integer = postForObject(Constants.CLIENT_API_URL_PRODUCT_CREATE, new HashMap(productEntity), Integer.class, params);
        return integer;
    }

    @Override
    public Page<ProductEntity> pager(CredentialEntity credential,Map params, Pageable pager) {
         params = checkParams(credential, params);
         Integer count = count(credential, params);
         params.put("limit", pager.getPageSize());
         params.put("offset", pager.getPageNumber() * pager.getPageSize());
         if (!params.containsKey("orderby")) {
              params.put("orderby", "updateTime desc");
         }
         List<ProductEntity> results = getForObject(Constants.CLIENT_API_URL_PRODUCTS, List.class, params);
         return new PageImpl(results, pager, count);
    }

    @Override
    public List<ProductEntity> findByCategoryId(CredentialEntity credential, String categoryId, Map params) throws Exception {
        params = checkParams(credential, params);
        params.put("categoryId", categoryId);
        String result = getForObject(Constants.CLIENT_API_URL_PRODUCTS_ALL + "&filter=category.id eq '{categoryId}'", String.class, params);
        List<ProductEntity> results = JsonpHelper.toObject(result, new TypeReference<List<ProductEntity>>() {
        });
        return results;
    }
    
    
    /***************************附件************************/
    /**
     * @see com.plocc.moffice.client.ProductClient#getAttachmentsByProductId(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer)
     */
    @Override
    public List<Integer> getAttachmentsByProductId(CredentialEntity credential, String productId) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", productId);
        List<Integer> results = getForObject(Constants.CLIENT_API_URL_PRODUCT_ATTACHMENT_SELECT_LIST_GET, List.class, params);
        return results;
    }

    /**
     * @see com.plocc.moffice.client.ProductClient#addAttachment(com.plocc.moffice.entity.CredentialEntity, com.plocc.moffice.entity.AttachmentEntity)
     */
    @Override
    public Integer addAttachment(CredentialEntity credentialMaster, AttachmentEntity attachmentEntity) {
        Map params = checkParams(credentialMaster, new HashMap());
        Integer attachmentId = postForObject(Constants.CLIENT_API_URL_PRODUCT_ATTACHMENT_CREATE_POST, new HashMap(attachmentEntity), Integer.class, params);
        return attachmentId;
    }

    /**
     * @see com.plocc.moffice.client.ProductClient#deleteAttachment(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public void deleteAttachment(CredentialEntity credentialMaster, String productId, String attachmentId) {
        Map params = checkParams(credentialMaster, new HashMap());
        params.put("id", productId);
        params.put("attachmentId", attachmentId);
        delete(Constants.CLIENT_API_URL_PRODUCT_ATTACHMENT_DELETE_ID_DELETE, String.class, params);
    }

    /**
     * @see com.plocc.moffice.client.ProductClient#findAttachmentById(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public AttachmentEntity findAttachmentById(CredentialEntity credential, String productId, String attachmentId) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", productId);
        params.put("attachmentId", attachmentId);
        AttachmentEntity attachmentEntity = getForObject(Constants.CLIENT_API_URL_PRODUCT_ATTACHMENT_SELECT_ID_GET, AttachmentEntity.class, params);
        return attachmentEntity;
    }

    
    /***************************图片************************/
    /**
     * @see com.plocc.moffice.client.ProductClient#getImagesByProductId(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer)
     */
    @Override
    public List<Integer> getImagesByProductId(CredentialEntity credential, String productId) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", productId);
        List<Integer> results = getForObject(Constants.CLIENT_API_URL_PRODUCT_IMAGE_SELECT_LIST_GET, List.class, params);
        return results;
    }

    /**
     * @see com.plocc.moffice.client.ProductClient#appendImagesAndSetMainImage(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer, com.plocc.moffice.entity.AttachmentEntity)
     */
    @Override
    public Integer appendImagesAndSetMainImage(CredentialEntity credentialMaster, String productId, AttachmentEntity attachmentEntity) {
        Map params = checkParams(credentialMaster, new HashMap());
        params.put("id", productId);
        Integer attachmentId = postForObject(Constants.CLIENT_API_URL_PRODUCT_IMAGE_CREATE_POST, new HashMap(attachmentEntity), Integer.class, params);
        return attachmentId;
    }

    /**
     * @see com.plocc.moffice.client.ProductClient#getImageDetailByProductIdAndAttachmentId(com.plocc.moffice.entity.CredentialEntity, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public AttachmentEntity getImageDetailByProductIdAndAttachmentId(CredentialEntity credential, String productId, String attachmentId) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", productId);
        params.put("attachmentId", attachmentId);
        AttachmentEntity attachmentEntity = getForObject(Constants.CLIENT_API_URL_PRODUCT_IMAGE_SELECT_ID_GET, AttachmentEntity.class, params);
        return attachmentEntity;
    }

}
