package com.junfeng.platform.payment.bank.jxbank.common;

/**
 * 银行支付种类
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月18日 上午10:21:10
 * @version 1.0
 */
public enum JxBankPayTypeEnum {

    JXBANK_WXPAY_JSAPI("WXPAY.JSAPI","微信公众号支付"),
    JXBANK_WXPAY_APP("WXPAY.APP","微信APP支付"),
    JXBANK_WXPAY_NATIVE("WXPAY.NATIVE","微信扫码支付"),
    JXBANK_WXPAY_MICROPAY("WXPAY.MICROPAY","微信刷卡支付"),

    JXBANK_WXPAY_MINI("WXPAY.MINI","微信小程序支付"),

    JXBANK_WXPAY_MWEB("WXPAY.MWEB","微信 H5支付"),

    JXBANK_ALIPAY_JSAPI("ALIPAY.JSAPI","支付宝服务窗支付"),

    JXBANK_ALIPAY_APP("ALIPAY.APP","支付宝APP支付"),

    JXBANK_ALIPAY_NATIVE("ALIPAY.NATIVE","支付宝扫码支付"),

    JXBANK_ALIPAY_MICROPAY("ALIPAY.MICROPAY","支付宝刷卡支付");

    private String value;
    private String description;

    private JxBankPayTypeEnum(String value,String description) {
        this.value = value;
        this.description = description;
    }

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
}
