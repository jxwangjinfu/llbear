/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.junfeng.platform.tc.service.impl;

import com.junfeng.platform.mc.api.feign.RemoteMemberService;
import com.junfeng.platform.tc.api.entity.GroupOrder;
import com.junfeng.platform.tc.api.order.state.GroupOrderConstant;
import com.junfeng.platform.tc.api.order.state.OrderEvent;
import com.junfeng.platform.tc.api.message.OrderMessage;
import com.junfeng.platform.tc.config.rabbit.ExpireMessagePostProcessor;
import com.junfeng.platform.tc.service.GroupOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.feign.OcRemoteService;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.order.state.OrderState;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.junfeng.platform.tc.config.rabbit.OrderPayDelayProcess;
import com.junfeng.platform.tc.mapper.OrderMapper;
import com.junfeng.platform.tc.service.OrderItemService;
import com.junfeng.platform.tc.service.OrderService;
import com.pig4cloud.pig.common.core.util.SnowFlake;

/**
 * 订单
 *
 * @author fuh
 * @date 2019-09-17 14:14:29
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private OcRemoteService ocRemoteService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RemoteMemberService remoteMemberService;

    @Autowired
    private GroupOrderService groupOrderService;

    /**
     * @Author wangk
     * @Description 创建订单
     * @Date 14:04 2019-10-11
     * @Param
     * @return
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderRequest request) {

        // 会员中心拉去用户数据
        // remoteMemberService.findMember(request.getUserId().intValue(), SecurityConstants.FROM_IN);
        // Order order = request.convert();

        String orderNo = String.valueOf(snowFlake.nextId());

        long totalMoney = 9400L;
        long deliverMoney = 500L;
        long totalDiscount = 0L;
        // TODO 需要调用运营中心的数据。确定优惠的金额。
        // OrderVO vo = new OrderVO();
        // vo.setOrderNo(orderNo);
        // R<OrderResult> r = ocRemoteService.orderLock(vo, SecurityConstants.FROM_IN);
        // Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), "优惠价锁定失败！");

        // OrderResult data = r.getData();

        // TODO 需要调用商品中心的接口。用于确定订单中商品数据
        // order.setTotalMoney(data.getTotalPrice());

        return doCreateOrder(request.convert().setOrderNo(orderNo).setTotalMoney(totalMoney)
                .setDeliverMoney(deliverMoney).setTotalDiscount(totalDiscount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order doCreateOrder(Order order) {

        order.setState(OrderState.WAIT_FOR_PAY);
        order.insert();

        order.getOrderItems().forEach(e -> e.setOrderNo(order.getOrderNo()).insert());

        // 30 分钟后检查订单。
        rabbitTemplate.convertAndSend(
                OrderPayDelayProcess.ORDER_DELAY_QUEUE_PRE_QUEUE, new OrderMessage().setTradeOrderNo(order.getOrderNo())
                        .setMemberId(order.getMemberId()).setUserId(order.getUserId()),
                new ExpireMessagePostProcessor(30 * 60 * 60 * 1000L));

        return order;
    }

    /**
     * @Author wangk
     * @Description 更新订单状态
     * @Date 14:03 2019-10-28
     **/
    @Override
    public boolean updateOrderState(Order order) {
        return this.update(
                Wrappers.<Order> lambdaUpdate().set(Order::getState, order.getState()).eq(Order::getId, order.getId()));
    }

    /**
     * @Author wangk
     * @Description 30 分钟订单检查。
     * @Date 11:00 2019-10-28
     **/
    @Override
    public Order checkOrderState(String tradeOrderNo) {
        Order order = this.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, tradeOrderNo));
        if (order != null && order.getState().equals(OrderState.WAIT_FOR_PAY)) {
            order.trigger(OrderEvent.Cancel);
            this.updateOrderState(order);
            return order;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order orderPayNotify(Order order, String payOrderNo) {
        order.trigger(OrderEvent.ConsumerPay);

        order.update(Wrappers.<Order> lambdaUpdate().eq(Order::getId, order.getId())
                .set(Order::getState, order.getState()).set(Order::getPayOrderNo, payOrderNo));

        //存在团单号。更新该团单的状态为 成功！
        String groupOrderNo = order.getGroupOrderNo();
        if (groupOrderNo != null) {
            groupOrderService.orderPayNotify(groupOrderNo, order.getOrderNo());
        }

        return order;
    }
}
