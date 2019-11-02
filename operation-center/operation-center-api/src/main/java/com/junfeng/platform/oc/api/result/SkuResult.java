package com.junfeng.platform.oc.api.result;

import lombok.Data;

/**
 * SKU结果
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/16 17:28
 * @projectName pig
 */
@Data
public class SkuResult {
	//skuId
	private Long id;
	private Long spuCode;
	//单价
	private Long price;
	private String skuCode;
	//数量
	private Integer amount;
	//总价
	private Long totalPrice;
}
