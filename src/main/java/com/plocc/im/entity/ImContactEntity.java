/**
 * 宝龙电商
 * com.plocc.im.entity
 * ImContactEntity.java
 * <p/>
 * 2016-01-29
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plocc.framework.entity.Pojo;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * ImContactEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 22:56
 * @email zhanggj@powerlong.com
 * @description Im 联系人列表
 */
@Entity(name = "tb_im_contacts")
@JsonIgnoreProperties(value = {"id", "createDate", "creator", "updator", "status", "remarks", "version", "isDrop"})
public class ImContactEntity extends Pojo {
    // 我的编号
    private String mid;
    // 对方编号
    private String tid;
    // 昵称
    private String nickname;
    // 头像
    private String head;
    // 类型 weixin、web
    private String type;
    // 所在 mall
    private String mall;
    // 群组
    private String group;
    // 最后一次说的话
    private String ltc;
    // 是否客服
    private int ispm;
    // 是否商家
    private int issp;
    // 性别
    private int sex;

    private String device;

    public String getDevice() {
        return getString("device");
    }

    public void setDevice(String device) {
        set("device", device);
    }

    public String getMid() {
        return getString("mid");
    }

    public void setMid(String mid) {
        set("mid", mid);
    }

    public String getTid() {
        return getString("tid");
    }

    public void setTid(String tid) {
        set("tid", tid);
    }


    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        set("type", type);
    }

    public String getMall() {
        return getString("mall");
    }

    public void setMall(String mall) {
        set("mall", mall);
    }

    @Column(name = "`group`")
    public String getGroup() {
        return getString("group");
    }

    public void setGroup(String group) {
        set("group", group);
    }

    public String getLtc() {
        return getString("ltc");
    }

    public void setLtc(String ltc) {
        set("ltc", ltc);
    }

    @Transient
    public String getNickname() {
        return getString("nickname");
    }

    public void setNickname(String nickname) {
        set("nickname", nickname);
    }

    @Transient
    public String getHead() {
        return getString("head");
    }

    public void setHead(String head) {
        set("head", head);
    }

    @Transient
    public int getSex() {
        return getInt("sex");
    }

    public void setSex(int sex) {
        set("sex", sex);
    }

    @Transient
    public int getIspm() {
        return getInt("ispm");
    }

    public void setIspm(int ispm) {
        set("ispm", ispm);
    }

    @Transient
    public int getIssp() {
        return getInt("issp");
    }

    public void setIssp(int issp) {
        set("issp", issp);
    }

    public static void main(String[] args) {
        ImContactEntity imContactEntity = new ImContactEntity();
        //imContactEntity.setUpdateDate(new Date());
        imContactEntity.set("updateDate",new Date());

        imContactEntity.matches("updateDate","asdf");
        System.out.println(imContactEntity.isDate("updateDate"));

    }
}
