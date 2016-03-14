/**
 * 宝龙电商
 * com.plocc.im.service.handler
 * ImHandler.java
 * <p>
 * 2016-01-24
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.service.impl;

import com.plocc.im.entity.ImSession;
import com.plocc.im.support.ImHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * ImHandler
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 3:07
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Component
public class ImWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ImServerImpl.class);

    @Autowired
    private ImServerImpl imServer;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        imServer.online(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        imServer.send(session, message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        ImSession imSession = ImHelper.getImSession(session);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("reply device “heart-beat” msg %s", imSession.getDevice()));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable ex) throws Exception {
        if (logger.isDebugEnabled()) {
            ImSession imSession = ImHelper.getImSession(session);
            logger.debug(String.format("web socket handleTransportError %s", imSession.getDevice()), ex);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        imServer.offline(session);
    }
}
