package com.junfeng.platform.payment.bank.citicbank.common;

public enum CiticBankTradeStateEnum {

    SUCCESS("SUCCESS", "支付成功"), REFUND("REFUND", "转入退款"), NOTPAY("NOTPAY", "未支付"), CLOSED("CLOSED",
            "已关闭"), USERPAYING("USERPAYING", "用户支付中"), PAYERROR("PAYERROR", "支付失败");

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

    private CiticBankTradeStateEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
