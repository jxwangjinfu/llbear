package com.junfeng.platform.payment.bank.constructionbank.common;

/**
 * 二维码类型
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 下午2:42:48
 * @version 1.0
 */
public enum CcBankQrCodeType {

    WXPAY("2", "微信"), ALIPAY("3", "支付宝"),DRAGONPAY("1","龙支付");

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

    /**
     * @param value
     * @param description
     */
    private CcBankQrCodeType(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
