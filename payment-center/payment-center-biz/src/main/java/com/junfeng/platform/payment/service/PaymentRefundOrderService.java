package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;

/**
 * 退款订单
 *
 * @author wangk
 * @date 2019-09-19 11:04:02
 */
public interface PaymentRefundOrderService extends IService<PaymentRefundOrder> {

  /**
   * 退款订单简单分页查询
   * @param paymentRefundOrder 退款订单
   * @return
   */
  IPage<PaymentRefundOrder> getPaymentRefundOrderPage(Page<PaymentRefundOrder> page, PaymentRefundOrder paymentRefundOrder);


}
