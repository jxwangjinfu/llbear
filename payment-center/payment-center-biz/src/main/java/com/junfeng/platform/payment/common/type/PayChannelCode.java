/**
 *
 */
package com.junfeng.platform.payment.common.type;

/**
 * 支付通道编码
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月19日 下午3:45:46
 * @version 1.0
 */
public enum PayChannelCode {
    JXBANK("JXBANK", "江西银行"),
    CMB("CMB", "招商银行"),
    CITICBANK("CITICBANK", "中信银行"),
    CCB("CCB", "建设银行"),
    ICBC("ICBC", "工商银行"),
    UNIONPAY("UNIONPAY","银联"),
    POSTBANK("POSTBANK","邮政银行"),
    CHINABANK("CHINABANK","中国银行"),
    ABCBANK("ABCBANK","农业银行");


    private String value;
    private String description;

    private PayChannelCode(String value, String description) {
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
