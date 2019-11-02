package com.junfeng.platform.payment.bank.jxbank.model;

import lombok.Data;

@Data
public class JxBankRefundOrderParam {

    /**
     * 支付中心退款单号
     */
    private String refundOrderNo;
    /**
     * 支付中心订单号
     */
    private String payOrderNo;
    /**
     * 退款费用，单位：分
     */
    private Long refundFee;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 加密KEY值
     */
    private String key;

}
