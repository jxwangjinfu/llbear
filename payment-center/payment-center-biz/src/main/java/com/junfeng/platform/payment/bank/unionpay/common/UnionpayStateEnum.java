package com.junfeng.platform.payment.bank.unionpay.common;

public enum UnionpayStateEnum {

    SUCCESS("00","支付成功"),

    QUERYSUCCESS("0","查询成功"),

    QUERYTIMEOUT("1","查询超时"),

    RETURNED_GOODS("3","已退货"),

    QUERYFAIL("5","查询失败");

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

    private UnionpayStateEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
