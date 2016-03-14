/**
 * 宝龙电商
 * com.plocc.im.repository
 * ImContactDbRepository.java
 * <p>
 * 2016-01-29
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.repository;

import com.plocc.im.entity.ImContactEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ImContactDbRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 23:04
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface ImContactRepository extends CrudRepository<ImContactEntity, String> {

    /**
     * 获取 单个联系人
     *
     * @param mid
     * @param mall
     * @param tid
     * @return
     */
    public ImContactEntity findByMidAndMallAndTid(String mid, String mall, String tid);

    /**
     * 获取全部联系人
     *
     * @param mid
     * @param mall
     * @return
     */
    public List<ImContactEntity> findByMidAndMallOrderByUpdateDateDesc(String mid, String mall,Pageable pageable);
}
