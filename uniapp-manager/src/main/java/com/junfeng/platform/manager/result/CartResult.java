package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 购物车
 *
 * @author daiysh
 * @date 2019-10-16 10:44
 **/
@Data
public class CartResult {
	private Long id;
	private String image;
	private String attr_val;
	private int stock;
	private String title;
	private float price;
	private int number;
}
