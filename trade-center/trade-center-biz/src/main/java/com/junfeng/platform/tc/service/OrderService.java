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
package com.junfeng.platform.tc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.vo.OrderRequest;

/**
 * 订单
 *
 * @author fuh
 * @date 2019-09-17 14:14:29
 */
public interface OrderService extends IService<Order> {

	public Order createOrder(OrderRequest request);

	public Order doCreateOrder(Order order);

	Order checkOrderState(String tradeOrderNo);

	boolean updateOrderState(Order order);

	Order orderPayNotify(Order order, String payOrderNo);
}
