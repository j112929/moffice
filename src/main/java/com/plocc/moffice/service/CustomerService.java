package com.plocc.moffice.service;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.CustomerEntity;

public interface CustomerService {
    /**
     * 创建账号
     *
     * @param credential
     * @param customerEntity
     * @return
     */
    Integer createCustomer(CredentialEntity credential, CustomerEntity customerEntity);

    /**
     * 为子账号创建 汇总账号对应账号
     *
     * @param credential
     * @return
     */
    Integer createCustomer(CredentialEntity credential);

    /**
     * 根据客户代号查询
     *
     * @param code
     * @return
     */
    CustomerEntity findCustomerByCode(CredentialEntity credential, String code);

}
