package com.plocc.moffice.service;

import com.plocc.moffice.entity.CredentialEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by yanghuan on 2016-02-21.
 */
public interface MonitorRefreshService {
    /*
    *获取总数
     */
    String getCount(CredentialEntity credential, String url) throws Exception;

    /*
    *获取最后更新时间
     */
    String getLastUpdate(CredentialEntity credential, String url) throws Exception;

    /*
    *获取最大id
     */
    String getMaxId(CredentialEntity credential, String url) throws Exception;

    /*
    *同步数据
     */
    void getIds(CredentialEntity credential, String resUrl, String type) throws Exception;

    /*
    *生成删除任务
     */
    void createProductDeleteTask(CredentialEntity credential) throws Exception;

    /*
    *获取数据列表
     */
    <T> List<T> getList(CredentialEntity credential, Map params, String url, Class<T> tClass) throws Exception;

    <T> T selectById(CredentialEntity credential, Long id, Class<T> tClass, String url) throws Exception;
}
