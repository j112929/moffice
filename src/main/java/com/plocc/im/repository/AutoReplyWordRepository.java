/**
 * 宝龙电商
 * com.plocc.console.repository.ask
 * AskReplyWordDbRepository.java
 * <p>
 * 2016-01-04
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.repository;

import com.plocc.im.entity.AutoReplyWordEntity;
import com.plocc.framework.entity.Model;
import com.plocc.framework.repository.BaseDbRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * AutoReplyWordDbRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 17:33
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface AutoReplyWordRepository extends BaseDbRepository<AutoReplyWordEntity, String> {

    /**
     * 按照主键查询
     *
     * @param id
     * @return
     */
    AutoReplyWordEntity findById(String id);

    /**
     * 获取第一条 关键词 对应记录
     *
     * @param word
     * @return
     */
    AutoReplyWordEntity findTopByWordOrderByWeightDesc(String word);

    /**
     * 查询全部记录
     *
     * @param pageable
     * @return
     */
    Page<AutoReplyWordEntity> findAll(Pageable pageable);

    /**
     * weight 排序
     *
     * @param pageable
     * @return
     */
    Page<AutoReplyWordEntity> findAllByOrderByWeightDesc(Pageable pageable);

    Page<AutoReplyWordEntity> findAllByOrderByCreateDateDesc(Pageable pageable);

    /**
     * 按照 word 过滤、weight 排序
     *
     * @param pageable
     * @return
     */
    Page<AutoReplyWordEntity> findByWordOrderByWeightDesc(String word, Pageable pageable);

    Page<AutoReplyWordEntity> findByWordOrderByCreateDateDesc(String word, Pageable pageable);

    Page<AutoReplyWordEntity> findByWordContainingOrderByCreateDateDesc(String word, Pageable pageable);


   /* default Model validation(AutoReplyWordEntity input) {
        Model errors = BaseDbRepository.super.validation(input);
        // 关键词
        if (input.isNeedValidField("mall") && input.getInt("mall") < 0) {
            errors.set("mall", "必须选择MALL");
        }
        if (input.isNeedValidField("word") && input.isEmpty("word")) {
            errors.set("word", "必须输入关键词");
        } else if (null != findTopByWordOrderByWeightDesc(input.getStringByTrim("word"))) {
            errors.set("word", "关键词已存在");
        }
        if (input.isNeedValidField("weight")) {
            if (!input.isNumber("weight")) {
                errors.set("weight", "权重值必须为数字!");
            } else if (input.getInt("weight") < 1) {
                errors.set("weight", "必须设置权重值且大于等于1");
            }
        }
        if (input.isNeedValidField("reply") && input.isEmpty("reply")) {
            errors.set("reply", "必须填写对应回复内容");
        }
        return errors;
    }*/
}
