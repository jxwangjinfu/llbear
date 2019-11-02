package com.junfeng.platform.payment.bank.citicbankmini.model;

import lombok.Data;

/**
 * 中信小程序支付参数类
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年1月23日 下午2:32:35
 * @version 1.0
 */
@Data
public class CiticBankMiniPaymentParam {

    private String mchId;

    /**
     * 总金额 单位：分
     */
    private int totalFee;

    /**
     * 服务类型
     */
    private String service;

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 加密key
     */
    private String key;

    /**
     * openId
     */
    private String subOpenId;

    /**
     * 小程序的appId
     */
    private String subAppId;

    /**
     * 商户机器IP
     */
    private String mchCreateIp;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 订单状态
     */
    private String body;

}
