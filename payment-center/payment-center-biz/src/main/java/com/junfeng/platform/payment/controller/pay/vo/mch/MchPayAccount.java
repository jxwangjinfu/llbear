package com.junfeng.platform.payment.controller.pay.vo.mch;

import lombok.Data;

/**
 * 商户的账号信息
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月1日 下午5:06:07
 * @version 1.0
 */
@Data
public class MchPayAccount {

    /**
     * 商户账号
     */
    private String account;

    /**
     * 商户终端号
     */
    private String payKey;


}
