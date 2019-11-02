package com.junfeng.platform.tc.api.vo;

import com.junfeng.platform.tc.api.entity.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单明细
 *
 * @projectName:trade-center-api
 * @author:fuh
 * @date:2019年9月17日 下午3:19:37
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "购物车物品")
public class OrderItemVo {

    /**
     * 商品
     */
	@ApiModelProperty(value = "商品", example="1")
    private Long skuId;

    /**
     * 数量
     */
	@ApiModelProperty(value = "数量", example="10")
    private Integer goodsNum;


	public OrderItem convert() {
		return new OrderItem().setSkuId(this.skuId).setGoodsNum(this.goodsNum);
	}
}
