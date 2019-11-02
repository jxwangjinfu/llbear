package com.junfeng.platform.manager.result;

import com.junfeng.platform.tc.api.entity.Order;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单结果
 *
 * @author daiysh
 * @date 2019-10-23 09:46
 **/
@Data
@Accessors(chain = true)
public class OrderResult {
	private String title;
    private String orderNo;
    private String time;
    private String state;
    private String stateDesc;
    List<OrderItemResult> goodsModelList;

    public static OrderResult transfer(Order order) {
        return new OrderResult()
			.setTitle(order.getOrderItems().get(0).getGoodsName()) //TODO spu 商品名称
			.setStateDesc(order.getStateDesc())
			.setOrderNo(order.getOrderNo())
			.setTime(order.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
			.setState(order.getState())
			.setGoodsModelList(
				order.getOrderItems().stream().map(OrderItemResult::create).collect(Collectors.toList())
			);
    }
}
