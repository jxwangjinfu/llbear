package com.junfeng.platform.payment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;

/**
 * 支付订单请求记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:55
 */
public interface PaymentOrderRequestRecordService extends IService<PaymentOrderRequestRecord> {

  /**
   * 支付订单请求记录简单分页查询
   * @param paymentOrderRequestRecord 支付订单请求记录
   * @return
   */
  IPage<PaymentOrderRequestRecord> getPaymentOrderRequestRecordPage(Page<PaymentOrderRequestRecord> page, PaymentOrderRequestRecord paymentOrderRequestRecord);


	boolean isMchOrderNo(String mchOrderNo);

	/**
	 * 创建支付单
	 * @param
	 * * @return a
	 * @Author 2fx0one
	 * @Description
	 * @Date 2019-10-09 17:29
	**/
	PaymentOrderRequestRecord createPaymentOrder(PaymentOrderRequestRecord paymentOrderRequestRecord);

}
