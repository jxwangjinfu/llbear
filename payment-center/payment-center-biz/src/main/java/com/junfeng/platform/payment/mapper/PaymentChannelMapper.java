package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentChannel;
import org.apache.ibatis.annotations.Param;

/**
 * 支付通道
 *
 * @author wangk
 * @date 2019-09-19 11:03:43
 */
public interface PaymentChannelMapper extends BaseMapper<PaymentChannel> {
  /**
    * 支付通道简单分页查询
    * @param paymentChannel 支付通道
    * @return
    */
  IPage<PaymentChannel> getPaymentChannelPage(Page page, @Param("paymentChannel") PaymentChannel paymentChannel);


}
