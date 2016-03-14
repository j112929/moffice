package com.plocc.moffice.service.impl;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.moffice.entity.TaskEntity;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.service.CredentialService;
import com.plocc.moffice.service.TaskConsumerHandle;
import com.plocc.moffice.support.ChannelEnum;
import com.plocc.moffice.support.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by yanghuan on 2016/2/19.
 * @description redis订阅
 */
@Service
public class SubscribeJob {
    private static final Logger logger = LoggerFactory.getLogger(SubscribeJob.class);
    @Autowired
    private MofficeRedisRepository mofficeRedisRepository;
    @Resource(name = "taskConsumers")
    private Map<String, Map<String, TaskConsumerHandle>> taskConsumers;
    @Autowired
    private CredentialService credentialService;

    @Bean
    public RedisMessageListenerContainer mofficeRedisMessageListenerContainer(@Qualifier("mofficeRedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(new MessageListener() {
            public void onMessage(Message message, byte[] bytes) {
                String channel = null;
                String msg = null;
                try {
                    channel = new String(message.getChannel(), "UTF-8");
                    msg = new String(message.getBody(), "UTF-8");
                    selectTask(channel, msg);
                } catch (Exception e) {
                    logger.error("任务处理异常,channel:" + channel + ";内容:" + msg, e);
                }
            }
        }, new PatternTopic("moffice_*"));
        return container;
    }


    private void selectTask(String channel, String msg) throws Exception {

        TaskEntity taskEntity = null;

        try {
            taskEntity = JsonpHelper.toObject(msg, TaskEntity.class);
            //根据channel，type查找对应该执行的方法
            taskConsumers.get(channel).get(taskEntity.getType().getValue()).handle(taskEntity.getAppId(), taskEntity.getResult());
        } catch (HttpClientErrorException e) {
            HttpStatus httpStatus = e.getStatusCode();
            if (httpStatus == HttpStatus.UNAUTHORIZED) {
                //401则刷新认证重试
                credentialService.refreshAccessTokenByAppId(taskEntity.getAppId());
                mofficeRedisRepository.createTask(ChannelEnum.getEnumByValue(channel), taskEntity);
            } else if (httpStatus == HttpStatus.TOO_MANY_REQUESTS) {
                //429则sleep3秒再重试
                Thread.sleep(Constants.THREAD_SLEEP_3000);
                mofficeRedisRepository.createTask(ChannelEnum.getEnumByValue(channel), taskEntity);
            } else {
                throw e;
            }
        }
    }

}
