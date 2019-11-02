package com.junfeng.platform.csc.controller;

import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/28 15:07
 * @projectName pig
 */
@Api(tags = {"订单中心"})
@RestController
@AllArgsConstructor
@RequestMapping("/tradeservice")
public class TradeServiceController {

	private final RemoteTradeOrderService remoteTradeOrderService;

	@ApiOperation(value = "根据单号获取订单", notes = "参数： 订单编号")
	@GetMapping("/getorderbyid")
	@PreAuthorize("@cscpms.hasPermission('Order')")
	public R<Order> getOrderById(@RequestParam("orderNo") String orderNo) {
		return remoteTradeOrderService.remoteGetOrderInfo(orderNo, SecurityConstants.FROM_IN);
	}

	@ApiOperation(value = "订单取消",notes = "参数:memberId 会员userId orderId 订单编号")
	@PutMapping("/cancel")
	@PreAuthorize("@cscpms.hasPermission('Order')")
	public R<Order> cancelOrder(@RequestParam("memberId") String memberId, @RequestParam("orderId")String orderId){
		return remoteTradeOrderService.remoteOrderCancel(memberId,orderId,SecurityConstants.FROM_IN);
	}

}
