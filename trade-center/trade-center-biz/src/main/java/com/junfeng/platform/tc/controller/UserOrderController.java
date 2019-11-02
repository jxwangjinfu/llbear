package com.junfeng.platform.tc.controller;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.feign.RemotePaymentService;
import com.junfeng.platform.payment.api.vo.PaymentPayVo;
import com.junfeng.platform.tc.api.ApiConstant;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.order.state.OrderEvent;
import com.junfeng.platform.tc.api.order.state.OrderState;
import com.junfeng.platform.tc.api.vo.OrderBaseVo;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.junfeng.platform.tc.service.GroupOrderService;
import com.junfeng.platform.tc.service.OrderItemService;
import com.junfeng.platform.tc.service.OrderService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;

import cn.hutool.core.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 订单
 *
 * @author fuh
 * @date 2019-09-17 14:14:29
 */
@Api(tags = {"用户订单"})
@RestController
@AllArgsConstructor
public class UserOrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final RemotePaymentService remotePaymentService;

    private final GroupOrderService groupOrderService;


    @ApiOperation(value = "订单创建", notes = "创建订单。（isGroupOrder表示团购单）")
    @PostMapping(ApiConstant.API_ORDER_CONSUMER_CREATE)
    public R<Order> create(@RequestBody OrderRequest request) {

		Order order = orderService.createOrder(request);

        return R.ok(order);
}

	@ApiOperation(value = "获取支付参数，发起订单支付", notes = "")
	@GetMapping(ApiConstant.API_ORDER_CONSUMER_PAY + "/{orderNo}")
	public R<PaymentOrderRequestRecord> consumerPay(@PathVariable  String orderNo) {

		Order order = orderService.getOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderNo, orderNo));

		checkOrder(order);

		return remotePaymentService.remotePaymentCreate(order.getOrderNo(), SecurityConstants.FROM_IN);

	}



	/**
	 * @return
	 * @Author wangk
	 * @Description //商户后台发货  (事件2：商家发货)
	 * @Date 17:26 2019-10-15
	 * @Param
	 **/
	@PutMapping(ApiConstant.API_ORDER_SEND_GOODS)
	public R sendGoods(@RequestBody OrderBaseVo request) {
		Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, request.getOrderNo()));

		checkOrder(order);

		order.trigger(OrderEvent.BusinessSend);

		orderService.update(
			Wrappers.<Order> lambdaUpdate().set(Order::getState, order.getState()).eq(Order::getId, order.getId()));

		return R.ok();
	}

	/**
	 * @Author wangk
	 * @Description  用户检查订单
	 * @Date 22:50 2019-10-15
	 * @Param
	 * @return
	 **/
	private void checkOrder(Order order) {
//		Assert.isFalse(OrderState.CLOSED.equals(order.getState()), "订单已经关闭！");
		Assert.notNull(order, "订单不存在！");
	}

	/**
	 * @Author wangk
	 * @Description 订单确认 (事件3：用户确认收货)
	 * @Date 22:34 2019-10-15
	 * @Param
	 * @return
	 **/
	@ApiOperation(value = "订单确认收货", notes = "")
	@PutMapping(ApiConstant.API_ORDER_CONFIRM_GOODS)
	public R confirmGoods(@RequestBody OrderBaseVo request) {

		Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, request.getOrderNo()));

		checkOrder(order);

		// 触发确认操作
		order.trigger(OrderEvent.ConsumerConfirmGoods);

		orderService.updateOrderState(order);

		return R.ok("订单确认收货！");
	}

	// (事件4 用户退款)
	@ApiOperation(value = "用户退款", notes = "")
	@PutMapping(ApiConstant.API_CONSUMER_REFUND)
	public R consumerRefund(@RequestBody OrderBaseVo request) {
		Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, request.getOrderNo()));

		checkOrder(order);

		order.trigger(OrderEvent.ConsumerRefund);

		orderService.updateOrderState(order);
		return R.ok("用户申请退款成功");
	}

	// (事件5：商户确认退款)
    @ApiOperation(value = "商户确认退款", notes = "")
	@PutMapping(ApiConstant.API_BUSINESS_CONFIRM_REFUND)
	public R businessConfirmRefund(@RequestBody OrderBaseVo request) {

		Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, request.getOrderNo()));

		checkOrder(order);

		Assert.isTrue(OrderState.REFUNDING.equals(order.getState()), "订单必须是退款中！");

		PaymentPayVo paymentPayVo = new PaymentPayVo().setPayOrderNo(order.getPayOrderNo());

		return remotePaymentService.remotePaymentRefund(paymentPayVo, SecurityConstants.FROM_IN);
	}



	// (事件6：未付款状态下，取消订单)
	@ApiOperation(value = "未付款状态下，取消订单", notes = "")
	@PutMapping(ApiConstant.API_ORDER_USER_CANCEL)
	public R orderCancel(@RequestBody OrderBaseVo request) {

		Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, request.getOrderNo()));

		checkOrder(order);

		order.trigger(OrderEvent.Cancel);

		orderService.updateOrderState(order);

		return R.ok("取消订单成功！");
	}



}
