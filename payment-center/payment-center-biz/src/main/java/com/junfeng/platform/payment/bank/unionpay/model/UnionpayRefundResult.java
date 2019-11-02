package com.junfeng.platform.payment.bank.unionpay.model;

import com.junfeng.platform.payment.bank.unionpay.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月6日 上午11:24:32
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class UnionpayRefundResult extends BaseBean{

    /**
     * 交易时间
     */
    private String transactionTime;
    /**
     * 交易日期
     */
    private String transactionDate;
    /**
     * 结算日期
     */
    private String settlementDate;
    /**
     * 检索参考号
     */
    private String retrievalRefNum;

}
