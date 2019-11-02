package com.junfeng.platform.manager.result;

import com.junfeng.platform.tc.api.entity.OrderItem;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-11-01 15:19
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class OrderItemResult {

	private Long id;
	private String goodsName;
	private Double goodsPrice;
	private String title;
	private String image;
	private Integer goodsNum;

	private Long spuId;
	private Long skuId;

	public static OrderItemResult create(OrderItem o) {
		return new OrderItemResult()
			.setId((o.getId()))
			.setGoodsName(o.getGoodsName())
			.setGoodsPrice(o.getGoodsPrice())
			//TODO:商品图片需要根据spuId查询 & 销量,skuId; goodsThums
			//讨论：这个是不是应该放在前端异步加载呢
			.setImage("https://img13.360buyimg.com/n8/jfs/t1/30343/20/1029/481370/5c449438Ecb46a15b/2b2adccb6dc742fd.jpg")
			.setGoodsNum(o.getGoodsNum())
			.setSpuId(o.getSpuId())
			.setSkuId(o.getSkuId())
			;

	}
}
