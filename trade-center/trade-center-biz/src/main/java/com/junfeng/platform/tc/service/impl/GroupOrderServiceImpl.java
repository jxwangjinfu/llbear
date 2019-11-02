package com.junfeng.platform.tc.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.oc.api.feign.OcRemoteService;
import com.junfeng.platform.payment.api.feign.RemotePaymentService;
import com.junfeng.platform.payment.api.vo.PaymentPayVo;
import com.junfeng.platform.tc.api.entity.GroupOrder;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.entity.OrderItem;
import com.junfeng.platform.tc.api.message.GroupOrderMessage;
import com.junfeng.platform.tc.api.order.state.GroupOrderConstant;
import com.junfeng.platform.tc.api.order.state.OrderEvent;
import com.junfeng.platform.tc.api.order.state.OrderState;
import com.junfeng.platform.tc.api.vo.GroupOrderRequest;
import com.junfeng.platform.tc.config.rabbit.ExpireMessagePostProcessor;
import com.junfeng.platform.tc.config.rabbit.GroupOrderDelayProcess;
import com.junfeng.platform.tc.mapper.GroupOrderMapper;
import com.junfeng.platform.tc.service.GroupOrderService;
import com.junfeng.platform.tc.service.OrderService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.SnowFlake;

import cn.hutool.core.lang.Assert;
import lombok.AllArgsConstructor;

/**
 * 团购订单
 *
 * @author wangk
 * @date 2019-10-22 16:17:48
 */
@Service("groupOrderService")
public class GroupOrderServiceImpl extends ServiceImpl<GroupOrderMapper, GroupOrder> implements GroupOrderService {
    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private OcRemoteService ocRemoteService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RemotePaymentService remotePaymentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order leaderCreateGroupOrder(GroupOrderRequest request) {
        // 团购单
        String groupOrderNo = String.valueOf(snowFlake.nextId());
        // R<GroupBuyResult> r = ocRemoteService.groupbuySkuId(request.getSkuId(), SecurityConstants.FROM_IN);
        //
        // Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), "无团购活动");

        // GroupBuyResult data = r.getData();
        // Assert.isTrue("1".equals(data.getState()), "团购未开启！");

        GroupOrder groupOrder = new GroupOrder().setRole(GroupOrderConstant.GROUP_LEADER).setUserId(request.getUserId())
                .setGroupOrderNo(groupOrderNo).setJoinLimitNum(2).setJoinedNum(1).setExpireHour(24).setSellerId(1L)
                .setSpuId(request.getSpuId()).setSkuId(request.getSkuId()).setGoodsNum(request.getGoodsNum());

        Order order = insertGroupOrder(groupOrder);

        // 发起团购 一天后结束 由团长开团时间决定
        if (GroupOrderConstant.GROUP_LEADER.equals(groupOrder.getRole())) {
            rabbitTemplate.convertAndSend(GroupOrderDelayProcess.GROUP_ORDER_DELAY_QUEUE_PRE_MESSAGE,
                    new GroupOrderMessage().setGroupOrderNo(groupOrder.getGroupOrderNo()),
                    new ExpireMessagePostProcessor(groupOrder.getExpireHour() * 60 * 1000L));
        }

        return order;

    }

    /**
     * @Author wangk
     * @Description 加入团购 成为团员
     * @Date 16:54 2019-10-28
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order memberJoinGroupOrder(GroupOrderRequest request) {
        String groupOrderNo = request.getGroupOrderNo();
        Long userId = request.getUserId();

        GroupOrder leaderGroupOrder = this
                .getOne(Wrappers.<GroupOrder> lambdaQuery().eq(GroupOrder::getGroupOrderNo, groupOrderNo)
                        .eq(GroupOrder::getRole, GroupOrderConstant.GROUP_LEADER));

        Assert.notNull(leaderGroupOrder, "团单不存在，无法加入");

        Assert.isTrue(GroupOrderConstant.GROUP_STATE_PROCESSING.equals(leaderGroupOrder.getState()), "不是进行中的团，团单无法加入！");

        // TODO 放入消息队列处理 现在乐观锁处理即可
        int joinLimitNum = leaderGroupOrder.getJoinLimitNum();
        int joinedNum = leaderGroupOrder.getJoinedNum();

        Assert.isTrue(joinedNum < joinLimitNum, "该团已满，无法加入！");

        // 更新团长订单数量
        this.update(Wrappers.<GroupOrder> lambdaUpdate().eq(GroupOrder::getId, leaderGroupOrder.getId())
                .eq(GroupOrder::getJoinedNum, joinedNum).set(GroupOrder::getJoinedNum, joinedNum + 1));

        GroupOrder groupOrder = new GroupOrder().setRole(GroupOrderConstant.GROUP_MEMBER).setUserId(request.getUserId())
                .setSellerId(request.getSellerId()).setGroupOrderNo(groupOrderNo).setSpuId(request.getSpuId())
                .setSkuId(request.getSkuId()).setGoodsNum(request.getGoodsNum());

        Order order = insertGroupOrder(groupOrder);

        return order;
    }

    /**
     * @Author wangk
     * @Description 插入订单和团单，并建立关联
     * @Date 15:49 2019-10-29
     **/
    @Transactional(rollbackFor = Exception.class)
    private Order insertGroupOrder(GroupOrder groupOrder) {
        String orderNo = String.valueOf(snowFlake.nextId());

        // 保存
        groupOrder.setTradeOrderNo(orderNo).setState(GroupOrderConstant.GROUP_STATE_WAIT_FOR_PAY).insert();

        Long totalMoney = 9400L;
        Long deliverMoney = 500L;

        Order order = new Order().setUserId(groupOrder.getUserId()).setOrderNo(orderNo)
                .setSellerId(groupOrder.getSellerId()).setGroupOrderNo(groupOrder.getGroupOrderNo())
                .setTotalMoney(totalMoney).setDeliverMoney(deliverMoney).setOrderNo(orderNo)
                .setOrderItems(Arrays.asList(new OrderItem().setOrderNo(orderNo).setSkuId(groupOrder.getSkuId())
                        .setGoodsNum(groupOrder.getGoodsNum())));

        return orderService.doCreateOrder(order);
    }

