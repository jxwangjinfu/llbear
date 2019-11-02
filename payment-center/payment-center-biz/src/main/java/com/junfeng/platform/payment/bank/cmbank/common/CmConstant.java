package com.junfeng.platform.payment.bank.cmbank.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

public class CmConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);

	/**
     * 招商银行请求接口地址
     */
    public final static String CM_BANK_URL = paymentPropertites.getCm_bank_host();
//		HaoposProperties.getInstance().getValue("cm_bank.host","https://pay.swiftpass.cn/pay/gateway");

}
