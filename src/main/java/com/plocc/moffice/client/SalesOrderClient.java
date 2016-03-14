/**
 * 宝龙电商
 * com.plocc.moffice.service
 * OrderService.java
 * <p/>
 * 2016-02-15
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.client;

import com.plocc.moffice.entity.CredentialEntity;
import com.plocc.moffice.entity.SalesOrderEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * OrderRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:14
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface SalesOrderClient {

    /**
     * 全部订单
     *
     * @param credential 店铺平成
     * @param params     条件
     * @param pager      分页参数
     * @return
     */
    Page<SalesOrderEntity> pager(CredentialEntity credential, Map params, Pageable pager) throws Exception;
    
    /**
     * 根据客户ID查询订单列表
     * @param credential
     * @param params
     * @param pager
     * @return
     */
    Page<SalesOrderEntity> findByCustomerId(CredentialEntity credential, Map params, Pageable pager) throws Exception;
    /**
     * 全部订单数量
     *
     * @param credential
     * @param params     条件
     * @return
     */
    Integer count(CredentialEntity credential, Map params) throws Exception;

    /**
     * 主键查询
     *
     * @param credential
     * @param orderId
     * @return
     */
    SalesOrderEntity findById(CredentialEntity credential, Long orderId) throws Exception;

    /**
     * 订单更新
     *
     * @param credential
     * @param salesOrderEntity
     * @param orderId
     */
     void update(CredentialEntity credential, SalesOrderEntity salesOrderEntity, Map params) throws Exception;

    /**
     * 创建单个销售订单
     *
     * @param salesOrder
     */
    String createOrder(CredentialEntity credential, SalesOrderEntity salesOrderEntity) throws Exception;

    /**
     * 取消单个订单
     *
     * @param credential
     * @param salesOrderEntity
     * @param orderId
     */
    void cancelOrder(CredentialEntity credential, SalesOrderEntity salesOrderEntity, Map params) throws Exception;


}
