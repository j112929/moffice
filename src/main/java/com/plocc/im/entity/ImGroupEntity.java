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

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GroupEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 11:33
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Entity(name = "tb_im_group")
public class ImGroupEntity extends Pojo {
    // 名称
    private String name;
    // 编号
    private String group;
    // 所属MALL
    private ImMallEntity mall;
    // 群组客服
    private List<ImGroupUserEntity> groupUsers;
    // 是否默认群组
    private int isDefault;

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    @Column(name = "`group`")
    public String getGroup() {
        return getString("group");
    }

    public void setGroup(String group) {
        set("group", group);
    }

    public ImMallEntity getMall() {
        return (ImMallEntity) get("mall");
    }

    public void setMall(ImMallEntity mall) {
        set("mall", mall);
    }

    public void setIsDefault(int isDefault) {
        set("isDefault", isDefault);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "`group`", referencedColumnName = "`group`")
    public List<ImGroupUserEntity> getGroupUsers() {
        List<ImGroupUserEntity> groupUsers = getList("groupUsers");
        if (null == groupUsers) {
            put("groupUsers", groupUsers = new ArrayList<ImGroupUserEntity>());
        }
        return groupUsers;
    }

    public void setGroupUsers(List<ImGroupUserEntity> groupUsers) {
        set("groupUsers", groupUsers);
    }

    // 不能为空
    @Column(nullable = false)
    public int getIsDefault() {
        return getInt("isDefault");
    }

}
