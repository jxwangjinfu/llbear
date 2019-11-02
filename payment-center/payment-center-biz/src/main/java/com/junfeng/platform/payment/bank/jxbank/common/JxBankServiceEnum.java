package com.junfeng.platform.payment.bank.jxbank.common;

/**
 * 银行服务接口
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月19日 下午5:24:27
 * @version 1.0
 */
public enum JxBankServiceEnum {

    JX_BANK_MIRCOPAY("micropay","刷卡支付"),

    JX_BANK_ORDERQUERY("orderquery","订单查询"),

    JX_BANK_REFUNDORDER("refundorder","申请退款");

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

    private JxBankServiceEnum(String value,String description) {
        this.value = value;
        this.description = description;
    }

}
