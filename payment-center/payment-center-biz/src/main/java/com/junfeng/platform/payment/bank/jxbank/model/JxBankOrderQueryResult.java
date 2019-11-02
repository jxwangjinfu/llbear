package com.junfeng.platform.payment.bank.jxbank.model;

import com.junfeng.platform.payment.bank.jxbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单查询
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月18日 下午2:19:59
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@XStreamAlias(value="xml",impl=JxBankOrderQueryResult.class)
public class JxBankOrderQueryResult extends BaseBean {

    private String attach;

    //银行类型，采用字符串类型的银行标识
    @XStreamAlias("bank_type")
    private String bankType;

    //货币类型，符合 默认人民币： CNY ISO 4217 ，其他值列表详见货
    @XStreamAlias("fee_type")
    private String feeType;

    //商户号
    @XStreamAlias("mch_id")
    private String mchId;

    //商户系统内部的订单号,32 个字符内、
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    //用户在商户 appid 下的唯一标识
    @XStreamAlias("sub_openid")
    private String subOpenId;

    //订单支付时间，格式为 yyyy-MM-dd H
    @XStreamAlias("time_end")
    private String timeEnd;

    //总金额
    @XStreamAlias("total_fee")
    private Long totalFee;

    //SUCCESS—支付成功 REFUND—已完全 REFUNDING—部分退款 NOTPAY—未支 CLOSED—已关闭
    @XStreamAlias("trade_state")
    private String tradeState;

    //支付种类
    @XStreamAlias("trade_type")
    private String tradeType;

}
