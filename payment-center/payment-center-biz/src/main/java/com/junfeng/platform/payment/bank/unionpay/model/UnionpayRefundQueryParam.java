package com.junfeng.platform.payment.bank.unionpay.model;

import lombok.Data;

/**
 * 银联退款查询参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月7日 下午4:41:51
 * @version 1.0
 */
@Data
public class UnionpayRefundQueryParam {

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
