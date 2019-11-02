package com.junfeng.platform.payment.bank.jxbank.model;

import com.junfeng.platform.payment.bank.jxbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退款查询
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月18日 下午2:49:22
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@XStreamAlias(value="xml",impl=JxBankRefundQueryResult.class)
public class JxBankRefundQueryResult extends BaseBean{


    //商户号
    @XStreamAlias("mch_id")
    private String mchId;

    //商户系统内部的退款单号
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    //退款总金额，订单总金额，单位为 分，只能为整数
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    //总金额
    @XStreamAlias("total_fee")
    private Integer totalFee;

    //平台退款单号
    @XStreamAlias("refund_no")
    private String refundNo;

    //SUCCESS—退款成功 PROCESSING—退款处理中 FAIL-退款失败
    @XStreamAlias("refund_status")
    private String refundStatus;

}
