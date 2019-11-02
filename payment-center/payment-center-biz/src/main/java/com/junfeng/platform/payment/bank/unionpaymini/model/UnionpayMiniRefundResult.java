package com.junfeng.platform.payment.bank.unionpaymini.model;

import com.junfeng.platform.payment.bank.unionpaymini.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UnionpayMiniRefundResult extends BaseBean {

    private String msgId;
    private String msgType;
    private String msgSrc;
    private String srcReserve;
    private String responseTimestamp;
    private String mid;
    private String tid;
    private String merOrderId;
    private String merName;
    private String seqId;
    private String status;
    private String targetMid;
    private String targetOrderId;
    private String targetStatus;
    private String targetSys;
    private Double totalAmount;
    private Double refundAmount;
    private String refundFunds;
    private String refundFundsDesc;
    private Double refundInvoiceAmount;
    private String refundOrderId;
    private String refundTargetOrderId;

}
