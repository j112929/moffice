package com.plocc.moffice.service.impl;

import com.plocc.moffice.client.CustomerClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerClient customerClient;
    // @Todo review
    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 创建账号
     *
     * @param credential
     * @param customerEntity
     * @return
     */
    public Integer createCustomer(CredentialEntity credential, CustomerEntity customerEntity) {
        return customerClient.createCustomer(credential, customerEntity);
    }

    /**
     * 根据客户代号查询
     *
     * @param code
     * @return
     */
    public CustomerEntity findCustomerByCode(CredentialEntity credential, String code) {
        return customerClient.findCustomerByCode(credential, code);
    }

    /**
     * 为子账号创建 汇总账号对应账号
     *
     * @param credential
     */
    public Integer createCustomer(CredentialEntity credential) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerCode(credential.getAppId());
        customerEntity.setCustomerName(credential.getTitle());
        customerEntity.setCustomerType("INDIVIDUAL_CUSTOMER");
        customerEntity.setStage("CUSTOMER");
        customerEntity.setStatus("ACTIVE");
        customerEntity.setMarketingStatus("SUBSCRIBED");
        return createCustomer(credential, customerEntity);
    }
}
