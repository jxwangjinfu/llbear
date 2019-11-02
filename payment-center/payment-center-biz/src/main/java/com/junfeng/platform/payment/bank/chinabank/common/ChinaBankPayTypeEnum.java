package com.junfeng.platform.payment.bank.chinabank.common;

import lombok.Getter;

/**
 * 中国银行支付方式
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月5日 上午10:38:14
 * @version 1.0
 */
@Getter
public enum ChinaBankPayTypeEnum {

    ZFBA("ZFBA", "支付宝"), WEIX("WEIX", "微信"), UPAY("UPAY", "银联二维码");

    private String value;
    private String description;

    private ChinaBankPayTypeEnum(String value, String description)
    {
        this.value = value;
        this.description = description;
    }


}
