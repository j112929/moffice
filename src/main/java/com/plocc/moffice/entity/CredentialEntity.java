/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * StoreCredentialEntity.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;
import com.plocc.framework.support.Constants;

import javax.persistence.Entity;

/**
 * StoreCredentialEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:22
 * @email zhanggj@powerlong.com
 * @description 凭证
 */
@Entity(name = "tb_mo_credential")
public class CredentialEntity extends Pojo {
    // 应用 ID
    private String appId;
    // 应用 密钥
    private String appSecret;
    // 应用 刷新令牌
    private String refreshToken;
    // 应用 有效令牌
    private String accessToken;
    // 店铺名称
    private String title;
    // 店铺描述
    private String description;
    // 是否汇总账号 Constants.YES/Constants.NO
    private boolean master;
    // 汇总账号对应 子账号的客户编号
    private String customer;

    public CredentialEntity() {
    }


    public String getAppId() {
        return getString("appId");
    }

    public void setAppId(String appId) {
        set("appId", appId);
    }

    public String getAppSecret() {
        return getString("appSecret");
    }

    public void setAppSecret(String appSecret) {
        set("appSecret", appSecret);
    }

    public String getRefreshToken() {
        return getString("refreshToken");
    }

    public void setRefreshToken(String refreshToken) {
        set("refreshToken", refreshToken);
    }

    public String getAccessToken() {
        return getString("accessToken");
    }

    public void setAccessToken(String accessToken) {
        set("accessToken", accessToken);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        set("title", title);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        set("description", description);
    }

    public boolean getMaster() {
        return getBoolean("master");
    }

    public void setMaster(boolean master) {
        set("master", master);
    }

    public String getCustomer() {
        return getString("customer");
    }

    public void setCustomer(String customer) {
        set("customer", customer);
    }
}
