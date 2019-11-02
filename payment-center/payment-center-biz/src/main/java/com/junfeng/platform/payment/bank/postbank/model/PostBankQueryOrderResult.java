package com.junfeng.platform.payment.bank.postbank.model;

import com.junfeng.platform.payment.bank.postbank.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午3:16:41
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class PostBankQueryOrderResult extends BaseBean{

    /**
     * 返回码
     */
    private String retCode;

    /**
     * 返回信息
     */
    private String retMsg;

    /**
     * 商户号
     */
    private String cusId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 收银宝平台的交易流水号
     */
    private String trxId;

    /**
     * 商户交易单号
     */
    private String reqSn;

    /**
     * 交易状态
     */
    private String trxStatus;

    /**
     * 交易类型
     */
    private String trxCode;

    /**
     * 交易金额
     */
    private String trxAmt;

    /**
     * 交易完成时间
     */
    private String finTime;

}
