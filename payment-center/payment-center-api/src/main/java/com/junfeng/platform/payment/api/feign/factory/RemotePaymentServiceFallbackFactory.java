package com.junfeng.platform.payment.api.feign.factory;

import com.junfeng.platform.payment.api.feign.RemotePaymentService;
import com.junfeng.platform.payment.api.feign.fallback.RemotePaymentServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Component
public class RemotePaymentServiceFallbackFactory implements FallbackFactory<RemotePaymentService> {

	@Override
	public RemotePaymentService create(Throwable throwable) {
		RemotePaymentServiceFallbackImpl userPaymentOrderServiceFallback = new RemotePaymentServiceFallbackImpl();
		userPaymentOrderServiceFallback.setCause(throwable);
		return userPaymentOrderServiceFallback;
	}
}