    /**
     * @Author wangk
     * @Description 创建或加入团单，未支付交易单
     * @Date 11:46 2019-10-29
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkGroupOrderNotPay(String tradeOrderNo) {

        GroupOrder groupOrder = this
                .getOne(Wrappers.<GroupOrder> lambdaQuery().eq(GroupOrder::getTradeOrderNo, tradeOrderNo));
        if (GroupOrderConstant.GROUP_MEMBER.equals(groupOrder.getState())) {
            // 若团员未支付。找到团长单。更新团员数量 -1
            GroupOrder leaderGroup = this.getOne(
                    Wrappers.<GroupOrder> lambdaQuery().eq(GroupOrder::getGroupOrderNo, groupOrder.getGroupOrderNo())
                            .eq(GroupOrder::getRole, GroupOrderConstant.GROUP_LEADER));
            this.update(
                    Wrappers.<GroupOrder> lambdaUpdate().eq(GroupOrder::getGroupOrderNo, groupOrder.getGroupOrderNo())
                            .set(GroupOrder::getJoinedNum, leaderGroup.getJoinedNum() - 1));
        }
        this.update(Wrappers.<GroupOrder> lambdaUpdate().eq(GroupOrder::getTradeOrderNo, tradeOrderNo)
                .set(GroupOrder::getState, GroupOrderConstant.GROUP_STATE_FAIL));

    }

    /**
     * @Author wangk
     * @Description 团长创建团单时间到期。如果团长的团单状态是进行中的。 查询该团的团员订单，所有已经付款的需要做退款处理。并关闭该订单。
     * @Date 14:30 2019-10-28
     **/
    @Override
    public void checkLeaderGroupOrderTimeout(String groupOrderNo) {
        GroupOrder headOrder = this
                .getOne(Wrappers.<GroupOrder> lambdaQuery().eq(GroupOrder::getGroupOrderNo, groupOrderNo)
                        .eq(GroupOrder::getRole, GroupOrderConstant.GROUP_LEADER));
        Assert.notNull(headOrder, "团单不存在，无法处理！");

        if (headOrder != null && headOrder.getState().equals(GroupOrderConstant.GROUP_STATE_PROCESSING)) {
            // 团单时间到期。如果团长的团单状态是进行中的。

            // 所有状态成功(已经付款)的团单 团员的团单 ！注意 未支付的团单。直接等待订单支付超时即可。
            List<GroupOrder> list = this.list(
                    Wrappers.<GroupOrder> lambdaQuery().eq(GroupOrder::getTradeOrderNo, headOrder.getGroupOrderNo())
                            .eq(GroupOrder::getRole, GroupOrderConstant.GROUP_MEMBER)
                            .eq(GroupOrder::getState, GroupOrderConstant.GROUP_STATE_SUCCESS));

            list.forEach(groupOrder -> {
                Order order = orderService
                        .getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, groupOrder.getTradeOrderNo()));
                // 未支付的，需要触发关闭订单。
                // if (OrderState.WAIT_FOR_PAY.equals(order.getState())) {
                // order.trigger(OrderEvent.Cancel);
                // orderService.updateOrderState(order);
                // }
                // 已经支付，需要自动触发退款
                if (OrderState.ALREADY_PAID.equals(order.getState())) {
                    // 自动触发用户退款申请
                    order.trigger(OrderEvent.ConsumerRefund);
                    orderService.updateOrderState(order);

                    // 自动发起退款流程
                    PaymentPayVo paymentPayVo = new PaymentPayVo().setPayOrderNo(order.getPayOrderNo());
                    remotePaymentService.remotePaymentRefund(paymentPayVo, SecurityConstants.FROM_IN);
                }

            });

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderPayNotify(String groupOrderNo, String orderNo) {

        GroupOrder leaderGroupOrder = this
                .getOne(Wrappers.<GroupOrder> lambdaQuery().eq(GroupOrder::getGroupOrderNo, groupOrderNo)
                        .eq(GroupOrder::getRole, GroupOrderConstant.GROUP_LEADER));

        Assert.notNull(leaderGroupOrder, "团不存在！");

        int joinLimitNum = leaderGroupOrder.getJoinLimitNum();
        int joinedNum = leaderGroupOrder.getJoinedNum();

        // 更新该交易单对应的团单状态 改为进行中！
        this.update(Wrappers.<GroupOrder> lambdaUpdate().eq(GroupOrder::getTradeOrderNo, orderNo)
                .set(GroupOrder::getState, GroupOrderConstant.GROUP_STATE_PROCESSING));

        if (joinedNum == joinLimitNum) {
            // 拼团成功 该团的所有团单状态 都改成成功
            this.update(Wrappers.<GroupOrder> lambdaUpdate()
                    .eq(GroupOrder::getGroupOrderNo, leaderGroupOrder.getGroupOrderNo())
                    .set(GroupOrder::getState, GroupOrderConstant.GROUP_STATE_SUCCESS));
        }
    }
}
