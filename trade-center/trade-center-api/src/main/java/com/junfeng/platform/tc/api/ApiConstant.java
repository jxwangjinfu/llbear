package com.junfeng.platform.tc.api;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-15 17:15
 * @projectName pig
 */
public interface ApiConstant {

	//团购单创建
	String API_ORDER_CONSUMER_GROUP_CREATE = "/order/consumer/group/create";
	//团购单加入
	String API_ORDER_CONSUMER_GROUP_JOIN = "/order/consumer/group/join";
	//订单创建
	String API_ORDER_CONSUMER_CREATE = "/order/consumer/create";

	//订单支付
	String API_ORDER_CONSUMER_PAY = "/order/consumer/pay";


	//商户后台发货
	String API_ORDER_SEND_GOODS = "/order/business/send";

	//订单确认
	String API_ORDER_CONFIRM_GOODS = "/order/consumer/confirm";

	//(事件4 用户退款)
	String API_CONSUMER_REFUND = "order/consumer/refund";

	//(事件5：商户确认退款)
	String API_BUSINESS_CONFIRM_REFUND = "order/business/confirm/refund";

	//(事件6：未付款状态下，取消订单)
	String API_ORDER_USER_CANCEL = "order/user/cancel";

	//内部调用 订单信息
	String API_REMOTE_ORDER_INFO = "/order/remote/info";

	//内部调用 通知订单
	String API_REMOTE_NOTIFY_PAY_SUCCESS = "/order/remote/notify/pay/success";


	//内部调用 支付退款成功通知
	String API_REMOTE_NOTIFY_PAY_REFUND = "/order/remote/notify/pay/refund";

	//创建订单
	String API_REMOTE_MEMBER_ORDER_CREATE = "/order/remote/memeber/create";

	String API_REMOTE_MEMBER_ORDER_PREPAY = "/order/remote/memeber/pay";

	//内部调用 会员订单列表
	String API_REMOTE_MEMBER_ORDER_LIST = "/order/remote/memeber/list";

	// 客服中心 关闭退款中的订单
	String API_REMOTE_CSC_ORDER_CLOSED_REFUND_ORDER = "/order/remote/csc/order/cancel";

}
