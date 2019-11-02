package com.junfeng.platform.payment.bank.unionpay.model;

import lombok.Data;

@Data
public class UnionpayBarcodeParam {

    /**
     * 商户号
     */
    private String merchantCode;
    /**
     * 终端号
     */
    private String terminalCode;
    /**
     * 交易金额
     */
    private String transactionAmount;

    /**
     * 商户订单号
     */
    private String merchantOrderId;
    /**
     * 商户备注
     */
    private String merchantRemark;
    /**
     * 支付码
     */
    private String payCode;
    /**
     * 验证二维码
     */
    private String body;

}
