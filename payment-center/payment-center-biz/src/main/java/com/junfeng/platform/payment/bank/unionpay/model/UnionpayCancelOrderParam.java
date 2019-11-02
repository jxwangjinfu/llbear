package com.junfeng.platform.payment.bank.unionpay.model;

import lombok.Data;

/**
 * 银联撤销接口传递参数
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月6日 上午9:18:36
 * @version 1.0
 */
@Data
public class UnionpayCancelOrderParam {

    /**
     * 商户号
     */
    private String merchantCode;
    /**
     * 终端号
     */
    private String terminalCode;

    /**
     * 银商订单号
     */
    private String originalOrderId;

}
