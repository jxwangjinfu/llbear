package com.junfeng.platform.payment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.api.entity.PaymentChannel;
import com.junfeng.platform.payment.service.PaymentChannelService;
import com.junfeng.platform.payment.mapper.PaymentChannelMapper;
import org.springframework.stereotype.Service;

/**
 * 支付通道
 *
 * @author wangk
 * @date 2019-09-19 11:03:43
 */
@Service("paymentChannelService")
public class PaymentChannelServiceImpl extends ServiceImpl<PaymentChannelMapper, PaymentChannel> implements PaymentChannelService {

  /**
   * 支付通道简单分页查询
   * @param paymentChannel 支付通道
   * @return
   */
  @Override
  public IPage<PaymentChannel> getPaymentChannelPage(Page<PaymentChannel> page, PaymentChannel paymentChannel){
      return baseMapper.getPaymentChannelPage(page,paymentChannel);
  }

}
