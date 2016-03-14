/**
 * 宝龙电商
 * com.plocc.im.config
 * WebAppConfig.java
 * <p/>
 * 2016-02-14
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebAppConfig
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:59
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置拦截器
     *
     * @param registry
     * @author lance
     */
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截未登录请求
        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/sdk/im/**").excludePathPatterns("/sdk/im/login", "/sdk/im/login/check");
    }
}
