package com.junfeng.platform.payment.bank.citicbank.model;

import lombok.Data;

/**
 * 刷卡支付参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 下午2:29:04
 * @version 1.0
 */
@Data
public class CiticBankBarcodeParam {

    /**
     * 订单描述
     */
    private String body;

    /**
     * 总金额
     */
    private Long totalFee;

    /**
     * 订单生成的机器 IP
     */
    private String mchCreateIp;

    /**
     * 授权码
     */
    private String authCode;

    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 加密key
     */
    private String key;

    /**
     * 商户订单号
     */
    private String outTradeNo;

}
