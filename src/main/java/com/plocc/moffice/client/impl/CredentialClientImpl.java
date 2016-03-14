/**
 * 宝龙电商
 * com.plocc.moffice.client.impl
 * CredentialClientImpl.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client.impl;

import com.plocc.framework.entity.Model;
import com.plocc.moffice.client.CredentialClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.support.Constants;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * CredentialClientImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:17
 * @email zhanggj@powerlong.com
 * @description 凭证操作
 */
@Service
public class CredentialClientImpl extends BaseClient implements CredentialClient {
    public String getAccessToken(CredentialEntity credentialEntity) {
        Map params = new HashMap();
        params.put("client_id", credentialEntity.getAppId());
        params.put("client_secret", credentialEntity.getAppSecret());
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", credentialEntity.getRefreshToken());
        Model result = getForObject(Constants.CLIENT_ACCESS_TOKEN, Model.class, params);
        return result.getString("access_token");
    }

}
