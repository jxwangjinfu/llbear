package com.junfeng.platform.payment.bank.chinabank.model;

import lombok.Data;

/**
 * 中国银行请求返回结果
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午4:57:27
 * @version 1.0
 */
@Data
public class ChinaBankCallbackResult {

    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应信息
     */
    private String respMsg;

    /**
     * 终端流水号
     */
    private String traceNo;

    /**
     * 流水号
     */
    private String sysTrace;

    /**
     * 交易时间
     */
    private String txnTime;

    /**
     * 银行所给支付订单号
     */
    private String tradeNo;

    /**
     * 退款单号
     */
    private String vfTradeNo;

}
