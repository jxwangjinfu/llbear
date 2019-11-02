package com.junfeng.platform.pc.api.vo;

import cn.hutool.json.JSONUtil;
import com.junfeng.platform.pc.api.entity.Sku;
import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.entity.SpuDetail;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-29 17:28
 * @projectName pig
 */
@Data
@Accessors(chain = true)
public class ProductDetailVo {
	private Long id;
	private String title;
	private String subTitle;
	private double discountPrice;
	private Long price;
	private Long sales;
	private int repertory;
	private int pageView;
	private List<String> imgList;
	private List<SpecialVo> specification;
	private Map<String, SkuVo> skuList;
	private String desc;

	public static ProductDetailVo create(Spu spu, SpuDetail spuDetail, List<Sku> skuList) {

		Optional<Long> min = skuList.stream().map(Sku::getPrice).min(Long::compare);

		int stockSum = skuList.stream().mapToInt(Sku::getStock).sum();
		return new ProductDetailVo().setId(spu.getId()).setTitle(spu.getTitle()).setSubTitle(spu.getSubTitle())
			.setPrice(min.get())
			.setSales(spu.getSaleCount())
			.setRepertory(stockSum)
			.setImgList(Arrays.asList(spu.getImages().split("\\|")))
			.setSpecification(
				JSONUtil.parseArray(spuDetail.getSpecialSpecifications()).toList(SpecialVo.class)
			)
			.setSkuList(
				skuList.stream().map(SkuVo::create).collect(Collectors.toMap(SkuVo::getIndex, Function.identity()))
			)
			.setDesc(spuDetail.getDescription())
			;

	}
}
