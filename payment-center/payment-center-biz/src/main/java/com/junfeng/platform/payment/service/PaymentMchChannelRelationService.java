package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentMchChannelRelation;

/**
 * 商户_支付通道关联表
 *
 * @author wangk
 * @date 2019-09-25 15:30:24
 */
public interface PaymentMchChannelRelationService extends IService<PaymentMchChannelRelation> {

  /**
   * 商户_支付通道关联表简单分页查询
   * @param paymentMchChannelRelation 商户_支付通道关联表
   * @return
   */
  IPage<PaymentMchChannelRelation> getPaymentMchChannelRelationPage(Page<PaymentMchChannelRelation> page, PaymentMchChannelRelation paymentMchChannelRelation);


	PaymentMchChannelRelation getEnableByPayMchId(Long payMchId);
}
