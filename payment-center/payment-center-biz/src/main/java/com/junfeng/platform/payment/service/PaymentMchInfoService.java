package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentMchInfo;

/**
 * 商户信息
 *
 * @author wangk
 * @date 2019-09-19 10:18:01
 */
public interface PaymentMchInfoService extends IService<PaymentMchInfo> {

  /**
   * 商户信息简单分页查询
   * @param paymentMchInfo 商户信息
   * @return
   */
  IPage<PaymentMchInfo> getPaymentMchInfoPage(Page<PaymentMchInfo> page, PaymentMchInfo paymentMchInfo);


}
