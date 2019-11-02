package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 获取请求接口访问参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 11:36
 * @projectName pig
 */
@Data
public class TrainResultVo {

	/**
	 * 用于过滤查询
	 */
	private String title;

	/**
	 * 查询数据的页码
	 */
	@NotNull(message = "pagenum不能够为空")
	private Integer pagenum;
}
