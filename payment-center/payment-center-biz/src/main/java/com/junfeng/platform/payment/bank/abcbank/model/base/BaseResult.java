package com.junfeng.platform.payment.bank.abcbank.model.base;

import lombok.Data;

@Data
public class BaseResult {

    /**
     * 交易返回码
     */
    private String returnCode;

    /**
     * 交易返回信息
     */
    private String returnMsg;

    /**
     * 商户号
     */
    private String merId;

    /**
     * 终端号
     */
    private String termId;
}
