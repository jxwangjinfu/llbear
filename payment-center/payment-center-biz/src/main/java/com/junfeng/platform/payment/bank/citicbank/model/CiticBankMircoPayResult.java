package com.junfeng.platform.payment.bank.citicbank.model;

import com.junfeng.platform.payment.bank.citicbank.model.base.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 招商银行请求结果
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 上午11:13:05
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@XStreamAlias(value="xml",impl=CiticBankMircoPayResult.class)
public class CiticBankMircoPayResult extends BaseBean {
    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 支付结果
     */
    @XStreamAlias("pay_result")
    private Integer payResult;

    /**
     * 总金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    @XStreamAlias("need_query")
    private String needQuery;

}
