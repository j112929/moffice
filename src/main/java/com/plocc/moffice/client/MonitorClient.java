/**
 * 宝龙电商
 * com.plocc.moffice.service
 * OrderService.java
 * <p/>
 * 2016-02-15
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import com.plocc.moffice.entity.CredentialEntity;

import java.util.List;
import java.util.Map;

/**
 * OrderRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:14
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface MonitorClient {

    <T> List<T> pager(CredentialEntity credential, Map params, Class<T> tClass, String url) throws Exception;

    String count(CredentialEntity credential, String url) throws Exception;

    String selectMaxId(CredentialEntity credential, String url) throws Exception;

    String selectLastUpdate(CredentialEntity credential, String url) throws Exception;

    <T> T selectById(CredentialEntity credential, Long id, Class<T> tClass, String url) throws Exception;
}
