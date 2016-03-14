package com.plocc.moffice.service;

import com.plocc.moffice.entity.CredentialEntity;

import java.util.List;

/**
 * Created by xiaojiang on 2016-02-17.
 */
public interface CredentialService {

    /**
     * 获取全部 凭证
     *
     * @return
     */
    List<CredentialEntity> getCredentials();

    /**
     * 获取汇总账号凭证
     *
     * @return
     */
    public CredentialEntity getMastCredential();

    /**
     * 刷新并返回凭证
     *
     * @param appId
     * @return
     */
    CredentialEntity refreshAccessTokenByAppId(String appId);

    /**
     * 刷新并返回凭证
     *
     * @param credential
     * @return
     */
    CredentialEntity refreshAccessTokenByCredential(CredentialEntity credential);

    /**
     * 获取凭证
     *
     * @param appId
     * @return
     */
    CredentialEntity getCredentialByAppId(String appId);

}
