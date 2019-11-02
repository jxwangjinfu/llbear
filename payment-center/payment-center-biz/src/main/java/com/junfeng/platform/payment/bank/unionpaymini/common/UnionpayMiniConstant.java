package com.junfeng.platform.payment.bank.unionpaymini.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

public class UnionpayMiniConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);
    /**
     * 访问接口host
     */
    public final static String HOST = paymentPropertites.getUnion_pay_mini_host();
//		HaoposProperties.getInstance().getValue("union_pay_mini.host", "https://qr.chinaums.com/netpay-route-server/api/");

    /**
     * 应用ID
     */
    public final static String APPID = paymentPropertites.getUnion_pay_mini_appid();
//		HaoposProperties.getInstance().getValue("union_pay_mini_appid","f0ec96ad2c3848b5b810e7aadf369e2f");

    /**
     * appkey
     */
    public final static String APPKEY = paymentPropertites.getUnion_pay_mini_appkey();
//		HaoposProperties.getInstance().getValue("union_pay_mini_appkey","775481e2556e4564985f5439a5e6a277");

    /**
     * 机构商户号
     */
    public final static String MINI_INST_MID = paymentPropertites.getNion_pay_mini_instmid();
//		HaoposProperties.getInstance().getValue("union_pay_mini.instmid", "MINIDEFAULT");


    /**
     * 通讯密钥
     */
    public final static String MD5KEY = paymentPropertites.getUnion_pay_mini_key();
//		HaoposProperties.getInstance().getValue("union_pay_mini_key", "p2tTYAYKixPQzkkcprrwFEpGGDBhANBHKFjxjt5Tk6m4xQRh");

	/**
	 * 来源编号
	 */
	public final static String MSGSRCID = "4691";

	/**
     * 消息来源
     */
    public final static String MSGSRC= "WWW.JIANGXYC.COM";



}
