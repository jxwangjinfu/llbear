package com.junfeng.platform.payment.bank.cmbank.common;

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
public enum CmBankResultCodeEnum {

    SUCCESS("0", "成功");

    private String value;
    private String description;


    private CmBankResultCodeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
