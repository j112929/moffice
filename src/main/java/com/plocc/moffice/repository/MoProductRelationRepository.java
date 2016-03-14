package com.plocc.moffice.repository;

import org.springframework.data.repository.CrudRepository;

import com.plocc.moffice.entity.MoProductRelationEntity;

public interface MoProductRelationRepository extends CrudRepository<MoProductRelationEntity, Integer> {
    
    /**
     * 根据 appId，子账号产品idproductChildrenId 查询 主子帐号产品对应关系
     * 
     * @param appId
     * @param productChildrenId
     * @return
     */
    public MoProductRelationEntity findByAppIdAndProductChildrenId(String appId, String productChildrenId);
    
    /**
     * 根据 productMasterId查询 主子帐号产品对应关系
     * 
     * @param productMasterId
     * @return
     */
    public MoProductRelationEntity findByProductMasterId(String productMasterId);
    
    
    
}
