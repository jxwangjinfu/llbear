package com.junfeng.platform.tc.api.order.state;

import lombok.Getter;

/**
 * 描述 状态3. 已发货
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-12 00:07
 * @projectName pig
 */
public class OrderState3AlreadyShipped extends OrderState {

	@Getter
	private String code = ALREADY_SHIPPED;
	@Getter
	private String desc = "已发货";

	@Override
	protected String handle(OrderContext context, OrderEvent orderEvent) {
		switch (orderEvent) {
			case ConsumerConfirmGoods:
				return context.changeState(new OrderState4Done());
			case ConsumerRefund:
				return context.changeState(new OrderState5Refunding());
			default:
				return handleError(context, orderEvent);
		}
	}
}
