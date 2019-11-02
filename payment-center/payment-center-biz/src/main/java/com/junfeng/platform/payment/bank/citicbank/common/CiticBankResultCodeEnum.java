package com.junfeng.platform.payment.bank.citicbank.common;

import lombok.Getter;

/**
 * 业务结果
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 下午3:08:27
 * @version 1.0
 */
@Getter
public enum CiticBankResultCodeEnum {

    SUCCESS("0", "成功");

    private String value;
    private String description;

    private CiticBankResultCodeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
