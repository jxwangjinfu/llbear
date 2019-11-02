package com.junfeng.platform.csc.Interceptor;

import com.junfeng.platform.csc.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/27 12:57
 * @projectName pig
 */
@Component
@AllArgsConstructor
public class TokenInterceptor implements HandshakeInterceptor {
	private final  TokenStore tokenStore;
	private final UserService userService;

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		ServletServerHttpRequest req = (ServletServerHttpRequest) request;

		//通过url的query参数获取认证参数
		String  accessToken  = req.getServletRequest().getParameter("token");
		if(StringUtils.isNotEmpty(accessToken)){
			OAuth2AccessToken token = tokenStore.readAccessToken(accessToken);
			if(token == null){
				return false;
			}
			OAuth2Authentication oAuth2Auth = tokenStore.readAuthentication(token);
			if(oAuth2Auth==null){
				return false;
			}
			Authentication authentication = oAuth2Auth.getUserAuthentication();
			if(authentication==null){
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

	}


}
