package com.junfeng.platform.tc.api.order.state;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-11 21:07
 * @projectName pig
 */
public abstract class OrderState {
	public static String WAIT_FOR_PAY  = "1";
	public static String ALREADY_PAID  = "2";
	public static String ALREADY_SHIPPED  = "3";
	public static String DONE = "4";
	public static String REFUNDING = "5";
	public static String CLOSED = "6";

	private static Map<String, OrderState> stateMap = new HashMap<String, OrderState>() {
		{
			put(WAIT_FOR_PAY, new OrderState1WaitForPay());
			put(ALREADY_PAID, new OrderState2AlreadyPaid());
			put(ALREADY_SHIPPED, new OrderState3AlreadyShipped());
			put(DONE, new OrderState4Done());
			put(REFUNDING, new OrderState5Refunding());
			put(CLOSED, new OrderState6Closed());
		}
	};

	public static OrderState getStateByCode(String code) {
		//拿不到取OrderState1WaitForPay
		return OrderState.stateMap.getOrDefault(code, new OrderState1WaitForPay());
	}


	public abstract String getDesc();

	public abstract String getCode();
	/**
	 * State 包含的处理的事件 返回 state code
	 */
	protected abstract String handle(OrderContext context, OrderEvent orderEvent);

	// 默认的错误处理
	protected String handleError(OrderContext context, OrderEvent orderEvent){
		String s = String.format(" 该订单状态为 state = {%s}, 无法处理该事件 event = {%s}", context.getStateDesc(), orderEvent.getDesc());
		throw new IllegalArgumentException(s);
	}
}
