package com.junfeng.platform.tc.api.order.state;

import lombok.Getter;

/**
 * 描述 状态5. 退款中
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-12 00:10
 * @projectName pig
 */
public class OrderState5Refunding extends OrderState {
    @Getter
    private String code = REFUNDING;
    @Getter
    private String desc = "退款中";

    @Override
	protected String handle(OrderContext context, OrderEvent orderEvent) {
        switch (orderEvent) {
        case BusinessConfirmRefund:
            return context.changeState(new OrderState6Closed());
        default:
            return handleError(context, orderEvent);
        }
    }
}
