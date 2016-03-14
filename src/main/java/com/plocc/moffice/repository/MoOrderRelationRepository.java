package com.plocc.moffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.plocc.moffice.entity.MoOrderRelationEntity;

/** 
 *
 *
 * @author  jzl  
 * @version 1.0.0
 * @time 创建时间：2016年2月25日 下午4:37:38 
 * @email 1129290218@qq.com
 * @description  
 */
public interface MoOrderRelationRepository extends CrudRepository<MoOrderRelationEntity, Integer>{
	@Query(value = "select m.PARENT_ORDER_ID from TB_MO_ORDER_RELATION m where m.APP_ID = ? and m.CHILDREN_ORDER_ID = ? limit 1", nativeQuery = true)
	Integer findByAppIdAndChildrenOrderId(String appId,Long childrenOrderId);
	@Query(value = "select m.CHILDREN_ORDER_ID from TB_MO_ORDER_RELATION m where m.APP_ID = ? and m.PARENT_ORDER_ID = ? limit 1", nativeQuery = true)
	List findByAppIdAndParentOrderId(String appId,Long parentOrderId);
	@Modifying@Transactional
	@Query(value = "update TB_MO_ORDER_RELATION set CHILDREN_ORDER_STATUS = ?1 where CHILDREN_ORDER_ID = ?2", nativeQuery = true)
	int setFixedChildrenOrderStatusFor(String childrenOrderStatus,Long childrenOrderId);
	@Modifying@Transactional
	@Query(value = "update TB_MO_ORDER_RELATION set PARENT_ORDER_STATUS = ?1 where PARENT_ORDER_ID = ?2", nativeQuery = true)
	int setFixedParentOrderStatusFor(String parentOrderStatus,Long parentOrderId);
}
