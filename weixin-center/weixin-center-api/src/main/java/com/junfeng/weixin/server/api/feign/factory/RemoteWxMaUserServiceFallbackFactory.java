package com.junfeng.weixin.server.api.feign.factory;

import com.junfeng.weixin.server.api.feign.RemoteWxMaUserService;
import com.junfeng.weixin.server.api.feign.fallback.RemoteWxMaUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteWxMaUserServiceFallbackFactory implements FallbackFactory<RemoteWxMaUserService> {

	@Override
	public RemoteWxMaUserService create(Throwable throwable) {
		RemoteWxMaUserServiceFallbackImpl remoteWxMaUserServiceFallback = new RemoteWxMaUserServiceFallbackImpl();
//		remoteTokenServiceFallback.setCause(throwable);
		return remoteWxMaUserServiceFallback;
	}
}
