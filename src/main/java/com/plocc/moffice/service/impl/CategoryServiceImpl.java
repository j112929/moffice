package com.plocc.moffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plocc.moffice.client.CategoryClient;
import com.plocc.moffice.entity.CategoryEntity;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.service.CategoryService;
import com.plocc.moffice.service.CredentialService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private CredentialService credentialService;
    
    @Override
    public List<CategoryEntity> findAll() throws Exception {
        CredentialEntity credentialMaster = credentialService.getMastCredential();
        return categoryClient.findAll(credentialMaster, null);
    }

}
