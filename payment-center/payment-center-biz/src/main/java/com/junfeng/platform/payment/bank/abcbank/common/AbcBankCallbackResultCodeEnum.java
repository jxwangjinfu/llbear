package com.junfeng.platform.payment.bank.abcbank.common;

/**
 * 农业银行返回结果码
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 下午2:49:45
 * @version 1.0
 */
public enum AbcBankCallbackResultCodeEnum {

    SUCCESS("00", "成功"), UNPACK_FAIL("96", "解包失败"), TENANT_ABNORMAL("02", "商户异常"), OTHER_ABNORMAL("03",
            "其他异常"), PAYING("01", "支付中"), FAIL("10", "失败");

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

    private AbcBankCallbackResultCodeEnum(String value, String description)
    {
        this.value = value;
        this.description = description;
    }

}
