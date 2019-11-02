package com.junfeng.platform.payment.service.impl;

import cn.hutool.core.lang.Assert;
import com.junfeng.platform.payment.api.entity.PaymentNotifyRecord;
import com.junfeng.platform.tc.api.entity.Order;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.mapper.PaymentOrderResponseRecordMapper;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.PaymentOrderResponseRecordService;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.junfeng.platform.tc.api.vo.PaymentNotify;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 支付订单回调记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:59
 */
@Service("paymentOrderResponseRecordService")
@AllArgsConstructor
public class PaymentOrderResponseRecordServiceImpl extends ServiceImpl<PaymentOrderResponseRecordMapper, PaymentOrderResponseRecord> implements PaymentOrderResponseRecordService {

	private final PaymentOrderRequestRecordService paymentOrderRequestRecordService;
	private final RemoteTradeOrderService remoteTradeOrderService;
	/**
	 * 支付订单回调记录简单分页查询
	 *
	 * @param paymentOrderResponseRecord 支付订单回调记录
	 * @return
	 */
	@Override
	public IPage<PaymentOrderResponseRecord> getPaymentOrderResponseRecordPage(Page<PaymentOrderResponseRecord> page, PaymentOrderResponseRecord paymentOrderResponseRecord) {
		return baseMapper.getPaymentOrderResponseRecordPage(page, paymentOrderResponseRecord);
	}


	/**
	 * 收到支付通知
	 * @return
	 * @Author wangk
	 * @Description //TODO
	 * @Date 17:55 2019-10-10
	 * @Param
	 **/
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PaymentOrderResponseRecord notifyPaySuccess(PaymentOrderRequestRecord record, PaymentOrderResponseRecord responseRecord) {

		// 1. 保存支付响应单
		responseRecord.insert();

		// 2. 支付请求单状态更新
		paymentOrderRequestRecordService.update(
			Wrappers.<PaymentOrderRequestRecord>lambdaUpdate()
			.eq(PaymentOrderRequestRecord::getPayOrderNo, responseRecord.getPayOrderNo())
			//1-支付成功
			.set(PaymentOrderRequestRecord::getState, 1)
		);

		//3. 通知订单中心 该订单完成支付。
		PaymentNotify paymentNotify = new PaymentNotify()
			.setMchOrderNo(record.getMchOrderNo())
			.setPayOrderNo(responseRecord.getPayOrderNo());

		PaymentNotifyRecord paymentNotifyRecord = new PaymentNotifyRecord().setMchOrderNo(record.getMchOrderNo()).setPayMchId(record.getPayMchId())
			.setPayOrderNo(record.getPayOrderNo()).setTradeOrderNo(record.getMchOrderNo())
			.setAmount(record.getAmount()).setPaymentModeCode(record.getPaymentModeCode())
			.setPayChannelCode(record.getPayChannelCode())
			.setLastNotifyTime(LocalDateTime.now())
			.setNotifyUrl(record.getNotifyUrl())
			.setState(1);

		R<Order> r = remoteTradeOrderService.remoteNotifyPaySuccess(paymentNotify, SecurityConstants.FROM_IN);

		Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), "支付成功通知发送失败！");

		paymentNotifyRecord.setNotifySuccessTime(LocalDateTime.now()).insert();
		return responseRecord;
	}
}
