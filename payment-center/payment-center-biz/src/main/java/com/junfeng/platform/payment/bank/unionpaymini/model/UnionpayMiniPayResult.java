package com.junfeng.platform.payment.bank.unionpaymini.model;

import com.junfeng.platform.payment.bank.unionpaymini.model.base.BaseBean;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model.MiniPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UnionpayMiniPayResult extends BaseBean {

    private String msgId;
    private String msgType;
    private String msgSrc;
    private String srcReserve;
    private String responseTimestamp;
    private String merName;
    private String merOrderId;
    private String mid;
    private String tid;
    private String seqId;
    private String settleRefId;
    private String Status;
    private String totalAmount;
    private String targetOrderId;
    private String targetSys;
    private String targetStatus;
    private MiniPayRequest miniPayRequest;
    private String sign;
    private String signType;
    private String targetMid;

}
