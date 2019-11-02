package com.junfeng.platform.tc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.tc.api.entity.GroupOrder;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.vo.GroupOrderRequest;

/**
 * 团购订单
 *
 * @author wangk
 * @date 2019-10-22 16:17:48
 */
public interface GroupOrderService extends IService<GroupOrder> {

	Order leaderCreateGroupOrder(GroupOrderRequest request);

	Order memberJoinGroupOrder(GroupOrderRequest request);

	void checkLeaderGroupOrderTimeout(String groupOrderNo);

	void checkGroupOrderNotPay(String tradeOrderNo);

	void orderPayNotify(String groupOrderNo, String orderNo);
}
