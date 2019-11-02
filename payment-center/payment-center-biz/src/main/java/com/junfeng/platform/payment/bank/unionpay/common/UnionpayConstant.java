package com.junfeng.platform.payment.bank.unionpay.common;

import com.junfeng.platform.payment.bank.PaymentPropertites;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;

public class UnionpayConstant {

	private final static PaymentPropertites paymentPropertites = SpringContextHolder.getBean(PaymentPropertites.class);
    /**
     * 访问接口host
     */
    public final static String HOST = paymentPropertites.getUnion_pay_host();
//		HaoposProperties.getInstance().getValue("union_pay.host", "http://58.247.0.18:29015") + "/v2/poslink/transaction";

    /**
     * 应用ID
     */
    public final static String APPID = paymentPropertites.getUnion_pay_appid();
//		HaoposProperties.getInstance().getValue("union_pay.appid","f0ec96ad2c3848b5b810e7aadf369e2f");

    /**
     * appkey
     */
    public final static String APPKEY = paymentPropertites.getUnion_pay_appkey();
//		HaoposProperties.getInstance().getValue("union_pay.appkey","775481e2556e4564985f5439a5e6a277");

}
