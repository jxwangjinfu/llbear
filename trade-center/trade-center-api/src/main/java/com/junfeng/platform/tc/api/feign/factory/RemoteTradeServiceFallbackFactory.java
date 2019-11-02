package com.junfeng.platform.tc.api.feign.factory;

import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.junfeng.platform.tc.api.feign.fallback.RemoteTradeOrderServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Component
public class RemoteTradeServiceFallbackFactory implements FallbackFactory<RemoteTradeOrderService> {

	@Override
	public RemoteTradeOrderService create(Throwable throwable) {
		return new RemoteTradeOrderServiceFallbackImpl().setCause(throwable);
//		UserOrderServiceFallbackImpl userPaymentOrderServiceFallback = new UserOrderServiceFallbackImpl();
//		userPaymentOrderServiceFallback.setCause(throwable);
//		return userPaymentOrderServiceFallback;
	}
}
