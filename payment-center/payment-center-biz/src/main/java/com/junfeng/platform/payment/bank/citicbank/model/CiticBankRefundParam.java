package com.junfeng.platform.payment.bank.citicbank.model;

import lombok.Data;

/**
 * 中信银行请求参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月22日 上午9:42:01
 * @version 1.0
 */
@Data
public class CiticBankRefundParam {

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
     * 总金额　单位：分
     */
    private Long totalFee;

    /**
     * 退款总金额　单位：分
     */
    private Long refundFee;

}
