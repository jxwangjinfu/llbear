package com.junfeng.platform.pc.api.feign.factory;

import com.junfeng.platform.pc.api.feign.RemoteProductService;
import com.junfeng.platform.pc.api.feign.fallback.RemoteProductServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/18 11:00
 * @projectName pig
 */
@Component
public class RemoteProductServiceFallbackFactory implements FallbackFactory<RemoteProductService> {

	@Override
	public RemoteProductService create(Throwable throwable) {
		RemoteProductServiceFallbackImpl remoteProductServiceFallback = new RemoteProductServiceFallbackImpl();
		remoteProductServiceFallback.setCause(throwable);
		return remoteProductServiceFallback;
	}
}
