/**
 * 宝龙电商
 * com.plocc.moffice.entity
 * UserEntity.java
 * <p/>
 * 2016-02-17
 * 2016宝龙公司-版权所有
 */
package com.plocc.moffice.entity;

import com.plocc.framework.entity.Pojo;

/**
 * UserEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:11
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public class UserEntity extends Pojo {
    private String username;
    private String password;
    private String email;


    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        set("username", username);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        set("password", password);
    }

}
