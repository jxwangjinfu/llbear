package com.junfeng.platform.payment.bank.unionpay.model;

import com.junfeng.platform.payment.bank.unionpay.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银联撤消返回结果
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月6日 上午9:26:17
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayCancelOrderResult extends BaseBean {

    // 交易时间
    private String transactionTime;
    // 交易日期
    private String transactionDate;
    // 结算日期
    private String settlementDate;
    // 检索参考号
    private String retrievalRefNum;
    // 订单金额
    private String transactionAmount;
    // 实际交易金额
    private String actualTransactionAmount;
    // 第三方名称
    private String thirdPartyName;

}
