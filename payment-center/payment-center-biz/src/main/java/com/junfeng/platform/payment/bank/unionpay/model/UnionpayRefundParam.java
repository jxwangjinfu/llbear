package com.junfeng.platform.payment.bank.unionpay.model;

import lombok.Data;

/**
 * 退款请求参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月6日 上午11:02:01
 * @version 1.0
 */
@Data
public class UnionpayRefundParam {

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
    /**
     * 退款请求标识
     */
    private String refundRequestId;
    /**
     * 交易金额
     */
    private Long transactionAmount;

}
