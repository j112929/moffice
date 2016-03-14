/**
 * 宝龙电商
 * com.plocc.im.config
 * ImRedisConfig.java
 * <p/>
 * 2016-02-05
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ImRedisConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 15:16
 * @email zhanggj@powerlong.com
 * @description Redis 配置
 */
@Configuration("MofficeRedisConfig")
public class RedisConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.moffice")
    public RedisConnectionFactory mofficeRedisConnectionFactory() {
        return new JedisConnectionFactory();
    }


    @Bean
    public StringRedisTemplate mofficeStringRedisTemplate(@Qualifier("mofficeRedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(connectionFactory);
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }

    @Bean
    public RedisTemplate mofficeObjectRedisTemplate(@Qualifier("mofficeRedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
