/**
 * 宝龙电商
 * com.plocc.moffice.support.spring
 * RestTemplate.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client.impl;

import com.plocc.moffice.entity.CredentialEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplate
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:39
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */

public class BaseClient extends com.plocc.framework.rest.RestTemplate {

    /**
     * 完善参数
     *
     * @param credentialEntity
     * @param params
     * @return
     */
    public Map checkParams(CredentialEntity credentialEntity, Map params) {
        if (null == params) {
            params = new HashMap();
        }
        params.put("access_token", credentialEntity.getAccessToken());
        return params;
    }

}
