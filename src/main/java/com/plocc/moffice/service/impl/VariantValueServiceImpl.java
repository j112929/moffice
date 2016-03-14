package com.plocc.moffice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.VariantValueClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.MoVariantValueRelationEntity;
import com.plocc.moffice.entity.VariantInfoEntity;
import com.plocc.moffice.entity.VariantValueInfoEntity;
import com.plocc.moffice.repository.MoVariantValueRelationRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.VariantService;
import com.plocc.moffice.service.VariantValueService;

@Service
public class VariantValueServiceImpl implements VariantValueService {

    @Autowired
    private VariantValueClient variantValueClient;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private VariantService variantService;
    @Autowired
    private MoVariantValueRelationRepository moVariantValueRelationRepository;
    
    @Override
    public List<VariantValueInfoEntity> getAll(CredentialEntity credential, Map<String, Object> params) throws Exception {
        return variantValueClient.getAll(credential, params);
    }

    @Override
    public VariantValueInfoEntity getVariantValueByCode(CredentialEntity credential, String variantValueCode) throws Exception {
        return variantValueClient.getVariantValueByCode(credential, variantValueCode);
    }

    @Override
    public Integer add(VariantValueInfoEntity variantValueInfoEntity) throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        return variantValueClient.add(credentialMaster, variantValueInfoEntity);
    }

    @Override
    public VariantValueInfoEntity getById(CredentialEntity credential, String variantValueId) throws Exception {
        return variantValueClient.getById(credential, variantValueId);
    }

    @Override
    public void update(CredentialEntity credentialMaster, VariantValueInfoEntity variantValueInfoEntity) throws Exception {
        variantValueClient.update(credentialMaster, variantValueInfoEntity);
    }

    @Override
    public List<VariantValueInfoEntity> getVariantValueListNew(String appId, List<VariantValueInfoEntity> listVariantValues) throws Exception {
        List<VariantValueInfoEntity> listVariantValuesNew = new ArrayList<VariantValueInfoEntity>();
        MoVariantValueRelationEntity moVariantValueRelation = null;
        VariantValueInfoEntity variantValueInfoNew = null;
        for (VariantValueInfoEntity variantValueInfo : listVariantValues) {
            moVariantValueRelation = new MoVariantValueRelationEntity();
            moVariantValueRelation = moVariantValueRelationRepository.findByAppIdAndVariantValueChildrenId(appId, variantValueInfo.getId());
            // 关系表中存在关系
            if (null != moVariantValueRelation) {
                variantValueInfoNew = new VariantValueInfoEntity();
                variantValueInfoNew.setId(moVariantValueRelation.getVariantValueMasterId());
                variantValueInfoNew.setCode(moVariantValueRelation.getVariantValueMasterCode());
                variantValueInfoNew.setValue(moVariantValueRelation.getVariantValueMasterValue());
                listVariantValuesNew.add(variantValueInfoNew);
            } 
            // 新增属性值
            else {
                variantValueInfo.remove("id");
                variantValueInfo.remove("variant");
                variantValueInfo.remove("creationTime");
                variantValueInfo.remove("updateTime");
                VariantInfoEntity variantInfoEntity = variantService.findVariantByNameFromDB(appId, variantValueInfo.getVariant().getName());
                variantValueInfo.setVariant(variantInfoEntity);
                Integer variantValue = this.add(variantValueInfo);
                variantValueInfo.setId(String.valueOf(variantValue));
                listVariantValuesNew.add(variantValueInfo);
            }
        }
        return listVariantValuesNew;
    }

}
