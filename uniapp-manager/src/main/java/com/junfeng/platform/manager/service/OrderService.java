package com.junfeng.platform.manager.service;


import com.junfeng.platform.manager.result.OrderResult;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.pig4cloud.pig.common.core.util.R;

import java.util.List;

/**
 * 订单业务处理层
 *
 * @author daiysh
 * @date 2019-10-15 17:00:11
 */
public interface OrderService {
	/**
	 * 获取订单列表
	 *
	 * @param userId
	 * @param orderState
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Object>
	 * @author: daiysh
	 * @createTime: 2019-10-26  11:51
	 **/
	R<List<OrderResult>> getOrderList(int userId, String orderState);

	/**
	 * 创建订单
	 *
	 * @param request
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Object>
	 * @author: daiysh
	 * @createTime: 2019-10-26  11:53
	 **/
	R<Order> createOrder(OrderRequest request);

	R<String> orderPrepay(String request);
}
