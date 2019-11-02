package com.junfeng.platform.payment.bank.chinabank.model;

import lombok.Data;

/**
 * 中国银行扫码支付参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午4:45:57
 * @version 1.0
 */
@Data
public class ChinaBankBarcodeParam {

    /**
     * 设备终端号
     */
    private String posNo;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 支付二维码
     */
    private String authCode;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户key
     */
    private String key;

}
