/**
 * 宝龙电商
 * com.plocc.moffice.service.impl
 * ProductServiceImpl.java
 * <p/>
 * 2016-02-15
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service.impl;

import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.Md5Helper;
import com.plocc.framework.support.StringHelper;
import com.plocc.moffice.client.MonitorClient;
import com.plocc.moffice.entity.*;
import com.plocc.moffice.repository.MoProductIdRepository;
import com.plocc.moffice.repository.MoSKURepository;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.MonitorRefreshService;
import com.plocc.moffice.support.ChannelEnum;
import com.plocc.moffice.support.Constants;
import com.plocc.moffice.support.TaskTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description monitor 数据刷新
 * Created by yanghuan on 2016-02-21.
 */
@Service
public class MonitorRefreshServiceImpl implements MonitorRefreshService {
    private static final Logger logger = LoggerFactory.getLogger(MonitorRefreshServiceImpl.class);
    @Autowired
    private MonitorClient monitorClient;
    @Autowired
    private MoProductIdRepository moProductIdRepository;
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    @Autowired
    private MoSKURepository moSKURepository;
    @Autowired
    private CredentialService credentialService;

    public static final Integer PAGE_SIZE = 1000;
    public static final Integer DEFAULT_PAGE = 1;

    //获取列表
    public <T> List<T> getList(CredentialEntity credential, Map params, String url, Class<T> tClass) throws Exception {
        Integer pageSize = PAGE_SIZE;
        Integer page = DEFAULT_PAGE;
        params.put("limit", pageSize);
        params.put("offset", (page - 1) * pageSize);
        List<T> idList = new ArrayList<T>();
        while (true) {
            List<T> list = getPageList(credential, params, url, tClass);
            idList.addAll(list);
            if (list.size() == pageSize) {
                page++;
                params.put("offset", (page - 1) * pageSize);
            } else {
                break;
            }
        }
        return idList;
    }

