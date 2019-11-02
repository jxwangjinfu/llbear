package com.junfeng.platform.payment.bank.postbank.model;

import lombok.Data;

/**
 * 邮政银行b扫c,查询订单所传参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午3:13:47
 * @version 1.0
 */
@Data
public class PostBankQueryOrderParam {

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

}
