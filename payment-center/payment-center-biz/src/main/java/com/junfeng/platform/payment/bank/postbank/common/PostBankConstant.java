package com.junfeng.platform.payment.bank.postbank.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

public class PostBankConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);

	/**
     * 邮政银行接口url
     */
    public final static String POST_BANK_URL = paymentPropertites.getPost_bank_host();
//		HaoposProperties.getInstance().getValue("post_bank.host", "https://vsp.allinpay.com/apiweb/unitorder/scanqrpay");

}
