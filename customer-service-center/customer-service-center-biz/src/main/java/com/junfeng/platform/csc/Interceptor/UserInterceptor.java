package com.junfeng.platform.csc.Interceptor;

import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.LinkedList;
import java.util.Map;

/**
 * 功能描述:UserInterceptor
 * @author: lw
 * @createTime: 2019/10/11 18:18
 * @param null
 * @return
 */
@Component
@AllArgsConstructor
public class UserInterceptor extends ChannelInterceptorAdapter {
	private final TokenStore tokenStore;
     /**
	 * 获取包含在stomp中的用户信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
			// 设置当前访问器的认证用户
			Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
			if (raw instanceof Map) {
				Object name = ((Map) raw).get("name");
				if (name instanceof LinkedList) {
					// 设置当前访问器的认证用户
					accessor.setUser(new Principal() {
						@Override
						public String getName() {
							return ((LinkedList) name).get(0).toString();
						}
					});
				}
			}
/*			Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
			if (raw instanceof Map) {
				Object name = ((Map) raw).get("token");
				Authentication authentication =null;
				if (name instanceof LinkedList) {
					String accessToken = ((LinkedList) name).get(0).toString();
					if(StringUtils.isNotEmpty(accessToken)){
						OAuth2AccessToken token = tokenStore.readAccessToken(accessToken);
						if(token == null){
							return null;
						}
						OAuth2Authentication oAuth2Auth = tokenStore.readAuthentication(token);
						if(oAuth2Auth==null){
							return null;
						}
						authentication = oAuth2Auth.getUserAuthentication();
						if(authentication==null){
							return null;
						}
						if(authentication==null)return null;
						accessor.setUser(authentication);
					}
				}
			}*/
		}
		return message;
	}
}
