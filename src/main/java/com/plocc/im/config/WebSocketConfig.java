/**
 * 宝龙电商
 * com.plocc.im.config
 * WebSocketConfig.java
 * <p/>
 * 2016-01-24
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.config;

import com.plocc.im.service.impl.ImWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 3:28
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private ImWebSocketHandler imHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(imHandler, "/chat/**").setAllowedOrigins("*");
    }
}