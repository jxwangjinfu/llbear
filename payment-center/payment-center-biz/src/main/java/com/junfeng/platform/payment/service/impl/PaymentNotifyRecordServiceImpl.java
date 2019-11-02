package com.junfeng.platform.payment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.payment.api.entity.PaymentNotifyRecord;
import com.junfeng.platform.payment.service.PaymentNotifyRecordService;
import com.junfeng.platform.payment.mapper.PaymentNotifyRecordMapper;
import org.springframework.stereotype.Service;

/**
 * 支付成功通知记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:50
 */
@Service("paymentNotifyRecordService")
public class PaymentNotifyRecordServiceImpl extends ServiceImpl<PaymentNotifyRecordMapper, PaymentNotifyRecord> implements PaymentNotifyRecordService {

  /**
   * 支付成功通知记录简单分页查询
   * @param paymentNotifyRecord 支付成功通知记录
   * @return
   */
  @Override
  public IPage<PaymentNotifyRecord> getPaymentNotifyRecordPage(Page<PaymentNotifyRecord> page, PaymentNotifyRecord paymentNotifyRecord){
      return baseMapper.getPaymentNotifyRecordPage(page,paymentNotifyRecord);
  }

}
