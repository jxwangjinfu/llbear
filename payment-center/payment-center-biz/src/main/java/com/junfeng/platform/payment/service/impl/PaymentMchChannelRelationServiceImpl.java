package com.junfeng.platform.payment.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.common.type.PayMchChannelRelationState;
import com.junfeng.platform.payment.api.entity.PaymentMchChannelRelation;
import com.junfeng.platform.payment.service.PaymentMchChannelRelationService;
import com.junfeng.platform.payment.mapper.PaymentMchChannelRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户_支付通道关联表
 *
 * @author wangk
 * @date 2019-09-25 15:30:24
 */
@Service("paymentMchChannelRelationService")
public class PaymentMchChannelRelationServiceImpl extends ServiceImpl<PaymentMchChannelRelationMapper, PaymentMchChannelRelation> implements PaymentMchChannelRelationService {

	/**
	 * 商户_支付通道关联表简单分页查询
	 *
	 * @param paymentMchChannelRelation 商户_支付通道关联表
	 * @return
	 */
	@Override
	public IPage<PaymentMchChannelRelation> getPaymentMchChannelRelationPage(Page<PaymentMchChannelRelation> page, PaymentMchChannelRelation paymentMchChannelRelation) {
		return baseMapper.getPaymentMchChannelRelationPage(page, paymentMchChannelRelation);
	}


	@Override
	public PaymentMchChannelRelation getEnableByPayMchId(Long payMchId) {
		List<PaymentMchChannelRelation> list = this.list(Wrappers.<PaymentMchChannelRelation>lambdaQuery()
				.eq(PaymentMchChannelRelation::getPayMchId, payMchId)
				.eq(PaymentMchChannelRelation::getState, PayMchChannelRelationState.ENABLE.getValue())
			);

		if (CollectionUtil.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
}
