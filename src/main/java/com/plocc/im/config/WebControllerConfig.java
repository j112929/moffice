/**
 * 宝龙电商
 * com.plocc.framework.config
 * ControllerConfig.java
 * <p>
 * 2016-01-29
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * ControllerConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 18:28
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@ControllerAdvice
public class WebControllerConfig extends AbstractJsonpResponseBodyAdvice {
    public WebControllerConfig() {
        super("callback");
    }
}