package com.junfeng.platform.payment.service.responsibilitychain.barcodepay;

import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.service.bank.BankTradeService;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model.BarcodePayHandleParams;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付结果回写
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月19日 下午3:15:10
 */
@Component
public class BarcodePayResultHandler extends AbstractHandler<BarcodePayHandleParams> {

	@Autowired
	private BankTradeService bankTradeService;

	@Autowired
	private RedissonClient redissonClient;

	@Override
	public BarcodePayHandleParams handleRequest(BarcodePayHandleParams requestParam) throws Exception {
		if (requestParam.getPaySuccess()) {

			// 该处理需要跟支付回调和查询支付结果做同步，加锁
			RLock lock = redissonClient.getFairLock(requestParam.getPayOrderNo());

			if (lock != null && lock.isLocked()) {
				// 查询结果或者支付回调正在处理，这里可以不处理
				return requestParam;
			}

			try {
				if (lock != null) {
					lock.lock();
				}

				bankTradeService.barcodePaySuccessResult(requestParam.getPayOrderRequestRecord(),
					requestParam.getPayOrderResponseRecord());
			} finally {
				if (lock != null) {
					lock.unlock();
				}
			}

		} else {
			bankTradeService.barcodePayFailResult(requestParam.getPayOrderRequestRecord(),
				requestParam.getPayOrderResponseRecord());
		}

		return requestParam;
	}

}
