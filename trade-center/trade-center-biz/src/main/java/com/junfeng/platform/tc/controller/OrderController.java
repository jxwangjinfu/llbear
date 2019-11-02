package com.junfeng.platform.tc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.service.OrderService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 订单
 *
 * @author fuh
 * @date 2019-09-17 14:14:29
 */
@Api(tags = {"订单管理"})
@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;





	/**
	 * 简单分页查询
	 *
	 * @param page  分页对象
	 * @param order 订单
	 * @return
	 */
	@GetMapping("/page")
	@ApiOperation("分页查询")
	public R<IPage<Order>> getOrderPage(Page<Order> page, Order order) {
		IPage<Order> orderPage = orderService.page(page,
			Wrappers.<Order>lambdaQuery()
			.eq(StrUtil.isNotBlank(order.getState()), Order::getState, order.getState())
			.orderByDesc(Order::getCreateTime)
		);
		orderPage.getRecords().stream()
//			.filter(o -> StrUtil.isNotBlank(o.getState()))
			.forEach(o -> o.setState(o.getStateDesc()));
		return R.ok(orderPage);
	}

	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R<Order> getById(@PathVariable("id") Long id) {
		return R.ok(orderService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param order
	 * @return R
	 */
	@SysLog("新增订单")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('tc_order_add')")
	public R save(@RequestBody Order order) {
		return R.ok(orderService.save(order));
	}

	/**
	 * 修改记录
	 *
	 * @param order
	 * @return R
	 */
	@SysLog("修改订单")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('tc_order_edit')")
	public R update(@RequestBody Order order) {
		return R.ok(orderService.updateById(order));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@SysLog("删除订单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('tc_order_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(orderService.removeById(id));
	}

}
