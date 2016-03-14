/**
 * 宝龙电商
 * com.plocc.im.entity
 * GroupEntity.java
 * <p/>
 * 2016-01-30
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

import com.plocc.framework.entity.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * GroupEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 11:33
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Entity(name = "tb_im_group_user")
public class ImGroupUserEntity extends Pojo {
    private String group;
    private Integer isShop;
    private ImUserEntity user;

    @Column(name = "`group`")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getIsShop() {
        return getInt("isShop");
    }

    public void setIsShop(Integer isShop) {
        set("isShop", isShop);
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    public ImUserEntity getUser() {
        return (ImUserEntity) get("user");
    }

    public void setUser(ImUserEntity user) {
        set("user", user);
    }
}
