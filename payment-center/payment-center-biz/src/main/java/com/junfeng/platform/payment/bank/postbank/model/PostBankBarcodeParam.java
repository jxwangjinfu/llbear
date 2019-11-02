package com.junfeng.platform.payment.bank.postbank.model;

import lombok.Data;

/**
 * 邮政银行b扫c 扫码参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午2:11:29
 * @version 1.0
 */
@Data
public class PostBankBarcodeParam {

    /**
     * 商户ID
     */
    private String mchId;

    /**
     *加密key
     */
    private String key;

    /**
     * 平台分配的appId
     */
    private String appId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 总金额
     */
    private String totalFee;

    /**
     * 扫码
     */
    private String authCode;

    /**
     *订单标题
     */
    private String body;


}
