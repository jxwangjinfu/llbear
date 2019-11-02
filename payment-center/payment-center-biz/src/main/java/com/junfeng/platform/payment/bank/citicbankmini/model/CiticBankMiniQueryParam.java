package com.junfeng.platform.payment.bank.citicbankmini.model;

import lombok.Data;

@Data
public class CiticBankMiniQueryParam {

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
