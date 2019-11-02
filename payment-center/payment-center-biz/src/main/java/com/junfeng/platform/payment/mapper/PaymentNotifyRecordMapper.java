package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentNotifyRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 支付成功通知记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:50
 */
public interface PaymentNotifyRecordMapper extends BaseMapper<PaymentNotifyRecord> {
  /**
    * 支付成功通知记录简单分页查询
    * @param paymentNotifyRecord 支付成功通知记录
    * @return
    */
  IPage<PaymentNotifyRecord> getPaymentNotifyRecordPage(Page page, @Param("paymentNotifyRecord") PaymentNotifyRecord paymentNotifyRecord);


}
