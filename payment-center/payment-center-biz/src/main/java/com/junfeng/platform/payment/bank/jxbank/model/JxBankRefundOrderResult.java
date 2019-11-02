package com.junfeng.platform.payment.bank.jxbank.model;

import com.junfeng.platform.payment.bank.jxbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 申请退款
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月18日 下午12:41:11
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@XStreamAlias(value="xml",impl=JxBankRefundOrderResult.class)
public class JxBankRefundOrderResult extends BaseBean{


    //商户系统内部的退款单号，商户系统内部唯 一，同一退款单号多次请求
    @XStreamAlias("out_refund_no")
    private String outFundNo;
    //退款总金额，订单总金额，单位为分，只能为 整数
    @XStreamAlias("refund_fee")
    private Integer refundFee;
    //订单总金额，单位为分，只能为整数
    @XStreamAlias("total_fee")
    private Integer totalFee;
    //平台退款单号
    @XStreamAlias("total_fee")
    private String refundNo;
    //平台订单号
    @XStreamAlias("order_no")
    private String orderNo;
    //商户系统内部的订单号字母
    @XStreamAlias("out_refund_no")
    private String outTradeNo;


}
