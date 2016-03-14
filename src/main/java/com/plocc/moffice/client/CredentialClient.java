/**
 * 宝龙电商
 * com.plocc.moffice.client
 * CredentialClient.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import com.plocc.moffice.entity.CredentialEntity;

/**
 * CredentialClient
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:16
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface CredentialClient {
    public String getAccessToken(CredentialEntity credentialEntity);
}
