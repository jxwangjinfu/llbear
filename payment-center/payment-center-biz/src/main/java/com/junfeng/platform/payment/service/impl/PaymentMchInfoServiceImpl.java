package com.junfeng.platform.payment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.api.entity.PaymentMchInfo;
import com.junfeng.platform.payment.service.PaymentMchInfoService;
import com.junfeng.platform.payment.mapper.PaymentMchInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 商户信息
 *
 * @author wangk
 * @date 2019-09-19 10:18:01
 */
@Service("paymentMchInfoService")
public class PaymentMchInfoServiceImpl extends ServiceImpl<PaymentMchInfoMapper, PaymentMchInfo> implements PaymentMchInfoService {

  /**
   * 商户信息简单分页查询
   * @param paymentMchInfo 商户信息
   * @return
   */
  @Override
  public IPage<PaymentMchInfo> getPaymentMchInfoPage(Page<PaymentMchInfo> page, PaymentMchInfo paymentMchInfo){
      return baseMapper.getPaymentMchInfoPage(page,paymentMchInfo);
  }

}
