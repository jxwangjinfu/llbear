package com.junfeng.platform.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.junfeng.platform.tc.api.vo.PaymentNotify;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;

import cn.hutool.core.lang.Assert;
import lombok.AllArgsConstructor;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-21 13:47
 * @projectName pig
 */
@Service
@AllArgsConstructor
public class UserPaymentService {
    private final RemoteTradeOrderService remoteTradeOrderService;

    @Transactional(rollbackFor = Exception.class)
	public R<Order> paymentRefund(PaymentOrderRequestRecord record) {

		record.update(Wrappers.<PaymentOrderRequestRecord>lambdaUpdate()
			.eq(PaymentOrderRequestRecord::getId, record.getId())
			.set(PaymentOrderRequestRecord::getState, 9)
			.set(PaymentOrderRequestRecord::getRefundState, 1)
		);

		PaymentNotify paymentNotify = new PaymentNotify().setPayOrderNo(record.getPayOrderNo()).setMchOrderNo(record.getMchOrderNo());
		new PaymentRefundOrder()
			.setPayOrderNo(record.getPayOrderNo()).setMchOrderNo(record.getMchOrderNo())
			.setPayMchId(record.getPayMchId())
			.setRefundOrderNo(record.getMchOrderNo())
			.setTradeOrderNo(record.getTradeOrderNo()).setPaymentModeCode(record.getPaymentModeCode())
			.setPayAmount(record.getAmount()).setRefundAmount(record.getAmount()).setState(2).insert();
		R<Order> r =  remoteTradeOrderService.remoteNotifyPayRefund(paymentNotify, SecurityConstants.FROM_IN);
		Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), "支付退款通知 >> 通知交易中心失败！");


		return r;

	}
}
