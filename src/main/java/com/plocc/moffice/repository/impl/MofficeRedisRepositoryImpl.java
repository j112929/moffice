/**
 * 宝龙电商
 * com.plocc.moffice.repository.impl
 * MofficeRedisRepository.java
 * <p/>
 * 2016-02-20
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.repository.impl;

import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.ServerHelper;
import com.plocc.framework.support.StringHelper;
import com.plocc.moffice.entity.TaskEntity;
import com.plocc.moffice.repository.MofficeRedisRepository;
import com.plocc.moffice.support.ChannelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * MofficeRedisRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 18:25
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Service
public class MofficeRedisRepositoryImpl implements MofficeRedisRepository {
    private static final String SERVICE_ID = ServerHelper.SERVER_ID;

    @Autowired
    @Qualifier("mofficeStringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取锁如果为空则设置当前服务器为 加锁服务器
     *
     * @return
     */
    public boolean getServerLock(String key, long timer) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String lock = operations.get(key);
        if (null == lock) {
            operations.set(key, lock = SERVICE_ID, timer, TimeUnit.MILLISECONDS);
        }
        return StringHelper.equalsIgnoreCase(lock, SERVICE_ID);
    }


    public String getMonitorValue(String key) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String value = operations.get(key);
        return value;
    }

    public void setMonitorValue(String key, String value) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
    }

    public void createTask(ChannelEnum channel, TaskEntity taskEntity) {
        stringRedisTemplate.convertAndSend(channel.getValue(), JsonpHelper.toString(taskEntity));
    }

}
