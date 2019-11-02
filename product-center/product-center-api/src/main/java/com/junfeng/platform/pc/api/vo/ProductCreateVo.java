package com.junfeng.platform.pc.api.vo;

import com.junfeng.platform.pc.api.entity.Spu;
import com.junfeng.platform.pc.api.entity.SpuDetail;
import lombok.Data;

import java.util.List;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/15 10:24
 * @projectName pig
 */
@Data
public class ProductCreateVo {
	/**
	 * 产品信息
	 */
	private Spu spu;
	/**
	 * 产品详细信息
	 */
	private SpuDetail spuDetail;
	/**
	 * 库存量单位信息
	 */
	private List<ProductSkuVo> skuList;
}
