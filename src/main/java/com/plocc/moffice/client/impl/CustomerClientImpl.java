package com.plocc.moffice.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plocc.framework.exception.LogicException;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.client.CustomerClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.support.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerClientImpl extends BaseClient implements CustomerClient {
    /**
     * 查询所有客户
     *
     * @param credential
     * @param params
     * @return List<CustomerEntity>
     */
    public List<CustomerEntity> findAllCustomers(CredentialEntity credential, Map params) {
        params = checkParams(credential, params);
        String results = getForObject(Constants.CLIENT_API_URL_CUSTOMER_ORDERS, String.class, params);
        List<CustomerEntity> customerList = JsonpHelper.toObject(results, new TypeReference<List<CustomerEntity>>() {
        });
        return customerList;
    }

    /**
     * 根据客户代号查询
     *
     * @param code
     * @return
     */
    public CustomerEntity findCustomerByCode(CredentialEntity credential, String code) {
        Map params = new HashMap();
        params = checkParams(credential, params);
        params.put("customerCode", code);
        List<Map> results = getForObject(Constants.CLIENT_API_URL_CUSTOMER_ORDERS + "&filter=customerCode eq '{customerCode}'", List.class, params);
        if (!results.isEmpty()) {
            return new CustomerEntity(results.get(0));
        }
        return null;
    }

    /**
     * 创建客户
     *
     * @param credential
     * @param customerEntity
     * @return
     */
    public Integer createCustomer(CredentialEntity credential, CustomerEntity customerEntity) {
        Map params = checkParams(credential, new HashMap());
        ResponseEntity<Integer> responseEntity = postForEntity(Constants.CLIENT_API_URL_CUSTOMER_ORDERS, new HashMap(customerEntity), Integer.class, params);
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return responseEntity.getBody();
        }
        throw LogicException.generateException("创建用户失败！");
    }

    /**
     * 获取客户额外信息
     *
     * @param credential
     * @return
     */
    public void getCustomFieldsMeta(CredentialEntity credential) {

    }

    /**
     * 根据Id查询客户
     *
     * @param credential
     * @param params
     * @param id
     * @return CustomerEntity
     */
    public CustomerEntity findById(CredentialEntity credential, int id, Map params) {
        params = checkParams(credential, new HashMap());
        params.put("id", id);
        CustomerEntity CustomerEntity = getForObject(Constants.CLIENT_API_URL_CUSTOMER, CustomerEntity.class, params);
        return CustomerEntity;
    }

    /**
     * 更新客户
     *
     * @param credential
     * @param customer
     * @param id
     * @return
     */
    public void update(CredentialEntity credential, CustomerEntity customer, int id) {
        Map params = checkParams(credential, new HashMap());
        params.put("id", id);
        patchForEntity(Constants.CLIENT_API_URL_CUSTOMER, customer, String.class, params);
    }

}
