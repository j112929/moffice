/**
 * 宝龙电商
 * com.plocc.im.repository
 * ImMallRepository.java
 * <p/>
 * 2016-02-14
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.repository;

import com.plocc.im.entity.ImMallEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * ImMallRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:46
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface ImMallRepository extends CrudRepository<ImMallEntity, String> {
    public ImMallEntity findByCode(String code);
}
