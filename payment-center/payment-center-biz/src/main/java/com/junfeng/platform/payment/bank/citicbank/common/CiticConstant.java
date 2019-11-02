package com.junfeng.platform.payment.bank.citicbank.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

public class CiticConstant {


	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);

	/**
     * 招商银行请求接口地址
     */
    public final static String CITIC_BANK_URL = paymentPropertites.getCitic_bank_host();
//		HaoposProperties.getInstance().getValue("citic_bank.host","https://pay.swiftpass.cn/pay/gateway");

}
