package com.junfeng.platform.tc.api.order.state;

import lombok.Getter;

/**
 * 描述 状态1. 待付款
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-11 23:59
 * @projectName pig
 */
public class OrderState1WaitForPay extends OrderState {

    @Getter
    private String code = WAIT_FOR_PAY;
    @Getter
    private String desc = "待支付";

    @Override
	protected String handle(OrderContext context, OrderEvent orderEvent) {

        switch (orderEvent) {
        case ConsumerPay:
            return context.changeState(new OrderState2AlreadyPaid());
        case Cancel:
            return context.changeState(new OrderState6Closed());
        default:
            return handleError(context, orderEvent);
        }
    }
}
