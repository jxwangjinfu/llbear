package com.junfeng.platform.payment.controller.pay.vo.mch;

import lombok.Data;

/**
 * 商户支付类型
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月1日 下午5:04:25
 * @version 1.0
 */
@Data
public class MchPayType {

    private String type;
    private MchPayAccount data;

}
