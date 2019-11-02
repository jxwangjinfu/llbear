package com.junfeng.platform.payment.bank.abcbank.model;

import com.junfeng.platform.payment.bank.abcbank.model.base.BaseParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AbcBankBarcodeParam extends BaseParam{

    /**
     * 二维码
     */
    private String authCode;

    /**
     * 支付金额
     */
    private String amount;

}
