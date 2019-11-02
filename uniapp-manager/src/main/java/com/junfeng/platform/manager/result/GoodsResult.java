package com.junfeng.platform.manager.result;

import com.google.common.collect.Lists;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.entity.OrderItem;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 商品结果集
 *
 * @author daiysh
 * @date 2019-10-16 10:43
 **/
@Data
public class GoodsResult {
	private Long id;
	private String image;
	private String title;
	private Double price;
	private Integer sales;

	public GoodsResult() {

	}
	public GoodsResult(long id,String image,String title,double price,int sales) {
		this.id = id;
		this.image = image;
		this.title = title;
		this.price = price;
		this.sales = sales;
	}

	public static GoodsResult transfer(Spu spu) {
		GoodsResult goodsResult = new GoodsResult();
		goodsResult.setImage(spu.getImages());
		goodsResult.setId(spu.getId());
		//TODO:商品价格未找到字段，销量为long类型；
		goodsResult.setPrice(50.0);
		goodsResult.setTitle(spu.getTitle());
		goodsResult.setSales(20);

		return goodsResult;
	}

}
