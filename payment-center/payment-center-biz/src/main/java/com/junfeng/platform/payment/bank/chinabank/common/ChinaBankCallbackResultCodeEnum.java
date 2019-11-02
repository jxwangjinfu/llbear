package com.junfeng.platform.payment.bank.chinabank.common;

import lombok.Getter;

/**
 * 中国银行响应码
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月5日 上午10:06:06
 * @version 1.0
 */
@Getter
public enum ChinaBankCallbackResultCodeEnum {

    SUCCESS("00", "成功");

    private String value;
    private String description;

    private ChinaBankCallbackResultCodeEnum(String value, String description)
    {
        this.value = value;
        this.description = description;
    }




}
