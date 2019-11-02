package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 支付订单请求记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:55
 */
public interface PaymentOrderRequestRecordMapper extends BaseMapper<PaymentOrderRequestRecord> {
  /**
    * 支付订单请求记录简单分页查询
    * @param paymentOrderRequestRecord 支付订单请求记录
    * @return
    */
  IPage<PaymentOrderRequestRecord> getPaymentOrderRequestRecordPage(Page page, @Param("paymentOrderRequestRecord") PaymentOrderRequestRecord paymentOrderRequestRecord);


}
