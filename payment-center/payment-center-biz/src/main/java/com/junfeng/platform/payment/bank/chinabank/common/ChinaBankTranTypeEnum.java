package com.junfeng.platform.payment.bank.chinabank.common;

import lombok.Getter;

/**
 * 中国银行交易类型
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午4:05:25
 * @version 1.0
 */
@Getter
public enum ChinaBankTranTypeEnum {

    PAY("C", "被扫消费"), QUERY("G", "订单支付结果查询"), REFUND("R", "退货");

    private String value;
    private String description;

    private ChinaBankTranTypeEnum(String value, String description)
    {
        this.value = value;
        this.description = description;
    }



}
