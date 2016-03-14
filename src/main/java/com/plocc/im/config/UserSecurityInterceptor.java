/**
 * 宝龙电商
 * com.plocc.im.config
 * UserSecurityInterceptor.java
 * <p/>
 * 2016-02-14
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.config;

import com.plocc.framework.entity.JsonResult;
import com.plocc.framework.support.JsonpHelper;
import com.plocc.framework.support.ValidHelper;
import com.plocc.im.support.SessionHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UserSecurityInterceptor
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:55
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public class UserSecurityInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //验证用户是否登陆
        if (!SessionHelper.isLogin()) {
            JsonResult jsonResult = JsonpHelper.build();
            jsonResult.setCode(503);
            jsonResult.setSuccess(false);
            jsonResult.setMessage("未登录");
            response.setContentType("application/javascript;charset=UTF-8");
            if (ValidHelper.isNotEmpty(request.getParameter("callback"))) {
                response.getWriter().write(request.getParameter("callback") + "(" + JsonpHelper.toString(jsonResult) + ")");
            } else {
                response.getWriter().write(JsonpHelper.toString(jsonResult));

            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
