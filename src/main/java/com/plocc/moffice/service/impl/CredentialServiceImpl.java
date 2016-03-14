/**
 * 宝龙电商
 * com.plocc.moffice.service.impl
 * CredentialServiceImpl.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service.impl;

import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.DateHelper;
import com.plocc.framework.support.ProfileHelper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.moffice.client.CredentialClient;
import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.CustomerEntity;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.CustomerService;
import com.plocc.moffice.service.VendorsService;
import com.plocc.moffice.support.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CredentialServiceImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:02
 * @email zhanggj@powerlong.com
 * @description 凭证操作
 */
@Service
@Scope("singleton")
public class CredentialServiceImpl implements CredentialService {

    private static final Logger logger = LoggerFactory.getLogger(CredentialServiceImpl.class);
    @Autowired
    private CredentialClient credentialClient;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    @Autowired
    @Qualifier("mofficeObjectRedisTemplate")
    private RedisOperations redisOperations;
    @Resource(name = "credentials")
    private List<CredentialEntity> credentials;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VendorsService vendorsService;

    /**
     * 每半小时 刷新一次 AccessToken
     */
    @Scheduled(fixedDelay = 1000 * 60)
    private void refreshTokenTask() {
        if (!ProfileHelper.isDev() && !mofficeRedisRepository.getServerLock(String.format(Constants.REDIS_LOCK_KEY, "credential"), DateHelper.toMillis("8h"))) {
            // 加锁成功的服务器才能执行以下逻辑
            // 启用分布式锁、特殊任务防止任务重复执行、锁的有效期为8小时。8小时后 如果加锁服务器宕机不影响其他服务器加锁
            return;
        }
        for (CredentialEntity credential : credentials) {
            try {
                // 刷新 凭证
                credential = refreshAccessTokenByCredential(credential);
                // 同步 客户账号、排除汇总账号
                if (ValidHelper.isEmpty(credential.getCustomer()) && !credential.getMaster()) {
                    try {
                        CustomerEntity customerEntity = customerService.findCustomerByCode(credential, credential.getAppId());
                        if (null == customerEntity) {
                            Integer customer = customerService.createCustomer(credential);
                            credential.setCustomer(DataHelper.getString(customer));
                        } else {
                            credential.setCustomer(DataHelper.getString(customerEntity.getId()));
                        }
                        // 更新Redis
                        updateCredentialCustomer(credential);
                        vendorsService.add(credential);
                    } catch (Exception ex) {
                        logger.error(String.format("同步客户账号失败：%s appId -> %s", credential.getTitle(), credential.getAppId()), ex);
                    }
                }
            } catch (Exception ex) {
                // 防止影响 task 后续执行
                logger.error("刷新凭证失败：", ex);
            }
        }
    }

    /**
     * 根据配置信息刷新凭证
     *
     * @param credential
     * @return
     */
    public CredentialEntity refreshAccessTokenByCredential(CredentialEntity credential) {
        String accessToken = credentialClient.getAccessToken(credential);
        BoundValueOperations<String, CredentialEntity> operations = redisOperations.boundValueOps(String.format(Constants.REDIS_CREDENTIALS_PREFIX_KEY, credential.getAppId()));
        CredentialEntity credentialEntity = operations.get();
        if (null == credentialEntity) {
            credential.setAccessToken(accessToken);
            operations.set(credential);
        } else {
            credential.setAccessToken(accessToken);
            credentialEntity.setAccessToken(accessToken);
            operations.set(credential);
        }
        return credentialEntity;
    }

    /**
     * 获取所有 凭证信息
     * 凭证信息只有 一台服务器在刷新、非刷新服务器本地是没有 有效access_token凭证、所以要从 redis中取。
     *
     * @return
     */
    public List<CredentialEntity> getCredentials() {
        Set<String> keys = redisOperations.keys(String.format(Constants.REDIS_CREDENTIALS_PREFIX_KEY, "*"));
        List<CredentialEntity> results = new ArrayList();
        for (String key : keys) {
            BoundValueOperations<String, CredentialEntity> valueOperations = redisOperations.boundValueOps(key);
            results.add(valueOperations.get());
        }
        return results;
    }

    public CredentialEntity getMastCredential() {
        for (CredentialEntity credential : credentials) {
            if (credential.getMaster()) {
                return getCredentialByAppId(credential.getAppId());
            }
        }
        return null;
    }

    /**
     * 根据 AppId 刷新 凭证
     *
     * @param appId
     * @return
     */
    public CredentialEntity refreshAccessTokenByAppId(String appId) {
        return refreshAccessTokenByCredential(getCredentialByConfigAppId(appId));
    }

    /**
     * 更新 凭证对应客户编号
     *
     * @param credential
     */
    private void updateCredentialCustomer(CredentialEntity credential) {
        BoundValueOperations<String, CredentialEntity> operations = redisOperations.boundValueOps(String.format(Constants.REDIS_CREDENTIALS_PREFIX_KEY, credential.getAppId()));
        operations.set(credential);
    }

    /**
     * 读取 配置文件 凭证信息
     *
     * @param appId
     * @return
     */
    private CredentialEntity getCredentialByConfigAppId(String appId) {
        for (CredentialEntity credential : credentials) {
            if (credential.equals("appId", appId)) {
                return credential;
            }
        }
        return null;
    }

    /**
     * 根据 AppId获取 凭证
     *
     * @param appId
     * @return
     */
    @SuppressWarnings("unchecked")
    public CredentialEntity getCredentialByAppId(String appId) {
        BoundValueOperations<String, CredentialEntity> operations = null;
        try {
            operations = redisOperations.boundValueOps(String.format(Constants.REDIS_CREDENTIALS_PREFIX_KEY, appId));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // @Todo review
        CredentialEntity credentialEntity = operations.get();
        if (null == credentialEntity) {
            credentialEntity = refreshAccessTokenByAppId(appId);
        }
        return credentialEntity;
    }

}
