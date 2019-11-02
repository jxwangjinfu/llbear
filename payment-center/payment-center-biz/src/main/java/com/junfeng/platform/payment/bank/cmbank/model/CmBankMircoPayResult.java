package com.junfeng.platform.payment.bank.cmbank.model;

import com.junfeng.platform.payment.bank.cmbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@XStreamAlias(value="xml",impl=CmBankMircoPayResult.class)
public class CmBankMircoPayResult extends BaseBean{
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

}
