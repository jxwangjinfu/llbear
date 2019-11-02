package com.junfeng.platform.payment.bank.cmbank.common;

/**
 * 通信标识
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 下午3:04:29
 * @version 1.0
 */
public enum CmBankResultStatusEnum {

    SUCCESS("0","成功");

    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private CmBankResultStatusEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
