package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 支付订单回调记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:59
 */
public interface PaymentOrderResponseRecordMapper extends BaseMapper<PaymentOrderResponseRecord> {
  /**
    * 支付订单回调记录简单分页查询
    * @param paymentOrderResponseRecord 支付订单回调记录
    * @return
    */
  IPage<PaymentOrderResponseRecord> getPaymentOrderResponseRecordPage(Page page, @Param("paymentOrderResponseRecord") PaymentOrderResponseRecord paymentOrderResponseRecord);


}
