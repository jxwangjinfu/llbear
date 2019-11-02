package com.junfeng.platform.payment.bank.unionpay.common;

/**
 * 银联支付方式
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月28日 下午3:21:00
 * @version 1.0
 */
public enum UnionpayPayMode {
    E_CASH("E_CASH","电子现金"),
    SOUNDWAVE("SOUNDWAVE","声波"),
    NFC("NFC","NFC"),
    CODE_SCAN("CODE_SCAN","扫码"),
    MANUAL("MANUAL","手输");

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
    private UnionpayPayMode(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
