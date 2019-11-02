package com.junfeng.platform.payment.bank.citicbank.common;

import lombok.Getter;

/**
 * 通信标识
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 下午3:04:29
 * @version 1.0
 */
@Getter
public enum CiticBankResultStatusEnum {

    SUCCESS("0","成功"),
    NEED_QUERY_Y("Y","需要调用查询接口"),
    NEED_QUERY_N("N","支付失败");

    private String value;
    private String description;


    private CiticBankResultStatusEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
