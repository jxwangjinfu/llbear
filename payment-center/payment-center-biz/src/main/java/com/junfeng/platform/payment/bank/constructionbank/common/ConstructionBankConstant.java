package com.junfeng.platform.payment.bank.constructionbank.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

public class ConstructionBankConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);

	/**
     * 分行号
     */
    public final static String BRANCHID = paymentPropertites.getConstruction_bank_branchid();
//		HaoposProperties.getInstance().getValue("construction_bank_branchid","360000000");

    /**
     * 建行url
     */
    public final static String HOST = paymentPropertites.getConstruction_bank_url();
//		HaoposProperties.getInstance().getValue("construction_bank_url","https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_00_ENPAY");

    /**
     * 建行退款socktIp
     */
    public final static String SOCKET_IP = paymentPropertites.getConstruction_bank_socket_ip();
//		HaoposProperties.getInstance().getValue("construction_bank_socket_ip","101.132.44.172");

    /**
     * 建行退款socketPort
     */
    public final static String SOCKET_PORT = paymentPropertites.getConstruction_bank_socket_port();
//		HaoposProperties.getInstance().getValue("construction_bank_socket_port","12345");

    /**
     * 退款所用的大商户帐号
     */
    public final static String BIG_MCH_ID = paymentPropertites.getConstruction_bank_refund_big_mchid();
//		HaoposProperties.getInstance().getValue("construction_bank_refund_big_mchid","105000253317176");

    /**
     * 退款所用的大商户操作员帐号
     */
    public final static String BIG_MCH_OPERA = paymentPropertites.getConstruction_bank_refund_big_mchopera();
//		HaoposProperties.getInstance().getValue("construction_bank_refund_big_mchopera","105000253317176-002");

    /**
     * 退款所用操作员交易密码
     */
    public final static String MCH_OPERA_TRADE_PASSWORD = paymentPropertites.getConstruction_bank_refund_mchopera_trade_password();
//		HaoposProperties.getInstance().getValue("construction_bank_refund_mchopera_trade_password","147258");

}
