package com.junfeng.platform.payment.bank.postbank.common;

/**
 * 邮政银行返回结果枚举类
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月5日 下午1:55:00
 * @version 1.0
 */
public enum PostBankCallBankCodeEnum {

    SUCCESS("SUCCESS", "请求成功"), FAIL("FAIL", "请求失败"),

    TRADE_SUCCESS("0000", "交易成功"), TRADE_HANDLING("2000", "交易处理中");

    private String status;
    private String description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private PostBankCallBankCodeEnum(String status, String description)
    {
        this.status = status;
        this.description = description;
    }

}
