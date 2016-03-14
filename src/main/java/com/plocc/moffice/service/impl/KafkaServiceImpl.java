/**
 * 宝龙电商
 * com.plocc.moffice.service.impl
 * TestServiceImpl.java
 * <p/>
 * 2016-02-25
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.service.impl;

import com.plocc.framework.support.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * TestServiceImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:21
 * @email zhanggj@powerlong.com
 * @description Kafka 测试
 */
@Service
public class KafkaServiceImpl {

    @Autowired
    @Qualifier("inputToKafka")
    private MessageChannel inputToKafka;

    /**
     * 测试 每隔5秒发送数据
     */
    // @Scheduled(fixedDelay = 5000)
    public void output() {
        System.out.println(DateHelper.format("HH:mm:ss", new Date()));
        for (int i = 0; i < 10; i++) {
            MessageBuilder messageBuilder = MessageBuilder.withPayload("Message:" + DateHelper.format("HH:mm:ss", new Date()));
            messageBuilder = messageBuilder.setHeader(KafkaHeaders.MESSAGE_KEY, String.valueOf(i));
            inputToKafka.send(messageBuilder.setHeader(KafkaHeaders.TOPIC, "xj_xj").build());
            inputToKafka.send(messageBuilder.setHeader(KafkaHeaders.TOPIC, "zgj_spring_kafka_topic").build());
        }
    }

    /**
     * 测试 订阅消息
     *
     * @param message
     */
    // @ServiceActivator(inputChannel = "inputFromKafka", poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "100"))
    public void subscribe(Message<String> message) {
        System.out.println(message);
    }
}
