package com.junfeng.platform.payment.bank.constructionbank.model;

import lombok.Data;

/**
 * 建行b扫c支付请求参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 上午10:43:05
 * @version 1.0
 */
@Data
public class ConstructionBankBarcodeParam {

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

    /**
     * 柜台号
     */
    private String posId;

    /**
     * 扫码
     */
    private String qrCode;

    /**
     * 支付金额 单位：分
     */
    private Double amount;

    /**
     * 验证码
     */
    private String body;


}
