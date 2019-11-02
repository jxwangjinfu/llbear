package com.junfeng.platform.dc.api.result;

import lombok.Data;

import java.util.List;

/**
 * 分页返回结果基类
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/31 9:07
 * @projectName pig
 */
@Data
public class BasePageResult<T> {

	/**
	 * 列表
	 */
	List<T> records;

	/**
	 * 总数
	 */
	private Long total;

	/**
	 * 当前页
	 */
	private Long current;
}
