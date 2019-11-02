package com.junfeng.platform.sc.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * 请求中的工具操作
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/9/30 14:13
 * @projectName pig
 */
public class ContextHolderUtil {

    /**
     * 获取客户端ID，如pig
     *
     * @author: wangjf
     * @createTime: 2019/9/30 14:15
     * @param
     * @return java.lang.String
     */
    public static String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication)authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return "";
    }

    /**
     * 获取用户名
     *
     * @author: wangjf
     * @createTime: 2019/9/30 14:15
     * @param
     * @return java.lang.String
     */
    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "";
        }
        return authentication.getName();
    }
}
