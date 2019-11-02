package com.junfeng.platform.tc.api.vo;

import com.junfeng.platform.tc.api.entity.GroupOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-22 16:08
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class GroupOrderVo {


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

	/**
	 * 买家
	 */
	@ApiModelProperty(value = "买家", example = "1234")
	private Long memberId;
	/**
	 * 卖家
	 */
	@ApiModelProperty(value = "卖家", example = "1")
	private Long sellerId;
	/**
	 * 商品列表
	 */
	private List<OrderItemVo> items;
	public GroupOrder convert() {
		return new GroupOrder();
	}
}
