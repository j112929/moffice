package com.plocc.moffice.repository;

import org.springframework.data.repository.CrudRepository;

import com.plocc.moffice.entity.MoVariantRelationEntity;

public interface MoVariantRelationRepository extends CrudRepository<MoVariantRelationEntity, Integer> {
    
    /**
     * 根据 appId，子账号属性类型variantChildrenId 查询 主子帐号属性类型对应关系
     * 
     * @param appId
     * @param variantChildrenId
     * @return
     */
    public MoVariantRelationEntity findByAppIdAndVariantChildrenId(String appId, String variantChildrenId);
    
    /**
     * 根据 variantMasterId查询 主子帐号属性类型对应关系
     * 
     * @param variantMasterId
     * @return
     */
    public MoVariantRelationEntity findByVariantMasterId(String variantMasterId);
    
    /**
     * 根据 variantChildrenName查询 主子帐号属性类型对应关系
     * 
     * @param variantChildrenName
     * @return
     */
    public MoVariantRelationEntity findByAppIdAndVariantChildrenName(String appId, String variantChildrenName);
    
}
