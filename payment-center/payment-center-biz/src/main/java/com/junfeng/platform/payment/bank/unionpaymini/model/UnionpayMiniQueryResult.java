package com.junfeng.platform.payment.bank.unionpaymini.model;

import com.junfeng.platform.payment.bank.unionpaymini.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UnionpayMiniQueryResult extends BaseBean {

    private String msgId;
    private String msgType;
    private String msgSrc;
    private String srcReserve;
    private String responseTimestamp;
    private String mid;
    private String tid;
    private String instMid;
    private String seqId;
    private String settleRefId;
    private String refId;
    private String status;
    private String totalAmount;
    private String merName;
    private String merOrderId;
    private String targetOrderId;
    private String targetSys;
    private String targetStatus;
    private String buyerId;
    private String targetMid;
    private String bankCardNo;
    private String bankInfo;
    private String billFunds;
    private String billFundsDesc;
    private String buyerPayAmount;
    private String buyerUsername;
    private String couponAmount;
    private String invoiceAmount;
    private String payTime;
    private String receiptAmount;
    private String settleDate;
    private String subBuyerId;
}
