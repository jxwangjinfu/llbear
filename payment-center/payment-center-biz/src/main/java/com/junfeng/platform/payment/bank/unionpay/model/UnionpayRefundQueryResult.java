package com.junfeng.platform.payment.bank.unionpay.model;

import com.junfeng.platform.payment.bank.unionpay.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayRefundQueryResult extends BaseBean {
    /**
     * 原始交易时间
     */
    private String originalTransactionTime;
    /**
     * 查询结果
     */
    private String queryResCode;
    /**
     * 查询结果描述
     */
    private String queryResDesc;
    /**
     * 原交易付款码
     */
    private String originalPayCode;
    /**
     * 原交易批次号
     */
    private String originalBatchNo;
    /**
     * 原交易流水号
     */
    private String originalSystemTraceNum;
    /**
     * 原检索参考号
     */
    private String origialRetrievalRefNum;
    /**
     * 原交易金额
     */
    private String originalTransactionAmount;
    /**
     * 银商订单号
     */
    private String orderId;
    /**
     * 已退货金额
     */
    private String refundedAmount;
    /**
     * 营销联盟优惠后交易金额
     */
    private String actualTransactionAmount;
    /**
     * 实际金额
     */
    private String amount;
    /**
     * 交易营销联盟优惠说明
     */
    private String marketingAllianceDiscountInstruction;
    /**
     * 第三方优惠说明
     */
    private String thirdPartyDiscountInstruction;
    /**
     * 第三方名称
     */
    private String thirdPartyName;
    /**
     * 第三方买家Id
     */
    private String thirdPartyBuyerId;
    /**
     * 第三方买家用户名
     */
    private String thirdPartyBuyerUserName;
    /**
     * 第三方订单号
     */
    private String thirdPartyOrderId;
    /**
     * 第三方支付信息
     */
    private String thirdPartyPayInformation;
}
