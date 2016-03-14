package com.plocc.moffice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.VariantClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.MoVariantRelationEntity;
import com.plocc.moffice.entity.VariantInfoEntity;
import com.plocc.moffice.repository.MoVariantRelationRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.VariantService;

/**
 * 
 * @author xing_biao
 * 2016年2月23日
 * @version 1.0.0
 *
 */
@Service
public class VariantServiceImpl implements VariantService {

    @Autowired
    private VariantClient variantClient;
    @Autowired
    private CredentialService credentialService;
    
    @Autowired
    private MoVariantRelationRepository moVariantRelationRepository;
    
    @Override
    public List<VariantInfoEntity> getAll(CredentialEntity credential, Map<String, Object> params) throws Exception {
        return variantClient.getAll(credential, params);
    }

    @Override
    public VariantInfoEntity findVariantByName(CredentialEntity credential, String name) throws Exception {
        return variantClient.getVariantByName(credential, name);
    }

    @Override
    public Integer add(CredentialEntity credentialMaster, VariantInfoEntity variantInfoEntity) throws Exception {
        return variantClient.add(credentialMaster, variantInfoEntity);
    }
    
    @Override
    public VariantInfoEntity getById(CredentialEntity credential, String variantId) throws Exception {
        return variantClient.getById(credential, variantId);
    }

    @Override
    public void update(CredentialEntity credentialMaster, VariantInfoEntity variantInfoEntity) throws Exception {
        variantClient.update(credentialMaster, variantInfoEntity);
    }

    @Override
    public VariantInfoEntity findVariantByNameFromDB(String appId, String variantName) throws Exception {
        MoVariantRelationEntity moVariantRelation = moVariantRelationRepository.findByAppIdAndVariantChildrenName(appId, variantName);
        VariantInfoEntity variantInfoEntity = null;
        if (null != moVariantRelation) {
            variantInfoEntity = new VariantInfoEntity();
            variantInfoEntity.setId(moVariantRelation.getVariantMasterId());
            variantInfoEntity.setName(moVariantRelation.getVariantMasterName());
        } 
        else {
            CredentialEntity credentialMaster = credentialService.getMastCredential();
            variantInfoEntity = new VariantInfoEntity();
            variantInfoEntity.setName(variantName);
            Integer variantId = this.add(credentialMaster, variantInfoEntity);
            variantInfoEntity.setId(String.valueOf(variantId));
        }
        return variantInfoEntity;
    }

}
