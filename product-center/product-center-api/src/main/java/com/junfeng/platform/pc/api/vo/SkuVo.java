package com.junfeng.platform.pc.api.vo;
import com.junfeng.platform.pc.api.entity.Sku;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class SkuVo {
	private Long skuId;
	private String index;
	private Long price;
	private Integer stock;

	public static SkuVo create(Sku sku) {
		return new SkuVo().setSkuId(sku.getId())
			.setStock(sku.getStock())
			.setIndex(sku.getSpecialIndexs()).setPrice(sku.getPrice());
	}
}
