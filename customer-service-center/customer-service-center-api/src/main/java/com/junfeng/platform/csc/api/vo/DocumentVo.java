package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 知识引导接口请求参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 14:19
 * @projectName pig
 */
@Data
public class DocumentVo {

	/**
	 * 用户输入知识列表选择
	 */
	@NotNull(message = "title不能够为空")
	private String title;
}
