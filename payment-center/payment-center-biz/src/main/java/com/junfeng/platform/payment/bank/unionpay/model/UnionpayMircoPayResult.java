package com.junfeng.platform.payment.bank.unionpay.model;

import com.junfeng.platform.payment.bank.unionpay.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银联扫码支付返回值
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月28日 下午2:25:33
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class UnionpayMircoPayResult extends BaseBean {

    /**
     * 交易时间　格式：hhmmss
     */
    private String transactionTime;

    /**
     * 交易日期　格式：MMdd
     */
    private String transactionDate;

    /**
     * 结算日期　格式：MMdd
     */
    private String settlementDate;

    /**
     * 检索参考号
     */
    private String retrievalRefNum;

    /**
     * 订单金额
     */
    private String transactionAmount;

    /**
     * 营销联盟优惠后交易金额
     */
    private String actualTransactionAmount;

    /**
     * 实际支付金额
     */
    private String amount;

    /**
     * 订单号
     */
    private String orderId;


    private String marketingAllianceDiscountInstruction;

    private String thirdPartyDiscountInstruction;

    private String thirdPartyName;

    private String thirdPartyBuyerId;

    private String thirdPartyBuyerUserName;

    private String thirdPartyOrderId;

    private String thirdPartyPayInformation;

}
