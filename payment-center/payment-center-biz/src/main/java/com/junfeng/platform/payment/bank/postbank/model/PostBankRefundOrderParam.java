package com.junfeng.platform.payment.bank.postbank.model;

import lombok.Data;

/**
 *
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午4:06:43
 * @version 1.0
 */
@Data
public class PostBankRefundOrderParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     *加密key
     */
    private String key;

    /**
     * 平台分配的appId
     */
    private String appId;

    /**
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 原交易订单号
     */
    private String outTradeNo;

    /**
     * 退款金额
     */
    private String refundAmount;

}
