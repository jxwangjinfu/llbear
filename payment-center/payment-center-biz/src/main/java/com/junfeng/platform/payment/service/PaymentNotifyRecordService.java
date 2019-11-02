package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentNotifyRecord;

/**
 * 支付成功通知记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:50
 */
public interface PaymentNotifyRecordService extends IService<PaymentNotifyRecord> {

  /**
   * 支付成功通知记录简单分页查询
   * @param paymentNotifyRecord 支付成功通知记录
   * @return
   */
  IPage<PaymentNotifyRecord> getPaymentNotifyRecordPage(Page<PaymentNotifyRecord> page, PaymentNotifyRecord paymentNotifyRecord);


}
