package com.junfeng.platform.payment.bank.constructionbank.model;

import lombok.Data;

/**
 * 建行b扫c查询结果
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 上午11:26:16
 * @version 1.0
 */
@Data
public class ConstructionBankQueryOrderResult {

    private String result;
    private String orderId;
    private String amount;
    private String waitTime;
    private String errCode;
    private String errMsg;
    private String sign;
    private String qrCodeType;

}
