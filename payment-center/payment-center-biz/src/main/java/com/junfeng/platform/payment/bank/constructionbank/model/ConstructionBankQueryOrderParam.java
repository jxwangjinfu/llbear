package com.junfeng.platform.payment.bank.constructionbank.model;

import lombok.Data;

/**
 * 建行b扫c查询请求参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 上午11:22:54
 * @version 1.0
 */
@Data
public class ConstructionBankQueryOrderParam {

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
     * 二维码类型
     */
    private String qrCodeType;


}
