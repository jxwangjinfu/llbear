package com.junfeng.platform.payment.bank.jxbank.common;


import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

/**
 * 银行相关常量
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月17日 下午2:56:25
 * @version 1.0
 */
public class JxBankConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);
    /**
     * 江西银行
     */
    public static final String BANK_HOST = paymentPropertites.getJx_bank_host();
//		HaoposProperties.getInstance().getValue("jx_bank.host", "http://betaapi.speedpos.snsshop.net/");

}