    //查询分页
    private <T> List<T> getPageList(CredentialEntity credential, Map params, String url, Class<T> tClass) throws Exception {
        List<T> result = new ArrayList<T>();
        try {
            result = monitorClient.pager(credential, params, tClass, url);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                //401则刷新认证重试
                credential = credentialService.refreshAccessTokenByAppId(credential.getAppId());
                getPageList(credential, params, url, tClass);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                //429则sleep3秒再重试
                Thread.sleep(Constants.THREAD_SLEEP_3000);
                getPageList(credential, params, url, tClass);
            } else {
                throw e;
            }
        }
        return result;
    }

    public <T> T selectById(CredentialEntity credential, Long id, Class<T> tClass, String url) throws Exception {
        T result = null;
        try {
            result = monitorClient.selectById(credential, id, tClass, url);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                //401则刷新认证重试
                credential = credentialService.refreshAccessTokenByAppId(credential.getAppId());
                selectById(credential, id, tClass, url);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                //429则sleep3秒再重试
                Thread.sleep(Constants.THREAD_SLEEP_3000);
                selectById(credential, id, tClass, url);
            } else {
                throw e;
            }
        }
        return result;
    }

    public String getCount(CredentialEntity credential, String url) throws Exception {
        String count = null;
        try {
            count = monitorClient.count(credential, url);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                //401则刷新认证重试
                credential = credentialService.refreshAccessTokenByAppId(credential.getAppId());
                getCount(credential, url);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                //429则sleep3秒再重试
                Thread.sleep(Constants.THREAD_SLEEP_3000);
                getCount(credential, url);
            } else {
                throw e;
            }
        }
        return count;
    }

    public String getMaxId(CredentialEntity credential, String url) throws Exception {
        String maxId = null;
        try {
            maxId = monitorClient.selectMaxId(credential, url);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                //401则刷新认证重试
                credential = credentialService.refreshAccessTokenByAppId(credential.getAppId());
                getMaxId(credential, url);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                //429则sleep3秒再重试
                Thread.sleep(Constants.THREAD_SLEEP_3000);
                getMaxId(credential, url);
            } else {
                throw e;
            }
        }
        return maxId;
    }

    public String getLastUpdate(CredentialEntity credential, String url) throws Exception {
        String lastUpdate = null;
        try {
            lastUpdate = monitorClient.selectLastUpdate(credential, url);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                //401则刷新认证重试
                credential = credentialService.refreshAccessTokenByAppId(credential.getAppId());
                getLastUpdate(credential, url);
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                //429则sleep3秒再重试
                Thread.sleep(Constants.THREAD_SLEEP_3000);
                getLastUpdate(credential, url);
            } else {
                throw e;
            }
        }
        return lastUpdate;
    }

    public void createProductDeleteTask(CredentialEntity credential) throws Exception {
        Integer maxVersion = DataHelper.getInt(moProductIdRepository.findMaxVersionByAppId(credential.getAppId()));
        List<BigInteger> list = moProductIdRepository.findDeleteIdList(credential.getAppId(), maxVersion - 1, maxVersion);
        for (final BigInteger productId : list) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.put("id", productId);
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setAppId(credential.getAppId());
            taskEntity.setType(TaskTypeEnum.DROP);
            taskEntity.setResult(productEntity);
            mofficeRedisRepository.createTask(ChannelEnum.PRODUCT_CHANNEL, taskEntity);
        }
    }

    public void getIds(CredentialEntity credential, String resUrl, String type) throws Exception {
        if (StringHelper.equalsIgnoreCase("product", type)) {
            synProductId(credential, resUrl);
        }
        if (StringHelper.equalsIgnoreCase("sku", type)) {
            synSku(credential, resUrl);
        }
    }

    @Transactional
    private void synProductId(CredentialEntity credential, String url) throws Exception {
        Map params = new HashMap();
        params.put("select", "id");
        //查询sap的product列表
        List<Map> listId = getList(credential, params, url, Map.class);
        Integer maxVersion = DataHelper.getInt(moProductIdRepository.findMaxVersionByAppId(credential.getAppId()));
        List<MoProductIdEntity> productIdEntityList = new ArrayList<MoProductIdEntity>();
        for (Map map : listId) {
            MoProductIdEntity moProductIdEntity = new MoProductIdEntity();
            moProductIdEntity.setProductId(DataHelper.getLong(map.get("id")));
            moProductIdEntity.setAppId(credential.getAppId());
            moProductIdEntity.setVersion(maxVersion + 1);
            productIdEntityList.add(moProductIdEntity);
        }
        //新增新版本
        moProductIdRepository.save(productIdEntityList);
        //删除老版本
        moProductIdRepository.deleteByVersion(credential.getAppId(), maxVersion);
    }

    @Transactional
    private void synSku(CredentialEntity credential, String resUrl) throws Exception {
        Map params = new HashMap();
        //查询sap的sku列表
        List<Map> listId = getList(credential, params, resUrl, Map.class);
        Integer maxVersion = DataHelper.getInt(moSKURepository.findMaxVersionByAppId(credential.getAppId()));
        List<MoSKUEntity> entityList = new ArrayList<MoSKUEntity>();
        for (Map result : listId) {
            SKUInfoEntity skuInfoEntity = JsonpHelper.toObject(JsonpHelper.toString(result), SKUInfoEntity.class);
            SKUInfoEntity skuInfoEntityResult = (SKUInfoEntity) skuInfoEntity.clone();
            //移出product，防止product修改对sku的md5结果产生影响
            skuInfoEntity.remove("product");
            MoSKUEntity moSKUEntity = new MoSKUEntity();
            moSKUEntity.setAppId(credential.getAppId());
            moSKUEntity.setSkuId(skuInfoEntity.getLongId());
            moSKUEntity.setVersion(maxVersion + 1);
            moSKUEntity.setSkuMd5Str(Md5Helper.encrypt(JsonpHelper.toString(skuInfoEntity)));
            //查询上一个版本是否存在
            MoSKUEntity oldEntity = moSKURepository.findFirstByAppIdAndSkuIdAndVersion(credential.getAppId(), moSKUEntity.getSkuId(), maxVersion);
            if (null == oldEntity) {
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setAppId(credential.getAppId());
                taskEntity.setResult(skuInfoEntityResult);
                taskEntity.setType(TaskTypeEnum.ADD);//新增
                mofficeRedisRepository.createTask(ChannelEnum.SKU_CHANNEL, taskEntity);
            } else {
                if (!StringHelper.equalsIgnoreCase(oldEntity.getSkuMd5Str(), moSKUEntity.getSkuMd5Str())) {
                    TaskEntity taskEntity = new TaskEntity();
                    taskEntity.setAppId(credential.getAppId());
                    taskEntity.setResult(skuInfoEntityResult);
                    taskEntity.setType(TaskTypeEnum.MODIFY);//更新
                    mofficeRedisRepository.createTask(ChannelEnum.SKU_CHANNEL, taskEntity);
                }
            }
            entityList.add(moSKUEntity);
        }
        //新增新版本
        moSKURepository.save(entityList);
        //删除老版本
        moSKURepository.deleteByVersion(credential.getAppId(), maxVersion);
    }

}
