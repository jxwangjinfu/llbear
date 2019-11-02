package com.junfeng.platform.payment.common.type;

/**
 * 支付成功通知 通知状态
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月20日 下午7:31:39
 * @version 1.0
 */
public enum PayNotifyState {
    FAIL(0, "未成功"),SUCCESS(1, "成功");

    private Integer value;
    private String description;

    private PayNotifyState(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
