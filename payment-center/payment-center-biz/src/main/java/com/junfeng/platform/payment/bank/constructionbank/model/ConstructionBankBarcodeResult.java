package com.junfeng.platform.payment.bank.constructionbank.model;

import lombok.Data;

/**
 * 建行b扫c支付返回结果
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 上午10:51:01
 * @version 1.0
 */
@Data
public class ConstructionBankBarcodeResult {

    private String result;
    private String orderId;
    private String amount;
    private String qrCodeType;
    private String waitTime;
    private String traceId;
    private String errCode;
    private String errMsg;
    private String sign;


}
