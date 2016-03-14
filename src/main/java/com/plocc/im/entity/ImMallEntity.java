/**
 * 宝龙电商
 * com.plocc.im.entity
 * MallEntity.java
 * <p/>
 * 2016-02-14
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plocc.framework.entity.Pojo;

import javax.persistence.Entity;

/**
 * MallEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:08
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Entity(name = "tb_im_mall")
@JsonIgnoreProperties(value = {"id", "creator", "isDrop", "remarks", "status", "updator", "version", "createDate", "updateDate"})
public class ImMallEntity extends Pojo {

    // 名称
    private String name;
    // 编号
    private String code;

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getCode() {
        return getString("code");
    }

    public void setCode(String code) {
        set("code", code);
    }

}
