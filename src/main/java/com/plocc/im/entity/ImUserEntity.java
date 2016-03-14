/**
 * 宝龙电商
 * com.plocc.user.repository
 * UcUserDbRepository.java
 * <p/>
 * 2016-02-02
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * UcUserDbRepository
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:56
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Entity(name = "uc_user")
public class ImUserEntity implements Serializable {
    private long id;
    private String username;
    private String nickname;
    private String head;
    private String password;
    private String sex;
    private String mall;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "`USER_NAME`")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "`NICKNAME`")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "`USER_ICON`")
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Column(name = "`USER_PWD`")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "`USER_MALL`")
    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }
}
