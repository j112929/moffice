/**
 * 宝龙电商
 * com.plocc.im.entity
 * ImMessageEntity.java
 * <p/>
 * 2016-01-30
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

import com.plocc.framework.entity.Pojo;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * ImMessageEntity
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 16:26
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Entity(name = "tb_im_history")
public class ImHistoryEntity extends Pojo {
    // 我的消息
    private String mid;
    // 发送给谁的
    private String tid;
    // 谁发送的
    private String from;
    // 头型
    private String head;
    // 类型 weixin、web
    private String type;
    // 所在 mall
    private String mall;
    // 群组
    private String group;
    // 内容
    private String content;
    // 设备
    private String device;
    // 发送时间
    private Date send;
    // 消息唯一标识
    private String msgid;


    @Column(name = "`from`")
    public String getFrom() {
        return getString("from");
    }

    public void setFrom(String from) {
        set("from", from);
    }

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

    @Column(length = 1000)
    public String getContent() {
        return getString("content");
    }

    public void setContent(String content) {
        set("content", content);
    }

    public Date getSend() {
        return getDate("send");
    }

    public void setSend(Date send) {
        set("send", send);
    }

    public String getMsgid() {
        return getString("msgid");
    }

    public void setMsgid(String msgid) {
        set("msgid", msgid);
    }
}
