package com.junfeng.platform.payment.bank.citicbank.model;

import com.junfeng.platform.payment.bank.citicbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 中信银行退款返回结果
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月22日 上午9:43:19
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias(value = "xml", impl = CiticBankRefundResult.class)
public class CiticBankRefundResult extends BaseBean {
    /**
     * 退款单
     */
    @XStreamAlias(value = "out_refund_no")
    private String outRefundNo;

    /**
     * 退款金额 单位：分
     */
    @XStreamAlias(value = "refund_fee")
    private Double refundFee;
}
