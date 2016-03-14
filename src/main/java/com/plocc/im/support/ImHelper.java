/**
 * 宝龙电商
 * com.plocc.im.support
 * ImHelper.java
 * <p/>
 * 2016-01-24
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plocc.framework.support.Md5Helper;
import com.plocc.framework.support.NetHelper;
import com.plocc.framework.support.RandomHelper;
import com.plocc.framework.support.ServerHelper;
import com.plocc.im.entity.ImHistoryEntity;
import com.plocc.im.entity.ImSession;
import com.plocc.im.service.impl.ImServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.net.NetworkInterface;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ImHelper
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 4:44
 * @email zhanggj@powerlong.com
 * @description Im 辅助类
 */
public class ImHelper {

    private static final Logger logger = LoggerFactory.getLogger(ImServerImpl.class);

    // Web 集群环境下、同一主题会产生多个订阅、该标识用户区分连接 Redis的客户端。
    // 最终生成：Uxx_web_sessionId_SERVER_ID
    // Redis订阅主题：U*_*_*SERVER_ID
    public static final String SERVER_ID = ServerHelper.SERVER_ID;
    public final static String SESSION_TOPIC_KEY = "session_topic_key";
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static String MSG_TOPIC = "msg";
    //device,from,to,group,sTime,mall,type,content
    private final static String MSG_TPL = "{\"head\":{\"type\":\"sendMessage\",\"device\":\"%s\",\"from\":\"%s\",\"to\":\"U%s_G%s\",\"toType\":\"group\",\"sTime\":%s,\"mallId\":%s}," +
            "\"body\":{\"type\":\"%s\"," +
            "\"content\":\"%s\"}}";

    /**
     * 是否是心跳消息
     *
     * @param message
     * @return
     */
    public static boolean isHealthCheckMessage(String message) {
        return message.indexOf("\"type\":\"healthCheck\"") != -1;
    }

    public static boolean isGrabSingleMessage(String message) {
        return message.indexOf("\"type\":\"grab_single\"") != -1;
    }

    /**
     * 是否Web设备
     *
     * @param message
     * @return
     */
    public static boolean isWebDevice(String message) {
        return message.indexOf("_web_") != -1;
    }

    public static String getMsgTopic() {
        return MSG_TOPIC;
    }


    /**
     * 获取、初始化 Topic 信息
     *
     * @param session
     */
    public static ImSession getImSession(WebSocketSession session) {
        Map attrs = session.getAttributes();
        ImSession imSession = (ImSession) attrs.get(SESSION_TOPIC_KEY);
        if (null == imSession) {
            String path = session.getUri().getPath();
            String[] vars = path.substring(path.indexOf("/chat/")).split("/");
            imSession = new ImSession();
            imSession.setUser("U" + vars[2]);
            imSession.setDeviceType(vars[3]);
            imSession.setDeviceId(session.getId() + "_" + SERVER_ID);
            imSession.setDevice(imSession.getUser() + "_" + imSession.getDeviceType() + "_" + imSession.getDeviceId());
            imSession.setOffMsgListKey("offMsgList_" + imSession.getUser());
            imSession.setDeviceSet("deviceSet_" + imSession.getUser());
            imSession.setDeviceSetId(imSession.getDeviceType() + "_" + imSession.getDeviceId());
            imSession.setDestination(session.getUri().getPath());
            attrs.put(SESSION_TOPIC_KEY, imSession);
        }
        return imSession;
    }

    // 用户不在线时、无法将收到的消息送达所以要放到离线列表里
    public static void addMsgToList(Map<String, ConcurrentLinkedQueue<String>> redisBufferList, String device, String message) {
        ConcurrentLinkedQueue list = redisBufferList.get(device);
        if (null == list) {
            redisBufferList.put(device, list = new ConcurrentLinkedQueue());
        }
        list.add(message);
    }

    /**
     * 请求联消息Key，通知我上线了 给我离线消息
     *
     * @return
     */
    public static String getReqOfflineListKey() {
        return "reqOfflineList";
    }

    /**
     * Object to Json String
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static TextMessage toDeviceMessage(List messages) {
        return new TextMessage(ImHelper.toJSON(messages));
    }

    public static TextMessage toDeviceMessage(String message) {
        return toDeviceMessage(Arrays.asList(ImHelper.fromServerJson(message)));
    }

    public static String getOffMsgListRedisKeyByDevice(String device) {
        return "offMsgList_" + device.split("_")[0];
    }

    public static String getUserByDevice(String device) {
        return device.split("_")[0];
    }

    public static Map fromJson(String message) {
        try {
            return objectMapper.readValue(message, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public static Map fromServerJson(String message) {
        Map result = new HashMap();
        Map<String, Map> msg = fromJson(message);
        Map<String, String> head = null;
        Map<String, String> content = null;
        if (msg.containsKey("body") && msg.get("body").containsKey("type")) {
            content = msg.get("body");
        }
        if (msg.containsKey("head") && msg.get("head").containsKey("device")) {
            head = msg.get("head");
        }
        if (null != head) {
            String[] to = head.get("to").split("_");
            result.put("mall", String.valueOf(head.get("mallId")));
            result.put("from", head.get("from"));
            result.put("device", head.get("device").toLowerCase());
            result.put("send", head.get("sTime"));
            result.put("to", to[0].substring(1));
            result.put("group", to[1].substring(1));
        }
        if (null != content) {
            result.put("type", content.get("type"));
            if (null != content.get("content")) {
                result.put("content", content.get("content"));
            }
        }
        return result;
    }

    public static String toServerJson(WebSocketSession session, TextMessage message) {
        Map deviceMsg = fromJson(new String(message.asBytes()));
        ImSession imSession = getImSession(session);
        //device,from,to,group,mall,type,content
        return String.format(MSG_TPL,
                imSession.getDeviceType(),
                imSession.getUser().substring(1),
                deviceMsg.get("to"), deviceMsg.get("group"),
                System.currentTimeMillis(),
                deviceMsg.get("mall"),
                deviceMsg.get("type"),
                deviceMsg.get("content"),
                RandomHelper.getUUID());
    }


    public static String getMsgUniqueId(ImHistoryEntity imHistoryEntity) {
        String id = imHistoryEntity.getMid() + imHistoryEntity.getTid() + imHistoryEntity.getMall() + imHistoryEntity.getFrom() + imHistoryEntity.getContent() + imHistoryEntity.getSend().getTime();
        return Md5Helper.encrypt(id);
    }

    public static String getRedisContactsKey(String uid) {
        return "U" + uid + "_contacts";
    }

    public static void main(String[] args) {
        System.out.println(NetHelper.getMacAddress());
    }


}
