package com.junfeng.platform.payment.uitls.notify.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

/**
 * 支付中心常量定义
 * @projectName:tobacco-cloud-common
 * @author:chenyx
 * @date:2018年8月16日 下午2:54:06
 * @version 1.0
 */
public class PaymentCenterConst {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);

	public static final String KEY = paymentPropertites.getPayment_center_key();
//		HaoposConstant.PAYMENT_CENTER_KEY;



}
