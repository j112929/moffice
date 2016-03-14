/**
 * 宝龙电商
 * com.plocc.im.repository
 * ImGroupDbRepository.java
 * <p/>
 * 2016-01-30
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.repository;

import com.plocc.im.entity.ImGroupEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * ImGroupDbRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 12:22
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface ImGroupRepository extends CrudRepository<ImGroupEntity, String> {
}
