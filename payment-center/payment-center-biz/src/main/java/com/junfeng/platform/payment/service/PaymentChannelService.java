package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentChannel;

/**
 * 支付通道
 *
 * @author wangk
 * @date 2019-09-19 11:03:43
 */
public interface PaymentChannelService extends IService<PaymentChannel> {

  /**
   * 支付通道简单分页查询
   * @param paymentChannel 支付通道
   * @return
   */
  IPage<PaymentChannel> getPaymentChannelPage(Page<PaymentChannel> page, PaymentChannel paymentChannel);


}
