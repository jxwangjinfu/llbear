package com.junfeng.platform.payment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.PaymentRefundOrderService;
import com.junfeng.platform.payment.mapper.PaymentRefundOrderMapper;
import org.springframework.stereotype.Service;

/**
 * 退款订单
 *
 * @author wangk
 * @date 2019-09-19 11:04:02
 */
@Service("paymentRefundOrderService")
public class PaymentRefundOrderServiceImpl extends ServiceImpl<PaymentRefundOrderMapper, PaymentRefundOrder> implements PaymentRefundOrderService {

  /**
   * 退款订单简单分页查询
   * @param paymentRefundOrder 退款订单
   * @return
   */
  @Override
  public IPage<PaymentRefundOrder> getPaymentRefundOrderPage(Page<PaymentRefundOrder> page, PaymentRefundOrder paymentRefundOrder){
      return baseMapper.getPaymentRefundOrderPage(page,paymentRefundOrder);
  }

}
