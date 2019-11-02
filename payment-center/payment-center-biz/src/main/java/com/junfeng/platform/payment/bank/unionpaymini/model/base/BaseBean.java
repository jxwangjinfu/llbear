package com.junfeng.platform.payment.bank.unionpaymini.model.base;

import lombok.Data;

@Data
public class BaseBean {

    /**
     * 错误码
     */
    public String errCode;

    /**
     * 错误信息
     */
    public String errMsg;

}
