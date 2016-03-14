package com.plocc.moffice.repository;

import org.springframework.data.repository.CrudRepository;

import com.plocc.moffice.entity.MoProductSKURelationEntity;

public interface MoProductSKURelationRepository extends CrudRepository<MoProductSKURelationEntity, Integer> {
    
    /**
     * 根据 appId，子账号产品productChildrenSKUId 查询 主子帐号产品SKU对应关系
     * 
     * @param appId
     * @param productChildrenSKUId
     * @return
     */
    public MoProductSKURelationEntity findByAppIdAndProductChildrenSKUId(String appId, String productChildrenSKUId);
    
    /**
     * 根据 productMasterSKUId查询 主子帐号产品SKU对应关系
     * 
     * @param productMasterSKUId
     * @return
     */
    public MoProductSKURelationEntity findByProductMasterSKUId(String productMasterSKUId);
    
}
