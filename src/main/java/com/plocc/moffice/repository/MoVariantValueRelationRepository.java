package com.plocc.moffice.repository;

import org.springframework.data.repository.CrudRepository;

import com.plocc.moffice.entity.MoVariantValueRelationEntity;

public interface MoVariantValueRelationRepository extends CrudRepository<MoVariantValueRelationEntity, Integer> {
    
    /**
     * 根据 appId，子账号产品idproductChildrenId 查询 主子帐号产品对应关系
     * 
     * @param appId
     * @param variantValueChildrenId
     * @return
     */
    public MoVariantValueRelationEntity findByAppIdAndVariantValueChildrenId(String appId, String variantValueChildrenId);
    
    /**
     * 根据 productMasterId查询 主子帐号产品对应关系
     * 
     * @param variantValueMasterId
     * @return
     */
    public MoVariantValueRelationEntity findByVariantValueMasterId(String variantValueMasterId);
    
    
    
}
