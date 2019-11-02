package com.junfeng.platform.qc.api.feign.factory;

import org.springframework.stereotype.Component;

import com.junfeng.platform.qc.api.feign.RemoteQuartzService;
import com.junfeng.platform.qc.api.feign.fallback.RemoteQuartzServiceFallbackImpl;

import feign.hystrix.FallbackFactory;

/**
 * 熔断处理
 * @projectName:quartz-center-api
 * @author:Wangjf
 * @date:2019年9月24日 上午11:17:20
 * @version 1.0
 */
@Component
public class RemoteQuartzServiceFallbackFactory implements FallbackFactory<RemoteQuartzService> {

	@Override
	public RemoteQuartzService create(Throwable throwable) {
	    RemoteQuartzServiceFallbackImpl remoteQuartzServiceFallback = new RemoteQuartzServiceFallbackImpl();
	    remoteQuartzServiceFallback.setCause(throwable);
		return remoteQuartzServiceFallback;
	}
}
