package com.junfeng.platform.payment.bank.abcbank.model;

import com.junfeng.platform.payment.bank.abcbank.model.base.BaseResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 农业银行退款返回结果
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 下午4:24:05
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AbcBankRefundOrderResult extends BaseResult{

    /**
     * 退款订单号
     */
    private String rejectNo;

    /**
     * 第三方订单号
     */
    private String rejectOrderId;

    /**
     * 退款时间 格式：yyyyMMddHHmmSS
     */
    private String rejectTime;

    /**
     * 交易返回码
     */
    private String returnCode;

    /**
     * 交易返回信息
     */
    private String returnMsg;
}
