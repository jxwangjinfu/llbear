package com.junfeng.platform.csc.config;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/28 13:39
 * @projectName pig
 */
@Configuration
@AllArgsConstructor
public class AuthorizationConfig {
	private final RedisConnectionFactory redisConnectionFactory;

	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(SecurityConstants.PROJECT_PREFIX + SecurityConstants.OAUTH_PREFIX);
		return tokenStore;
	}
}
