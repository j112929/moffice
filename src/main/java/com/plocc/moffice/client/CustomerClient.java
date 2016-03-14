package com.plocc.moffice.client;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.CustomerEntity;

import java.util.List;
import java.util.Map;

public interface CustomerClient {
    /**
     * 查询所有客户
     *
     * @param credential
     * @param params
     * @return List<CustomerEntity>
     */
    List<CustomerEntity> findAllCustomers(CredentialEntity credential, Map params);

    /**
     * 根据客户代号查询
     *
     * @param code
     * @return
     */
    CustomerEntity findCustomerByCode(CredentialEntity credential, String code);


    /**
     * 创建客户
     *
     * @param credential
     * @param customer
     * @return
     */
    Integer createCustomer(CredentialEntity credential, CustomerEntity customer);

    /**
     * 获取客户额外信息
     *
     * @param credential
     * @return
     */
    void getCustomFieldsMeta(CredentialEntity credential);

    /**
     * 根据Id查询客户
     *
     * @param credential
     * @param params
     * @param id
     * @return CustomerEntity
     */
    CustomerEntity findById(CredentialEntity credential, int id, Map params);

    /**
     * 更新客户
     *
     * @param credential
     * @param customer
     * @param id
     * @return
     */
    void update(CredentialEntity credential, CustomerEntity customer, int id);

}
