package com.junfeng.platform.payment.bank.abcbank.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

/**
 * 农业银行返回值
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月7日 下午12:39:13
 * @version 1.0
 */
@Data
@XStreamAlias(impl = AbcBankCallbackResult.class, value = "body")
public class AbcBankCallbackResult {

    @XStreamAlias(value = "respDate")
    private String respDate;

    @XStreamAlias(value = "respTime")
    private String respTime;

    @XStreamAlias(value = "respCode")
    private String respCode;

    @XStreamAlias(value = "respExplain")
    private String respExplain;

    @XStreamAlias(value = "recvPackage")
    private String recvPackage;
}
