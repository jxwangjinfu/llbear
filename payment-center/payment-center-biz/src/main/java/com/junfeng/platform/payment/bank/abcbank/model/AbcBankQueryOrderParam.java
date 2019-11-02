package com.junfeng.platform.payment.bank.abcbank.model;

import com.junfeng.platform.payment.bank.abcbank.model.base.BaseParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 农业银行查询请求参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 下午4:20:56
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AbcBankQueryOrderParam extends BaseParam{

    /**
     * 支付金额
     */
    private String amount;

}
