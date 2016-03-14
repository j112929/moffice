/**
 * 宝龙电商
 * com.plocc.im.support
 * SessionHelper.java
 * <p/>
 * 2016-02-02
 * 2016宝龙公司-版权所有
 */
package com.plocc.im.support;

import com.plocc.framework.support.Constants;
import com.plocc.framework.support.StringHelper;
import com.plocc.im.entity.ImUserEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * SessionHelper
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 19:44
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public class SessionHelper {
    public static void setLogin(ImUserEntity userEntity) {
        RequestContextHolder.getRequestAttributes().setAttribute(Constants.WEB_SESSION_MEMBER_KEY, userEntity, RequestAttributes.SCOPE_SESSION);
    }

    public static ImUserEntity getLoginUser() {
        return (ImUserEntity) RequestContextHolder.getRequestAttributes().getAttribute(Constants.WEB_SESSION_MEMBER_KEY, RequestAttributes.SCOPE_SESSION);
    }

    public static boolean isLogin() {
        return null != RequestContextHolder.getRequestAttributes().getAttribute(Constants.WEB_SESSION_MEMBER_KEY, RequestAttributes.SCOPE_SESSION);
    }

    public static void logout() {
        RequestContextHolder.getRequestAttributes().removeAttribute(Constants.WEB_SESSION_MEMBER_KEY, RequestAttributes.SCOPE_SESSION);
    }

    public static boolean isUser(String id) {
        ImUserEntity userEntity = getLoginUser();
        return null != userEntity && StringHelper.equalsIgnoreCase(id, String.valueOf(userEntity.getId()));
    }
}
