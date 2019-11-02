package com.junfeng.platform.pc.api.vo;

import lombok.Data;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/15 10:36
 * @projectName pig
 */
@Data
public class ProductSkuVo {
	/**
	 * id 新增时为0
	 */
	private Long id;
	/**
	 * 图片信息
	 */
	private String images;
	/**
	 * 价格
	 */
	private Long price;
	/**
	 * 特有规格赋值
	 */
	private String special_indexs;
	/**
	 * sku编号
	 */
	private String skuCode;
	/**
	 *库存数
	 */
	private Integer stock;
	/**
	 *创建人
	 */
	private String createBy;
	/**
	 *变更人
	 */
	private String updateBy;
}
