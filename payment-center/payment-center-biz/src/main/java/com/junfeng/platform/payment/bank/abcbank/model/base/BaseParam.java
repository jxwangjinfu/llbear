package com.junfeng.platform.payment.bank.abcbank.model.base;

import lombok.Data;

@Data
public class BaseParam {

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 终端号
     */
    private String terminateCode;

    /**
     * 银行提供公钥
     */
    private String bankPublicKey;

    /**
     * 商户的私钥
     */
    private String mchPrivateKey;

    /**
     * 商户订单号
     */
    private String outTradeNo;

}
