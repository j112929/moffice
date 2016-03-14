/**
 * 宝龙电商
 * com.plocc.im.service.impl
 * ImServerImpl.java
 * <p/>
 * 2016-01-24
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.service.impl;

import com.plocc.framework.support.DataHelper;
import com.plocc.im.entity.ImContactEntity;
import com.plocc.im.entity.ImHistoryEntity;
import com.plocc.im.entity.ImSession;
import com.plocc.im.repository.ImContactRepository;
import com.plocc.im.repository.ImHistoryRepository;
import com.plocc.im.service.ImServer;
import com.plocc.im.support.ImHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ImServerImpl
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 3:04
 * @email zhanggj@powerlong.com
 * @description ImServer 实现
 */
@Configuration
@Service("imServer")
@Scope("singleton")
public class ImServerImpl implements ImServer {
    private static final Logger logger = LoggerFactory.getLogger(ImServerImpl.class);
    // 在线设备列表
    private static final Map<String, WebSocketSession> ONLINE_DEVICES = new ConcurrentHashMap();
    /**
     * 离线消息缓存列表
     * 设备首次上线待接收离线消息可能会很大，逐条推送性能很差。redis每次只能订阅到一条这里做一次缓冲
     * 间隔2秒检测一次缓冲列表列表、当进行到二次检测时 缓冲数量大于OFF_MSG_BUFFER_MAX_SIZE或较上一次数量没有发生改变时则立即发送缓冲消息。
     * 如果大于OFF_MSG_BUFFER_MAX_SIZE发送完后继续进入2秒间隔检测、重复之前的操作。如果是因为消息数量没有发生改变则 清理设备缓冲列表、进入正常逐条首发逻辑。
     * SchedulingTaskConfig.processOffMsgBufferList
     */
    private static final Map<String, ConcurrentLinkedQueue<String>> OFF_MSG_BUFFER_LIST = new ConcurrentHashMap();
    /**
     * redis离线消息
     * 用户不在线时、无法将收到的消息送达所以要放到离线列表里，但不能直接放。容易产生死循环。（GO从redis里推过来，这边在立即放进去会无限循环）
     * 所以这里缓冲一次
     */
    private static final Map<String, ConcurrentLinkedQueue<String>> OFF_MSG_REDIS_BUFFER_LIST = new ConcurrentHashMap();
    /**
     * 用户接收成功的消息、异步每隔5秒自动同步联系人列表
     */
    private static final Map<String, ConcurrentLinkedQueue<String>> IM_HISTORY_MSG_LIST = new ConcurrentHashMap();
    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;
    @Autowired
    @Qualifier("imRedisTemplate")
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ImContactRepository imContactDbRepository;
    @Autowired
    private ImHistoryRepository imHistoryDbRepository;

