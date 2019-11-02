package com.junfeng.platform.payment.bank.constructionbank.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

/**
 * 建行b扫c退款返回结果
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2018年11月28日 上午10:47:22
 * @version 1.0
 */

@Data
@XStreamAlias(value="TX",impl=ConstructionBankRefundResult.class)
public class ConstructionBankRefundResult {

    // 请求序列码
    @XStreamAlias(value = "REQUEST_SN")
    private String requestSn;

    // 商户号
    @XStreamAlias(value = "CUST_ID")
    private String custId;

    //
    @XStreamAlias(value = "TX_CODE")
    private String txCode;

    // 返回码
    @XStreamAlias(value = "RETURN_CODE")
    private String returnCode;

    // 返回码说明
    @XStreamAlias(value = "RETURN_MSG")
    private String returnMsg;

}
