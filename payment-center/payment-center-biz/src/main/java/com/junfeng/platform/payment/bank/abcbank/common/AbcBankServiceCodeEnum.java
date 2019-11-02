package com.junfeng.platform.payment.bank.abcbank.common;

/**
 * 农业银行业务代码
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 下午2:56:42
 * @version 1.0
 */
public enum AbcBankServiceCodeEnum {

    PAY("01", "支付"), QUERY("02", "支付查询"), REFUND("04", "退款");

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

    private AbcBankServiceCodeEnum(String value, String description)
    {
        this.value = value;
        this.description = description;
    }

}
