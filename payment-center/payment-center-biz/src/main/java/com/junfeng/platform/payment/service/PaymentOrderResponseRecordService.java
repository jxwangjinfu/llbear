package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;

/**
 * 支付订单回调记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:59
 */
public interface PaymentOrderResponseRecordService extends IService<PaymentOrderResponseRecord> {

	/**
	 * 支付订单回调记录简单分页查询
	 *
	 * @param paymentOrderResponseRecord 支付订单回调记录
	 * @return
	 */
	IPage<PaymentOrderResponseRecord> getPaymentOrderResponseRecordPage(Page<PaymentOrderResponseRecord> page, PaymentOrderResponseRecord paymentOrderResponseRecord);


	PaymentOrderResponseRecord notifyPaySuccess(PaymentOrderRequestRecord record, PaymentOrderResponseRecord paymentOrderResponseRecord);
}
