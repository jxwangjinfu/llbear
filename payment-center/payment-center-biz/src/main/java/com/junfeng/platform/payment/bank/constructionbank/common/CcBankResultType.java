package com.junfeng.platform.payment.bank.constructionbank.common;

/**
 * 建行返回结果枚举
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 下午3:04:34
 * @version 1.0
 */
public enum CcBankResultType {

    SUCCESS("Y", "成功"), FAIL("N", "失败"), UNCERTAIN("U", "不确定"), QUERY("Q", "待查询"),REFUND_SUCCESS("000000","退款成功");

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
    private CcBankResultType(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
