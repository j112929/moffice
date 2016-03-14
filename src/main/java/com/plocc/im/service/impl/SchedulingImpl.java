/**
 * 宝龙电商
 * com.plocc.im.config
 * SchedulingConfig.java
 * <p/>
 * 2016-01-24
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.service.impl;

import com.plocc.im.entity.ImSession;
import com.plocc.im.service.ImServer;
import com.plocc.im.support.ImHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * SchedulingConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 5:03
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Configuration
@EnableScheduling
public class SchedulingImpl {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingImpl.class);
    @Autowired
    private ImServer imServer;
    @Autowired
    @Qualifier("imRedisTemplate")
    private StringRedisTemplate redisTemplate;
    // 离线缓冲消息最大记录数
    private final int OFF_MSG_BUFFER_MAX_SIZE = 50;
    // 离线消息缓冲列表处理结果
    private static Map<String, Integer> offMsgProcessHistory = new ConcurrentHashMap();


    /**
     * 检查设备状态
     * 间隔30秒检查一次是非正常离线设备、进行补救，系统启动后1分钟开启任务
     * 补救非正常离线
     */
    @Scheduled(fixedDelay = 30 * 1000, initialDelay = 60 * 1000)
    public void inspectionDeviceStatus() {
        try {
            Map<String, WebSocketSession> devices = imServer.getOnlineDevices();
            if (!devices.isEmpty()) {
                SetOperations operations = redisTemplate.opsForSet();
                for (Map.Entry<String, WebSocketSession> device : devices.entrySet()) {
                    ImSession imSession = ImHelper.getImSession(device.getValue());
                    // 设备在线信息丢失、补救重新加入在线设别列表
                    if (device.getValue().isOpen() && !operations.isMember(imSession.getDeviceSet(), imSession.getDeviceSetId())) {
                        // 添加在线设备
                        redisTemplate.opsForSet().add(imSession.getDeviceSet(), imSession.getDeviceSetId());
                        // 我上线了、给我离线消息
                        redisTemplate.opsForList().leftPush(ImHelper.getReqOfflineListKey(), imSession.getUser());
                        // 设备状态异常、已修复
                        logger.error(String.format("device status error,have to repair %s", imSession.getDevice()));
                    }
                }
                logger.info("device status inspection completed");
            }
        } catch (Exception ex) {
            logger.error("检测非正常离线列定时任务表异常：", ex);
        }
    }

    /**
     * 间隔5分钟输出在线设备信息
     */
    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void printSystemInfo() {
        try {
            Set<String> devices = imServer.getOnlineDevices().keySet();
            if (!devices.isEmpty()) {
                logger.info("");
                logger.info("=========================================================");
                logger.info("\t\t\t\t\t\t供" + devices.size() + "个用户在线");
                logger.info("=========================================================");
                for (String device : devices) {
                    logger.info("\t\t\t\t" + device);
                }
                if (devices.size() > 0) {
                    logger.info("=========================================================");
                }
            }
        } catch (Exception ex) {
            logger.error("输出在线设备信息定时任务异常：", ex);
        }
    }

    /**
     * 回复设备心跳消息
     * 间隔10秒为所有在线用户发送一次心跳
     */
    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void replyDeviceHeartBeat() {
        try {
            Map<String, WebSocketSession> devices = imServer.getOnlineDevices();
            for (Map.Entry<String, WebSocketSession> device : devices.entrySet()) {
                ImSession imSession = ImHelper.getImSession(device.getValue());
                try {
                    if (!device.getValue().isOpen()) {
                        devices.remove(device.getValue());
                    } else {
                        device.getValue().sendMessage(new PingMessage());
                    }
                } catch (Exception ex) {
                    logger.error(String.format("reply device heart-beat msg fail %s", imSession.getDevice()));
                }
            }
        } catch (Exception ex) {
            logger.error("发送心跳定时任务异常：", ex);
        }
    }

    /**
     * 处理开启消息缓冲模式数据
     * 间隔1秒处理一次首次上线离线缓冲消息
     */
    @Scheduled(fixedDelay = 1000)
    public void processOffMsgBufferList() {
        try {
            Map<String, ConcurrentLinkedQueue<String>> OFF_MSG_BUFFER_LIST = imServer.getOffMsgBufferList();
            Map<String, WebSocketSession> devices = imServer.getOnlineDevices();
            if (!OFF_MSG_BUFFER_LIST.isEmpty()) {

                for (Map.Entry<String, ConcurrentLinkedQueue<String>> offMsgEntry : OFF_MSG_BUFFER_LIST.entrySet()) {
                    // 设备编号
                    String device = offMsgEntry.getKey();
                    // Copy一份防止线程冲突
                    ConcurrentLinkedQueue<String> offMsgList = new ConcurrentLinkedQueue(offMsgEntry.getValue());
                    // 已经处理过的记录记录数
                    Integer offMsgProcessHistorySize = offMsgProcessHistory.get(device); // 回话
                    WebSocketSession session = devices.get(offMsgEntry.getKey());
                    if (null == session || !session.isOpen()) {
                        // 设备离线
                        // 采取补救措施将消息加入离线消息列表
                        if (!offMsgList.isEmpty()) {
                            for (String message : offMsgList) {
                                redisTemplate.opsForList().rightPush(ImHelper.getOffMsgListRedisKeyByDevice(device), message);
                            }
                            logger.error("设备离线缓冲区离线消息已重新加入Redis离线消息队列：" + device + "->" + offMsgList);
                        }
                        // 清理 处理结果
                        offMsgProcessHistory.remove(device);
                        OFF_MSG_BUFFER_LIST.remove(device);
                    } else if (null == offMsgProcessHistorySize) {
                        // 首次检测初始离线消息数
                        offMsgProcessHistory.put(device, offMsgList.size());
                    } else if (offMsgList.size() <= OFF_MSG_BUFFER_MAX_SIZE && offMsgProcessHistorySize != offMsgList.size()) {
                        // 小于 OFF_MSG_BUFFER_MAX_SIZE 条并且缓冲数与记录数不相等 -》 继续缓冲中
                        offMsgProcessHistory.put(device, offMsgList.size());
                    } else {
                        // 缓冲中
                        offMsgProcessHistory.put(device, offMsgProcessHistorySize = offMsgList.size());
                        if (0 == offMsgProcessHistorySize) {
                            // 缓冲结束、清理数据
                            offMsgProcessHistory.remove(device);
                            OFF_MSG_BUFFER_LIST.remove(device);
                        } else {
                            // 开始发送
                            List<Map> messages = new LinkedList();
                            for (String message : offMsgList) {
                                messages.add(ImHelper.fromServerJson(message));
                            }
                            try {
                                // 重新获取 Session 可以已经失效了
                                session = devices.get(offMsgEntry.getKey());
                                session.sendMessage(ImHelper.toDeviceMessage(messages));
                                // 更新联系人
                                for (String message : offMsgList) {
                                    if (!ImHelper.isGrabSingleMessage(message)) {
                                        ImHelper.addMsgToList(imServer.getImHistoryMsgList(), device, message);
                                    }
                                }
                                logger.debug(String.format("离线缓冲消息、分发给设备：%s -> %s", device, messages));
                            } catch (Exception ex) {
                                for (String message : offMsgList) {
                                    redisTemplate.opsForList().rightPush(ImHelper.getImSession(session).getOffMsgListKey(), message);
                                }
                                logger.error(String.format("离线缓冲消息分发失败已重新加入 Redis 离线消息队列：%s -> %s", device, messages), ex);
                            }
                            // 处理完成重置数据
                            offMsgProcessHistory.remove(device);
                            OFF_MSG_BUFFER_LIST.get(device).removeAll(offMsgList);
                            if (offMsgProcessHistorySize <= OFF_MSG_BUFFER_MAX_SIZE) {
                                // 缓冲消息已完全处理结束
                                OFF_MSG_BUFFER_LIST.remove(device);
                            } else {
                                // 还有未处理的 继续缓冲中
                                System.out.println("========================");
                            }
                            if (null != session) {
                                ImSession imSession = ImHelper.getImSession(session);
                                imSession.setOffMsgLastRedTime(System.currentTimeMillis());
                            }
                        }
                    }
                }
                logger.info("************************************************** 缓冲消息处理完成");
            }
        } catch (Exception ex) {
            logger.error("处理离线缓冲消息列表异常：", ex);
        }
    }

    /**
     * 发送离线缓冲数据到 Redis
     * 间隔1分钟处理一次离线缓冲消息存放Redis
     */
    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void processOffMsgRedisBufferList() {
        try {
            Map<String, WebSocketSession> devices = imServer.getOnlineDevices();
            Map<String, ConcurrentLinkedQueue<String>> OFF_MSG_REDIS_BUFFER_LIST = imServer.getOffMsgRedisBufferList();
            for (Map.Entry<String, ConcurrentLinkedQueue<String>> offMsgEntry : OFF_MSG_REDIS_BUFFER_LIST.entrySet()) {
                String device = offMsgEntry.getKey();
                ConcurrentLinkedQueue<String> offMsgList = new ConcurrentLinkedQueue(offMsgEntry.getValue());
                int processingSize = offMsgList.size();
                String message = offMsgList.poll();
                while (null != message) {
                    try {
                        WebSocketSession session = devices.get(device);
                        // 如果回话存在则尝试补发一次、成功 这条消息就忽略、否则该消息 放进历史回话、下次上线才能接收到。
                        if (null != session && session.isOpen()) {
                            Thread.sleep(10);
                            session.sendMessage(ImHelper.toDeviceMessage(message));
                            if (!ImHelper.isGrabSingleMessage(message)) {
                                // 更新联系人
                                ImHelper.addMsgToList(imServer.getImHistoryMsgList(), device, message);
                            }
                            if (logger.isDebugEnabled()) {
                                logger.debug("replacement reply message " + device + "->" + message);
                            }
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception ex) {
                        redisTemplate.opsForList().rightPush(ImHelper.getOffMsgListRedisKeyByDevice(device), message);
                        logger.debug(String.format("replacement reply message failure 、msg to join redis、device has been offline %s -> %s", device, message));
                    }
                    message = offMsgList.poll();
                }
                if (processingSize == offMsgEntry.getValue().size()) {
                    // 全部处理完毕
                    OFF_MSG_REDIS_BUFFER_LIST.remove(device);
                } else {
                    // 局部处理完毕
                    offMsgEntry.getValue().removeAll(offMsgList);
                }
            }
        } catch (Exception ex) {
            logger.error("************************************************** 处理离线缓冲消息存放Redist异常：", ex);
        }
    }

    /**
     * 同步联系人列表、并生成历史聊天记录
     * 间隔5秒自动同步联系人列表、
     */
    @Scheduled(fixedDelay = 1 * 1000)
    public void processContacts() {
        try {
            Map<String, ConcurrentLinkedQueue<String>> IM_HISTORY_MSG_LIST = imServer.getImHistoryMsgList();
            if (!IM_HISTORY_MSG_LIST.isEmpty()) {
                for (Map.Entry<String, ConcurrentLinkedQueue<String>> entry : IM_HISTORY_MSG_LIST.entrySet()) {
                    ConcurrentLinkedQueue<String> msgList = entry.getValue();
                    String msgStr = msgList.poll();
                    while (null != msgStr) {
                        try {
                            if (!ImHelper.isGrabSingleMessage(msgStr)) {
                                // 排除抢单消息
                                Map<String, String> message = ImHelper.fromServerJson(msgStr);
                                message.put("mid", ImHelper.getUserByDevice(entry.getKey()).substring(1));
                                imServer.saveHistory(message);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        msgStr = msgList.poll();
                    }
                    IM_HISTORY_MSG_LIST.remove(entry.getKey());
                }
            }
        } catch (Exception ex) {
            logger.error("************************************************** 处理离线缓冲消息存放Redist异常：", ex);
        }
    }

}
