package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 订单商品模型
 *
 * @author daiysh
 * @date 2019-10-23 10:45
 **/
@Data
public class OrderGoodsModel extends GoodsResult {
	private String attr;
	private String number;
}
