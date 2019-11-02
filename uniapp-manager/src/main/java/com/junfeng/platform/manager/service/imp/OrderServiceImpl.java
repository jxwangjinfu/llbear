package com.junfeng.platform.manager.service.imp;

import cn.hutool.core.lang.Assert;
import com.junfeng.platform.manager.result.OrderResult;
import com.junfeng.platform.manager.service.OrderService;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单业务处理实现类
 *
 * @author daiysh
 * @date 2019-1 17:12
 **/
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final RemoteTradeOrderService remoteTradeOrderService;

	/**
	 * 订单列表
	 *
	 * @param userId
	 * @param state
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Object>
	 * @author: daiysh
	 * @createTime: 2019-10-26  11:57
	 **/
	@Override
	public R<List<OrderResult>> getOrderList(int userId, String state) {
		String memberId = String.valueOf(userId);
		R<List<Order>> r = remoteTradeOrderService.remoteMemberOrderList(memberId, state, SecurityConstants.FROM_IN);
		Assert.isTrue(CommonConstants.SUCCESS.equals(r.getCode()), "获取订单列表失败");
		List<Order> orderList = r.getData();

		return R.ok(orderList.stream().map(OrderResult::transfer).collect(Collectors.toList()));
	}

	/**
	 * 创建订单
	 *
	 * @param request
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Object>
	 * @author: daiysh
	 * @createTime: 2019-10-26  11:57
	 **/
	@Override
	public R<Order> createOrder(OrderRequest request) {
		R<Order> orderR = remoteTradeOrderService.remoteMemberOrderCreate(request, SecurityConstants.FROM_IN);
		return orderR;
	}

	@Override
	public R<String> orderPrepay(String orderNo) {
		return remoteTradeOrderService.remoteMemberOrderPrepay(orderNo, SecurityConstants.FROM_IN);
	}


}
