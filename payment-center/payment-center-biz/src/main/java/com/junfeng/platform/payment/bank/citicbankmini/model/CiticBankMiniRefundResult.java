package com.junfeng.platform.payment.bank.citicbankmini.model;

import com.junfeng.platform.payment.bank.citicbankmini.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 中信银行小程序退款请求结果
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年2月20日 上午11:19:47
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias(value = "xml", impl = CiticBankMiniRefundResult.class)
public class CiticBankMiniRefundResult extends BaseBean {

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
