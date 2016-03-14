/**
 * 宝龙电商
 * com.plocc.im.service
 * Topic.java
 * <p>
 * 2016-01-24
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.entity;

/**
 * Topic
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 5:15
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public class ImSession {
    private String user;
    private String deviceId;
    private String deviceType;
    private String device;
    private String deviceSet;
    private String deviceSetId;
    private String offMsgListKey;
    private String destination;
    private long create = System.currentTimeMillis();
    private long offMsgLastRedTime = System.currentTimeMillis();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOffMsgListKey() {
        return offMsgListKey;
    }

    public void setOffMsgListKey(String offMsgListKey) {
        this.offMsgListKey = offMsgListKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(String deviceSet) {
        this.deviceSet = deviceSet;
    }

    public String getDeviceSetId() {
        return deviceSetId;
    }

    public void setDeviceSetId(String deviceSetId) {
        this.deviceSetId = deviceSetId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getCreate() {
        return create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getOffMsgLastRedTime() {
        return offMsgLastRedTime;
    }

    public void setOffMsgLastRedTime(long offMsgLastRedTime) {
        this.offMsgLastRedTime = offMsgLastRedTime;
    }
}
