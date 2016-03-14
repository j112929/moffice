package com.plocc.im.service;


import com.plocc.im.entity.ImContactEntity;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ImServer
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 5:00
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public interface ImServer {

    /**
     * 用户上线
     *
     * @param session
     */
    public void online(WebSocketSession session);

    /**
     * 设备发送消息
     *
     * @param session
     * @param message
     */
    public void send(WebSocketSession session, TextMessage message);

    /**
     * 设备离线
     *
     * @param session
     */
    public void offline(WebSocketSession session);

    /**
     * 存储历史聊天记录
     */
    public boolean saveHistory(Map<String, String> message);

    /**
     * 获取联系人列表
     *
     * @return
     */
    public List<ImContactEntity> getContactList(String mid, String mall, int size);

    /**
     * 获取设备在线列表
     *
     * @return
     */
    public Map<String, WebSocketSession> getOnlineDevices();

    // 获取缓冲消息列表
    public Map<String, ConcurrentLinkedQueue<String>> getOffMsgBufferList();

    // 获取要存放Redis的离线消息列表
    public Map<String, ConcurrentLinkedQueue<String>> getOffMsgRedisBufferList();

    // 获取用户接收成功的消息
    public Map<String, ConcurrentLinkedQueue<String>> getImHistoryMsgList();

}
