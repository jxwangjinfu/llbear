package com.junfeng.platform.tc.api.order.state;

import lombok.Getter;

/**
 * 描述 状态4. 订单完成
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-12 00:10
 * @projectName pig
 */
public class OrderState4Done extends OrderState {
	@Getter
	private String code = OrderState.DONE;
	@Getter
	private String desc = "订单完成";

	@Override
	protected String handle(OrderContext context, OrderEvent orderEvent) {
		return handleError(context, orderEvent);
	}
}
