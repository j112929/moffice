package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * OrderRelationEntity
 *
 * @author jzl
 * @version 1.0.0
 * @time 创建时间：2016年2月25日 下午4:34:36
 * @email 1129290218@qq.com
 * @description 主 ,子订单关联实体
 */
@Entity
@Table(name = "tb_mo_order_relation")
public class MoOrderRelationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PARENT_ORDER_ID")
    private Long parentOrderId;
    @Column(name = "CHILDREN_ORDER_ID")
    private Long childrenOrderId;
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "MEMO")
    private String memo;
    @Column(name = "PARENT_ORDER_STATUS")
    private String parentOrderStatus;
    @Column(name = "CHILDREN_ORDER_STATUS")
    private String childrenOrderStatus;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(Long parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public Long getChildrenOrderId() {
        return childrenOrderId;
    }

    public void setChildrenOrderId(Long childrenOrderId) {
        this.childrenOrderId = childrenOrderId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getParentOrderStatus() {
        return parentOrderStatus;
    }

    public void setParentOrderStatus(String parentOrderStatus) {
        this.parentOrderStatus = parentOrderStatus;
    }

    public String getChildrenOrderStatus() {
        return childrenOrderStatus;
    }

    public void setChildrenOrderStatus(String childrenOrderStatus) {
        this.childrenOrderStatus = childrenOrderStatus;
    }
}
