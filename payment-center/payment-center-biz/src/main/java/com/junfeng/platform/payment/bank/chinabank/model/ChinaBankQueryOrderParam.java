package com.junfeng.platform.payment.bank.chinabank.model;

import lombok.Data;

/**
 * 中国银行查询订单参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午5:47:04
 * @version 1.0
 */
@Data
public class ChinaBankQueryOrderParam {

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 设备终端号
     */
    private String posNo;

    /**
     * 银行的流水单号
     */
    private String orgSysTrace;

    /**
     * 商户key
     */
    private String key;

}
