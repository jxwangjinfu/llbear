package com.junfeng.platform.payment.bank.chinabank.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

/**
 * 中国银行常量类
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午4:02:08
 * @version 1.0
 */
public class ChinaBankConstant {
	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);

    /**
     * 中国银行请求地址
     */
	public static final String CHINA_BANK_HOST = paymentPropertites.getChina_bank_host();
//		HaoposProperties.getInstance().getValue("china_bank.host","https://cloud.bankofchina.com/jx/webapp/jxboc-qrpay");

    /**
     * 签名所需key
     */
    public static final String SIGN_KEY = paymentPropertites.getChina_bank_key();
//	HaoposProperties.getInstance().getValue("china_bank.key","1AC$@!BGT98aw#C");

}
