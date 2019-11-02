package com.junfeng.platform.payment.bank.jxbank.common;

import lombok.Getter;
/**
 * 江西银行订单支付状态定义
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月22日 下午2:50:39
 * @version 1.0
 */
@Getter
public enum JxBankTradeStateEnum {

    SUCCESS("SUCCESS","支付成功"),
    REFUND("REFUND","已完全退款"),
    REFUNDING("REFUNDING","部分退款"),
    NOTPAY("NOTPAY","未支付"),
    CLOSED("CLOSED","已关闭")


    ;

    private String value;
    private String description;
    private JxBankTradeStateEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
