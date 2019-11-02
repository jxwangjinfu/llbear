package com.junfeng.platform.dc.api.vo;

import lombok.Data;

/**
 * 分页设置
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/31 15:42
 * @projectName pig
 */
@Data
public class PageVo {

	/**
	 * 分页页面设置，设置一页数量
	 */
	private Long pageSize;
	/**
	 * 分页页面设置，设置当前想获取的页序号
	 */
	private Long currentPage;
}
