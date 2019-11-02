package com.junfeng.platform.payment.bank.postbank.model;

import com.junfeng.platform.payment.bank.postbank.model.base.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 邮政银行b扫c,退款返回结果
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午4:10:25
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class PostBankRefundOrderResult extends BaseBean{

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
     * 交易完成时间
     */
    private String finTime;


}
