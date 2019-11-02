package com.junfeng.platform.payment.bank.jxbank.model;

import lombok.Data;
/**
 * 江西银行条码支付参数
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月22日 上午10:18:06
 * @version 1.0
 */
@Data
public class JxBankBarcodeParam {
    /**
     * 支付描述
     */
    private String body;

    /**
     * 微信或者支付宝的支付码
     */
    private String authCode;

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 机器 IP
     */
    private String spbillCreateIp;

    /**
     * 总金额
     */
    private String totalFee;
    /**
     * 银行商户账号
     */
    private String mchId;
    /**
     * 银行商户账号密钥
     */
    private String key;

}
