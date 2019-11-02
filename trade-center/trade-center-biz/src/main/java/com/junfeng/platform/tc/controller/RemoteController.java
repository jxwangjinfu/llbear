package com.junfeng.platform.tc.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.feign.RemotePaymentService;
import com.junfeng.platform.payment.api.vo.PaymentPayVo;
import com.junfeng.platform.tc.api.ApiConstant;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.entity.OrderItem;
import com.junfeng.platform.tc.api.order.state.OrderEvent;
import com.junfeng.platform.tc.api.order.state.OrderState;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.junfeng.platform.tc.api.vo.PaymentNotify;
import com.junfeng.platform.tc.service.OrderItemService;
import com.junfeng.platform.tc.service.OrderService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-30 14:08
 * @projectName pig
 */
@RestController
public class RemoteController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private RemotePaymentService remotePaymentService;

    /**
     * @return
     * @Author wangk
     * @Description //内部调用 获取订单信息用。如支付中心创建支付单时需要获取
     * @Date 17:30 2019-10-11
     * @Param
     **/
    @Inner
    @GetMapping(ApiConstant.API_REMOTE_ORDER_INFO)
    public R<Order> getByOrderInfo(@RequestParam String orderNo) {
        return R.ok(orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, orderNo)));
    }

    /**
     * @return
     * @Author wangk
     * @Description (事件1：支付） //支付成功后，支付中心发来的通知
     * @Date 17:30 2019-10-11
     * @Param
     **/
    @Inner
    @PostMapping(ApiConstant.API_REMOTE_NOTIFY_PAY_SUCCESS)
    public R<Order> notify(@RequestBody PaymentNotify paymentNotify) {
        Order order = orderService
                .getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, paymentNotify.getMchOrderNo()));

        Assert.notNull(order, "该订单不存在！");

        Assert.isTrue(order.getState().equals(OrderState.WAIT_FOR_PAY), "订单不是未支付状态！");

        // TODO 需要放入通知中心的队列来处理
        orderService.orderPayNotify(order, paymentNotify.getPayOrderNo());

        return R.ok(order);
    }

    /**
     * @Author wangk
     * @Description 支付中心 退款成功通知 交易修改状态。
     * @Date 17:07 2019-10-16
     **/
    @Inner
    @PostMapping(ApiConstant.API_REMOTE_NOTIFY_PAY_REFUND)
    public R<Order> remoteNotfiyPayRefund(@RequestBody PaymentNotify paymentNotify) {
        Order order = orderService
                .getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, paymentNotify.getMchOrderNo()));

        order.trigger(OrderEvent.BusinessConfirmRefund);

        orderService.updateOrderState(order);

        return R.ok(order);
    }

    /**
     * @Author wangk
     * @Description 创建订单
     * @Date 14:38 2019-10-28
     **/
    @Inner
    @PostMapping(ApiConstant.API_REMOTE_MEMBER_ORDER_CREATE)
    R<Order> remoteMemberOrderCreate(@RequestBody OrderRequest request,
            @RequestHeader(SecurityConstants.FROM) String from) {

        Order order = orderService.createOrder(request);

        return R.ok(order);
    }

    /**
    * @Author wangk
    * @Description 创建订单 支付单
    * @Date 11:21 2019-11-01
    **/
	@GetMapping(ApiConstant.API_REMOTE_MEMBER_ORDER_PREPAY)
	R<String> remoteMemberOrderPrepay(@RequestParam("orderNo") String orderNo) {
		Order order = orderService.getOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderNo, orderNo));

		Assert.notNull(order);

		R<PaymentOrderRequestRecord> r = remotePaymentService.remotePaymentCreate(order.getOrderNo(), SecurityConstants.FROM_IN);
		Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), r.getMsg());

		return R.ok(r.getData().getPayOrderNo());
	}


	/**
     * @Author wangk
     * @Description 会员订单列表
     * @Date 14:38 2019-10-28
     **/
    @Inner
    @GetMapping(ApiConstant.API_REMOTE_MEMBER_ORDER_LIST)
    R<List<Order>> remoteMemberOrderList(@RequestParam String memberId, @RequestParam String state) {
        List<Order> list = orderService.list(Wrappers.<Order> lambdaQuery()
                .eq(Order::getUserId, Long.parseLong(memberId)).eq(
                	StrUtil.isNotBlank(state) && !"0".equals(state), Order::getState, state)
			.orderByDesc(Order::getCreateTime)
		);
        list.forEach(o -> o.setOrderItems(
                orderItemService.list(Wrappers.<OrderItem> lambdaQuery().eq(OrderItem::getOrderNo, o.getOrderNo()))));
        return R.ok(list);
    }

    /**
     * @Author wangk
     * @Description 客服中心 取消订单
     * @Date 14:39 2019-10-28
     **/
    @Inner
    @PutMapping(ApiConstant.API_REMOTE_CSC_ORDER_CLOSED_REFUND_ORDER + "/{userId}/{orderId}")
    R<Order> remoteOrderCancel(@PathVariable("userId") String userId, @PathVariable("orderId") String orderId) {
        Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getUserId, Long.parseLong(userId))
                .eq(Order::getOrderNo, orderId));

        Assert.notNull(order, "订单不存在！");

        Assert.isTrue(OrderState.REFUNDING.equals(order.getState()), "订单必须是退款中！");

        PaymentPayVo paymentPayVo = new PaymentPayVo().setPayOrderNo(order.getPayOrderNo());

        remotePaymentService.remotePaymentRefund(paymentPayVo, SecurityConstants.FROM_IN);

        return R.ok(order);
    }
}
