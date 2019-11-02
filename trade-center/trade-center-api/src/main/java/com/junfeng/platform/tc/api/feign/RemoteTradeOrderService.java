package com.junfeng.platform.tc.api.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.junfeng.platform.tc.api.ApiConstant;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.factory.RemoteTradeServiceFallbackFactory;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.junfeng.platform.tc.api.vo.PaymentNotify;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-09 17:08
 * @projectName pig
 */
@FeignClient(contextId = "remoteTradeOrderService", value = ServiceNameConstants.TRADE_CENTER_SERVICE, fallbackFactory = RemoteTradeServiceFallbackFactory.class)
public interface RemoteTradeOrderService {

	/**
	* @Author wangk
	* @Description 订单信息
	* @Date 11:12 2019-10-22
	**/
	@GetMapping(ApiConstant.API_REMOTE_ORDER_INFO)
	R<Order> remoteGetOrderInfo(@RequestParam("orderNo") String orderNo, @RequestHeader(SecurityConstants.FROM) String from);

	@PostMapping(ApiConstant.API_REMOTE_NOTIFY_PAY_SUCCESS)
	R<Order> remoteNotifyPaySuccess(@RequestBody PaymentNotify paymentNotify, @RequestHeader(SecurityConstants.FROM) String from);

	@PostMapping(ApiConstant.API_REMOTE_NOTIFY_PAY_REFUND)
	R<Order> remoteNotifyPayRefund(@RequestBody PaymentNotify paymentNotify, @RequestHeader(SecurityConstants.FROM) String from);


	/**
	 * @Author wangk
	 * @Description 订单创建
	 * @Date 10:47 2019-10-28
	 **/
	@PostMapping(ApiConstant.API_REMOTE_MEMBER_ORDER_CREATE)
	R<Order> remoteMemberOrderCreate(@RequestBody OrderRequest request, @RequestHeader(SecurityConstants.FROM) String from);


	/**
	* @Author wangk
	* @Description 创建支付单
	* @Date 11:20 2019-11-01
	**/
	@GetMapping(ApiConstant.API_REMOTE_MEMBER_ORDER_PREPAY)
	R<String> remoteMemberOrderPrepay(@RequestParam("orderNo") String orderNo, @RequestHeader(SecurityConstants.FROM) String from);


	/**
	* @Author wangk
	* @Description 会员订单列表
	* @Date 11:03 2019-10-22
	**/
	@GetMapping(ApiConstant.API_REMOTE_MEMBER_ORDER_LIST)
	R<List<Order>> remoteMemberOrderList(@RequestParam("memberId") String memberId, @RequestParam("state") String state, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	* @Author wangk
	* @Description 订单取消接口
	* @Date 11:03 2019-10-22
	**/
	@PutMapping(ApiConstant.API_REMOTE_CSC_ORDER_CLOSED_REFUND_ORDER + "/{userId}/{orderId}")
	R<Order> remoteOrderCancel(@PathVariable("userId") String userId, @PathVariable("orderId")String orderId, @RequestHeader(SecurityConstants.FROM) String from);



}
