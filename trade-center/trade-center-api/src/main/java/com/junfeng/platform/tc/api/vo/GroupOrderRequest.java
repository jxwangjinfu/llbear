package com.junfeng.platform.tc.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-23 10:49
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class GroupOrderRequest {
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

	@ApiModelProperty(value = "spuId", example = "1")
	private Long spuId;
	@ApiModelProperty(value = "skuId", example = "1")
	private Long skuId;
	@ApiModelProperty(value = "数量", example="10")
	private Integer goodsNum;


	@ApiModelProperty(value = "团单ID", example="")
	private String groupOrderNo;
}
