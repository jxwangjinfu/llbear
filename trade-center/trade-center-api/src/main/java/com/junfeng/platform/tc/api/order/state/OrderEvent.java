package com.junfeng.platform.tc.api.order.state;

import lombok.Getter;

/**
 * 描述
 *
 * * (事件1：支付） * (事件2：商家发货) * (事件3：用户确认收货) * (事件4 用户退款) * (事件5：商户确认退款) * (事件6：取消订单)
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-10 16:25
 * @projectName pig
 */
public enum OrderEvent {

    ConsumerPay("1", "用户支付"),
    BusinessSend("2", "商家发货"),
    ConsumerConfirmGoods("3", "用户确认收货"),
	ConsumerRefund("4", "用户申请退款"),
	BusinessConfirmRefund("5", "商家确认退款"),
	Cancel("6", "取消订单");

    @Getter
    private String key;

    @Getter
    private String desc;

	private OrderEvent(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
