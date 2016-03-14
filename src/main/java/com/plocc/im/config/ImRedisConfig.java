/**
 * 宝龙电商
 * com.plocc.im.config
 * ImRedisConfig.java
 * <p/>
 * 2016-02-05
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * ImRedisConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 15:16
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Configuration
public class ImRedisConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.im")
    public RedisConnectionFactory imRedisConnectionFactory() {
        return new JedisConnectionFactory();
    }


    @Bean
    public StringRedisTemplate imRedisTemplate(@Qualifier("imRedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
