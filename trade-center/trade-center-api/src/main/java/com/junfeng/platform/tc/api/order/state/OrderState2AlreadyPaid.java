package com.junfeng.platform.tc.api.order.state;

import lombok.Getter;

/**
 * 描述 状态2. 已付款（待发货）
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-11 23:59
 * @projectName pig
 */
public class OrderState2AlreadyPaid extends OrderState {

    @Getter
    private String code = ALREADY_PAID;
    @Getter
    private String desc = "已付款";

    @Override
	protected String handle(OrderContext context, OrderEvent orderEvent) {
        switch (orderEvent) {
        case BusinessSend:
            return context.changeState(new OrderState3AlreadyShipped());
        case ConsumerRefund:
            return context.changeState(new OrderState5Refunding());
        default:
            return handleError(context, orderEvent);
        }
    }
}
