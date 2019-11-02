package com.junfeng.platform.payment.bank.cmbank.model;

import com.junfeng.platform.payment.bank.cmbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@XStreamAlias(value="xml",impl=CmBankQueryOrderResult.class)
public class CmBankQueryOrderResult extends BaseBean {

    /**
     * 交易状态
     */
    @XStreamAlias("trade_state")
    private String tradeState;

    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 平台订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 总金额
     */
    @XStreamAlias("totalFee")
    private Long totalFee;

    /**
     * 货币种类
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 附加信息
     */
    @XStreamAlias("attch")
    private String attach;

    /**
     * 付款银行
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

}
