/**
 * 宝龙电商
 * com.plocc.im.repository
 * ImMessageDbRepository.java
 * <p/>
 * 2016-01-30
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.repository;

import com.plocc.im.entity.ImHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * ImMessageDbRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:29
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface ImHistoryRepository extends CrudRepository<ImHistoryEntity, String> {
    /**
     * 获取聊天历史记录
     * 根据 Mid、Mall、UpdateDate过滤
     *
     * @param mid
     * @param mall
     * @return
     */
    List<ImHistoryEntity> findByMidAndTidAndMallAndSendLessThanOrderBySendDesc(String mid, String tid, String mall, Date sendDate, Pageable pageable);


    ImHistoryEntity findFirstByMsgid(String msgid);
}
