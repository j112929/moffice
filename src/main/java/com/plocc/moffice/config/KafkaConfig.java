/**
 * 宝龙电商
 * com.plocc.framework.config
 * ZookeeperConfig.java
 * <p/>
 * 2016-02-25
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.config;

import kafka.serializer.Decoder;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.kafka.core.ConnectionFactory;
import org.springframework.integration.kafka.core.DefaultConnectionFactory;
import org.springframework.integration.kafka.core.ZookeeperConfiguration;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.integration.kafka.serializer.common.StringDecoder;
import org.springframework.integration.kafka.support.ZookeeperConnect;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Repository;

/**
 * ZookeeperConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 12:31
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */

@Configuration("mofficeKafkaConfig")
public class KafkaConfig {

    @Bean(name = "inputToKafka")
    public MessageChannel inputToKafka() {
        return new QueueChannel();
    }

    @Bean(name = "inputFromKafka")
    public MessageChannel inputFromKafka() {
        return new QueueChannel();
    }

    @Bean(name = "kafkaStringDecoder")
    public Decoder kafkaStringDecoder() {
        return new StringDecoder();
    }

    @Bean(name = "kafkaStringSerializer")
    public Serializer kafkaStringSerializer() {
        return new StringSerializer();
    }

    @Bean(name = "zookeeperConnect")
    @ConfigurationProperties(prefix = "spring.zookeeper.moffice")
    public ZookeeperConnect zookeeperConnect() {
        return new ZookeeperConnect();
    }

    @Bean(name = "zkConfiguration")
    public ZookeeperConfiguration zkConfiguration(@Qualifier("zookeeperConnect") ZookeeperConnect zookeeperConnect) {
        return new ZookeeperConfiguration(zookeeperConnect);
    }

    @Bean(name = "kafkaBrokerConnectionFactory")
    public ConnectionFactory kafkaBrokerConnectionFactory(@Qualifier("zkConfiguration") ZookeeperConfiguration zkConfiguration) {
        return new DefaultConnectionFactory(zkConfiguration);
    }

    @Bean(name = "kafkaMessageDrivenChannelAdapter")
    public MessageProducer kafkaMessageDrivenChannelAdapter(
            @Qualifier("kafkaBrokerConnectionFactory") ConnectionFactory connectionFactory,
            @Qualifier("inputFromKafka") MessageChannel inputFromKafka,
            @Qualifier("kafkaStringDecoder") Decoder decoder) {
        KafkaMessageDrivenChannelAdapter adapter = new KafkaMessageDrivenChannelAdapter(new KafkaMessageListenerContainer(connectionFactory, "xj_xj", "zgj_spring_kafka_topic"));
        adapter.setOutputChannel(inputFromKafka);
        adapter.setKeyDecoder(decoder);
        return adapter;
    }

}
