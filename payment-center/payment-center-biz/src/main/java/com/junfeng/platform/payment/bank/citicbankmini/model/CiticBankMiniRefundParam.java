package com.junfeng.platform.payment.bank.citicbankmini.model;

import lombok.Data;

@Data
public class CiticBankMiniRefundParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 加密key
     */
    private String key;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 总金额 单位：分
     */
    private Long totalFee;

    /**
     * 退款总金额 单位：分
     */
    private Long refundFee;

}
