package com.junfeng.platform.payment.bank.postbank.model.base;

import lombok.Data;

@Data
public class BaseBean {

    /**
     * 返回码
     */
    private String retCode;

    /**
     * 返回信息
     */
    private String retMsg;

    /**
     * 商户号
     */
    private String cusId;

    /**
     * 应用id
     */
    private String appId;

}
