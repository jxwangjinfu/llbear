package com.junfeng.platform.manager.result;

import lombok.Data;

import java.util.List;

/**
 * 商品详情
 *
 * @author daiysh
 * @date 2019-10-28 16:13
 **/
@Data
public class GoodsDetailResult {
	private Long id;
	private String title;
	private double discountPrice;
	private double price;
	private int sales;
	private int repertory;
	private int pageView;
	private List<String> imgList;
//	private List<SkuModel> specList;
//	private List<SkuModel> specChildList;
	private Object specificatin;
	private List<Object> skuList;

	private String desc;
}

