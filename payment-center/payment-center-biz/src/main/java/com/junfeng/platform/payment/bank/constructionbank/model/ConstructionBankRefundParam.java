package com.junfeng.platform.payment.bank.constructionbank.model;

import lombok.Data;

/**
 * 建行b扫c退款请求参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2018年11月28日 上午10:46:55
 * @version 1.0
 */
@Data
public class ConstructionBankRefundParam {

    /**
     * 退款金额
     */
    private Double amount;

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 退款单号
     *
     */
    private String refundNo;

    /**
     * 子商户
     */
    private String mrchNo;

}
