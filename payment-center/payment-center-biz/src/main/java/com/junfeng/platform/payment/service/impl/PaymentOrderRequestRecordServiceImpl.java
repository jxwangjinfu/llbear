package com.junfeng.platform.payment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.mapper.PaymentOrderRequestRecordMapper;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.pig4cloud.pig.common.core.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付订单请求记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:55
 */
@Service("paymentOrderRequestRecordService")
public class PaymentOrderRequestRecordServiceImpl extends ServiceImpl<PaymentOrderRequestRecordMapper, PaymentOrderRequestRecord> implements PaymentOrderRequestRecordService {

	@Autowired
	private RemoteTradeOrderService remoteTradeOrderService;
	@Autowired
	private SnowFlake snowFlake;
	/**
	 * 支付订单请求记录简单分页查询
	 *
	 * @param paymentOrderRequestRecord 支付订单请求记录
	 * @return
	 */
	@Override
	public IPage<PaymentOrderRequestRecord> getPaymentOrderRequestRecordPage(Page<PaymentOrderRequestRecord> page, PaymentOrderRequestRecord paymentOrderRequestRecord) {
		return baseMapper.getPaymentOrderRequestRecordPage(page, paymentOrderRequestRecord);
	}

	@Override
	public boolean isMchOrderNo(String mchOrderNo) {
		int count = this.count(Wrappers.<PaymentOrderRequestRecord>lambdaQuery()
			.eq(PaymentOrderRequestRecord::getMchOrderNo, mchOrderNo)
		);

		return count > 0;
	}

	@Override
	public PaymentOrderRequestRecord createPaymentOrder(PaymentOrderRequestRecord paymentOrderRequestRecord) {

		//未支付
		paymentOrderRequestRecord.setState(0);
		//订单号
		paymentOrderRequestRecord.setPayOrderNo(String.valueOf(snowFlake.nextId()));
		paymentOrderRequestRecord.setPaymentModeCode("WX_JSAPI");
		this.save(paymentOrderRequestRecord);
		return paymentOrderRequestRecord;
	}
}
