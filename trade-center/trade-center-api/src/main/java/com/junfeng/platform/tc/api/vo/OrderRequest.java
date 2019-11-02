package com.junfeng.platform.tc.api.vo;

import com.junfeng.platform.tc.api.entity.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 下单对象
 *
 * @version 1.0
 * @projectName:trade-center-api
 * @author:fuh
 * @date:2019年9月17日 下午3:16:52
 */
@Data
@Accessors(chain = true)
@ApiModel("订单模型")
public class OrderRequest {

	/**
	 * 买家
	 */
	@ApiModelProperty(value = "买家", example = "1234")
	private Long userId;
	/**
	 * 卖家
	 */
	@ApiModelProperty(value = "卖家", example = "1")
	private Long sellerId;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "消费者", example = "消费者备注：我要打10个！谢谢！价格总计是99元，其中运费5元。不可以算错哟！~")
	private String remark;

	/**
	 * 商品列表
	 */
	private List<OrderItemVo> items;

	public Order convert() {
		return new Order()
			.setUserId(this.userId)
			.setSellerId(this.sellerId)
			.setRemarks(remark)
			.setOrderItems(
				this.items.stream().map(OrderItemVo::convert).collect(Collectors.toList())
			);
	}
}
