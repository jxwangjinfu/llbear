package com.junfeng.platform.payment.bank.unionpaymini.model;

import lombok.Data;

@Data
public class UnionpayMiniUnifiedorderParam {
    /**
     * 消息类型 微信:wx.unifiedOrder 必传
     */
    private String msgType;

    /**
     * 报文请求时间，格式yyyy-MM-dd HH:mm:ss 必传
     */
    private String requestTimestamp;

    /**
     * 消息来源 必传
     */
    private String msgSrc;

    /**
     * 商户号 必传
     */
    private String mid;


    /**
     * 终端号 必传
     */
    private String tid;

    /**
     * 机构商户号 必传
     */
    private String instMid;

    /**
     * 支付中心订单号 必传
     */
    private String merOrderId;

    /**
     * 支付总金额，单位分 必传
     * min = 1
     * max = 100000000
     */
    private String totalAmount;

    /**
     * 交易类型 值为MINI
     */
    private String tradeType;

    /**
     * 支付结果通知地址
     */
    private String notifyUrl;

    /**
     * 用户子标识
     */
    private String subOpenId;

}
