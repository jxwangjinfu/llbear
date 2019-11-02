package com.junfeng.platform.payment.bank.unionpay.model;

import lombok.Data;

@Data
public class UnionpayQueryOrderParam {

    /**
     * 商户号
     */
    private String merchantCode;
    /**
     * 终端号
     */
    private String terminalCode;
    /**
     * 中心支付订单
     */
    private String merchantOrderId;

}