    /**
     * 设备上线
     *
     * @param session
     */
    public void online(WebSocketSession session) {
        ImSession imSession = ImHelper.getImSession(session);
        // 加入在线设备
        ONLINE_DEVICES.put(imSession.getDevice(), session);
        // 首次上线消息缓冲
        OFF_MSG_BUFFER_LIST.put(imSession.getDevice(), new ConcurrentLinkedQueue());
        // 添加在线设备
        redisTemplate.opsForSet().add(imSession.getDeviceSet(), imSession.getDeviceSetId());
        // 我上线了、给我离线消息
        redisTemplate.opsForList().leftPush(ImHelper.getReqOfflineListKey(), imSession.getUser());
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("device online ：%s", imSession.getDevice()));
        }
    }

    /**
     * 设备发送消息
     *
     * @param session
     * @param message
     */
    public void send(WebSocketSession session, TextMessage message) {
        try {
            ImSession imSession = ImHelper.getImSession(session);
            String strMsg = ImHelper.toServerJson(session, message);
            redisTemplate.convertAndSend(ImHelper.getMsgTopic(), strMsg);
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("receive device message ：%s -> %s", imSession.getDevice(), strMsg));
            }
        } catch (Exception ex) {
            logger.error(String.format("device send error ：%s", ex.getMessage()), ex);
        }
    }

    /**
     * 设备离线
     *
     * @param session
     */
    public void offline(WebSocketSession session) {
        // redis 取消订阅
        ImSession imSession = ImHelper.getImSession(session);
        if (null != imSession && ONLINE_DEVICES.containsKey(imSession.getDevice())) {
            // 清理在线设备
            redisTemplate.opsForSet().remove(imSession.getDeviceSet(), imSession.getDeviceSetId());
            // 删除在线设备
            ONLINE_DEVICES.remove(imSession.getDevice());
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("device offline ：%s", imSession.getDevice()));
            }
        }

    }

    /**
     * 添加联系人
     *
     * @param message
     * @return
     */
    private ImContactEntity updateContact(Map<String, String> message) {
        ImContactEntity contactEntity = new ImContactEntity();
        contactEntity.setDevice(message.get("device").toLowerCase());
        contactEntity.setType(message.get("type"));
        contactEntity.setMid(message.get("mid"));
        contactEntity.setTid(message.get("to"));
        contactEntity.setMall(message.get("mall"));
        contactEntity.setGroup(message.get("group"));
        contactEntity.setLtc(message.get("content"));
        ImContactEntity imContactEntity = imContactDbRepository.findByMidAndMallAndTid(contactEntity.getMid(), contactEntity.getMall(), contactEntity.getTid());
        if (null != imContactEntity) {
            imContactEntity.setType(contactEntity.getType());
            imContactEntity.setLtc(contactEntity.getLtc());
            contactEntity = imContactEntity;
        }
        imContactDbRepository.save(contactEntity);
        return contactEntity;
    }

    /**
     * 存储历史聊天记录
     *
     * @param message
     * @return
     */
    public boolean saveHistory(Map<String, String> message) {
        // 生成联系人列表
        updateContact(message);
        // 生成历史记录

        ImHistoryEntity imHistoryEntity = new ImHistoryEntity();
        imHistoryEntity.setDevice(message.get("device").toLowerCase());
        imHistoryEntity.setType(message.get("type"));
        imHistoryEntity.setMid(message.get("mid"));
        imHistoryEntity.setTid(message.get("to"));
        imHistoryEntity.setFrom(message.get("from"));
        imHistoryEntity.setMall(message.get("mall"));
        imHistoryEntity.setGroup(message.get("group"));
        imHistoryEntity.setContent(message.get("content"));
        imHistoryEntity.setSend(new Date(DataHelper.getLong(message.get("send"))));
        imHistoryEntity.setMsgid(ImHelper.getMsgUniqueId(imHistoryEntity));
        if (null == imHistoryDbRepository.findFirstByMsgid(imHistoryEntity.getMsgid())) {
            // 防止重复记录
            imHistoryDbRepository.save(imHistoryEntity);
        }
        return true;
    }

    // 获取联系人
    public List getContactList(String mid, String mall, int size) {
        return imContactDbRepository.findByMidAndMallOrderByUpdateDateDesc(mid, mall, new PageRequest(0, size));
    }

    /**
     * 获取设备在线列表
     *
     * @return
     */
    public Map<String, WebSocketSession> getOnlineDevices() {
        return this.ONLINE_DEVICES;
    }

    // 获取缓冲消息列表
    public Map<String, ConcurrentLinkedQueue<String>> getOffMsgBufferList() {
        return OFF_MSG_BUFFER_LIST;
    }

    // 获取要存放Redis的离线消息列表
    public Map<String, ConcurrentLinkedQueue<String>> getOffMsgRedisBufferList() {
        return OFF_MSG_REDIS_BUFFER_LIST;
    }

    // 获取用户接收成功的历史消息
    public Map<String, ConcurrentLinkedQueue<String>> getImHistoryMsgList() {
        return IM_HISTORY_MSG_LIST;
    }

    @Bean
    public RedisMessageListenerContainer imRedisMessageListenerContainer(@Qualifier("imRedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new MessageListener() {
            public void onMessage(Message messageObject, byte[] bytes) {
                String device = new String(messageObject.getChannel());
                String message = messageObject.toString();
                try {
                    WebSocketSession session = ONLINE_DEVICES.get(device);
                    ImSession imSession = null == session ? null : ImHelper.getImSession(session);
                    if (null != session && session.isOpen()) {
                        if (ImHelper.isHealthCheckMessage(message)) {
                            // 处理 直接回复
                            redisTemplate.convertAndSend(ImHelper.getMsgTopic(), message);
                            if (logger.isDebugEnabled()) {
                                logger.debug(String.format("reply redis heart-beat msg %s", device));
                            }
                        } else {
                            if (System.currentTimeMillis() - imSession.getOffMsgLastRedTime() > 2000 && OFF_MSG_BUFFER_LIST.containsKey(device) && OFF_MSG_BUFFER_LIST.get(device).isEmpty()) {
                                // 忽略回话创建2秒以后还没有离线消息的缓冲区
                                OFF_MSG_BUFFER_LIST.remove(device);
                            }
                            if (OFF_MSG_BUFFER_LIST.containsKey(device)) {
                                // 开启消息缓冲模式
                                OFF_MSG_BUFFER_LIST.get(device).add(message);
                                logger.debug(String.format("enable the message buffering mode %s", device));
                            } else {
                                // 正常逻辑即刻转发给相关设备
                                try {
                                    // 重新获取 Session 可以已经失效了
                                    ONLINE_DEVICES.get(device).sendMessage(ImHelper.toDeviceMessage(message));
                                    if (!ImHelper.isGrabSingleMessage(message)) {
                                        // 更新联系人
                                        ImHelper.addMsgToList(IM_HISTORY_MSG_LIST, device, message);
                                    }
                                    if (logger.isDebugEnabled()) {
                                        logger.debug("reply message " + device + "->" + message);
                                    }
                                } catch (Exception ex) {
                                    // 设备离线、送达失败、采取补救措施将消息加入离线消息列表
                                    ImHelper.addMsgToList(OFF_MSG_REDIS_BUFFER_LIST, device, message);
                                    if (logger.isDebugEnabled()) {
                                        logger.debug(String.format(" msg to join buffer list、reply message failure device offline %s -> %s", device, message));
                                    }
                                }
                            }
                        }
                    } else {
                        if (ImHelper.isHealthCheckMessage(message) || ImHelper.isGrabSingleMessage(message)) {
                            // 设备已经离线丢弃掉心跳、和抢单消息
                            if (logger.isDebugEnabled()) {
                                logger.debug("discard msg、device offline " + device + "->" + message);
                            }
                        } else {
                            // 设备离线、送达失败、采取补救措施将消息加入离线消息列表
                            // 防止死循环、进行缓冲(GO程序一边从离线列表推过来，这边一边加入离线列表)
                            ImHelper.addMsgToList(OFF_MSG_REDIS_BUFFER_LIST, device, message);
                            if (logger.isDebugEnabled()) {
                                logger.debug(String.format(" msg to join buffer list、device offline %s -> %s", device, message));
                            }
                        }
                    }
                } catch (Exception ex) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("redis subscription error " + device + "->" + message);
                    }
                }
            }
        }, new PatternTopic("U*_*_*" + ImHelper.SERVER_ID));
        return container;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
