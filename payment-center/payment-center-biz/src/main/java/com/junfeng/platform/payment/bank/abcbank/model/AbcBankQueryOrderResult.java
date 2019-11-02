package com.junfeng.platform.payment.bank.abcbank.model;

import com.junfeng.platform.payment.bank.abcbank.model.base.BaseResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AbcBankQueryOrderResult extends BaseResult{

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 第三方订单号
     */
    private String orderId;

    /**
     * 支付成功时间 格式：yyyyMMddHHmmSS
     */
    private String payTime;

    /**
     * 交易返回码
     */
    private String returnCode;

    /**
     * 交易返回信息
     */
    private String returnMsg;



}
