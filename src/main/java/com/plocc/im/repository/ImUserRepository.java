/**
 * 宝龙电商
 * com.plocc.user.repository
 * UcUserDbRepository.java
 * <p/>
 * 2016-02-02
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.repository;

import com.plocc.im.entity.ImUserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * UcUserDbRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:56
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface ImUserRepository extends CrudRepository<ImUserEntity, Long> {

    public ImUserEntity findByUsername(String username);

    public ImUserEntity findByUsernameAndPassword(String username, String pasword);

}
