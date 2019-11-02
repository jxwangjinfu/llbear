package com.junfeng.platform.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 退款订单
 *
 * @author wangk
 * @date 2019-09-19 11:04:02
 */
public interface PaymentRefundOrderMapper extends BaseMapper<PaymentRefundOrder> {
  /**
    * 退款订单简单分页查询
    * @param paymentRefundOrder 退款订单
    * @return
    */
  IPage<PaymentRefundOrder> getPaymentRefundOrderPage(Page page, @Param("paymentRefundOrder") PaymentRefundOrder paymentRefundOrder);


}
