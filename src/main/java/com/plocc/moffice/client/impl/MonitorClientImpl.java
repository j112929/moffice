package com.plocc.moffice.client.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plocc.moffice.client.MonitorClient;
import com.plocc.moffice.entity.CredentialEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanghuan on 2016-02-21.
 */
@Service
public class MonitorClientImpl<T> extends BaseClient implements MonitorClient {

    public <T> List<T> pager(CredentialEntity credential, Map params, Class<T> tClass, String url) throws Exception {
        params = checkParams(credential, params);
        url += "?access_token={access_token}";
        if (params.containsKey("select")) {
            url += "&select={select},code";
        }
        if (params.containsKey("filter")) {
            url += "&filter={filter}";
        }
        if (params.containsKey("orderBy")) {
            url += "&orderby={orderBy}";
        }
        url += "&offset={offset}&limit={limit}&expand=*";
        String results = getForObject(url, String.class, params);
        return readList(results,tClass);
    }

    public String count(CredentialEntity credential, String url) throws Exception {
        Map params = checkParams(credential, new HashMap());
        url += "?access_token={access_token}";
        String count = getForObject(url, String.class, params);
        return count;
    }

    public String selectMaxId(CredentialEntity credential, String url) throws Exception {
        Map params = checkParams(credential, new HashMap());
        url += "?access_token={access_token}&offset={offset}&limit={limit}&orderby={orderBy}&select={select}";
        params.put("limit", 1);
        params.put("offset", 0);
        params.put("select", "id");
        params.put("orderBy", "id desc");
        List<Map> results = getForObject(url, List.class, params);
        if (null != results && results.size() > 0) {
            return results.get(0).get("id").toString();
        }
        return null;
    }

    public String selectLastUpdate(CredentialEntity credential, String url) throws Exception {
        Map params = checkParams(credential, new HashMap());
        url += "?access_token={access_token}&offset={offset}&limit={limit}&orderby={orderBy}&select={select}";
        params.put("limit", 1);
        params.put("offset", 0);
        params.put("select", "updateTime");
        params.put("orderBy", "updateTime desc");
        List<Map> results = getForObject(url, List.class, params);
        if (null != results && results.size() > 0) {
            return results.get(0).get("updateTime").toString();
        }
        return null;
    }

    public <T> T selectById(CredentialEntity credential, Long id, Class<T> tClass, String url) throws Exception {
        Map<String, Object> params = checkParams(credential, new HashMap());
        url += "/{id}?access_token={access_token}&expand=*";
        params.put("id", id);
        return getForObject(url, tClass, params);
    }

    public <T> List<T> readList(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        return mapper.readValue(json, javaType);
    }

    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
