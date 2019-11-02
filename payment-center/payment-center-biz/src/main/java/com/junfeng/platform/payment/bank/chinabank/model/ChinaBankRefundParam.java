package com.junfeng.platform.payment.bank.chinabank.model;

import lombok.Data;

/**
 * 中国银行退款请求
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午6:15:15
 * @version 1.0
 */
@Data
public class ChinaBankRefundParam {

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 设备终端号
     */
    private String posNo;

    /**
     * 支付方式
     */
    private String payType;


    /**
     * 银行的流水单号
     */
    private String orgSysTrace;

    /**
     * 退款订单号
     */
    private String VFTradeNo;

    /**
     * 原交易日期时间
     */
    private String orgTxnTime;
    /**
     *退款金额
     */
    private Long txnAmt;

    /**
     * 商户key
     */
    private String key;

}
