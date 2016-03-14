/**
 * 宝龙电商
 * com.plocc.moffice.repository
 * MofficeRedisRepository.java
 * <p/>
 * 2016-02-20
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.repository;

import com.plocc.moffice.entity.TaskEntity;
import com.plocc.moffice.support.ChannelEnum;

/**
 * MofficeRedisRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 18:25
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface MofficeRedisRepository {
    /**
     * 获取锁
     *
     * @param timeout 超时时间 ms
     * @return
     */
    public boolean getServerLock(String key, long timeout);


    /**
     * 获取任务最后的value
     *
     * @return
     */
    String getMonitorValue(String key);

    /**
     * 更新任务最后的value
     *
     * @return
     */
    void setMonitorValue(String key, String value);

    /**
     * 根据channel生成任务
     *@param taskEntity
     * @return
     */
    void createTask(ChannelEnum channel, TaskEntity taskEntity);

}
