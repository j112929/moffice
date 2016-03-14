package com.plocc.moffice.service;

/**
 * Created by yanghuan on 2016/2/23.
 * @description 订阅消息消费者接口
 */
public interface TaskConsumerHandle {
    void handle(String appId, Object result) throws Exception;
}
