package com.junfeng.platform.payment.bank.cmbank.model;

import lombok.Data;

/**
 * 招商银行查询订单参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 下午4:24:15
 * @version 1.0
 */
@Data
public class CmBankQueryOrderParam {

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
