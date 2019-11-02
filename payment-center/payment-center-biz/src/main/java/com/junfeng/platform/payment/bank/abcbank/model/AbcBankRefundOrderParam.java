package com.junfeng.platform.payment.bank.abcbank.model;

import com.junfeng.platform.payment.bank.abcbank.model.base.BaseParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 农业银行退款请求参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 下午4:21:11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AbcBankRefundOrderParam extends BaseParam{

    /**
     * 银行订单号
     */
    private String tradeOrderId;

    /**
     * 退款订单号
     */
    private String refundNo;

    /**
     * 退款金额
     */
    private String refundAmount;

}
