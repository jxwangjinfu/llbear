package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 导入文档知识接口访问参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 10:55
 * @projectName pig
 */
@Data
public class DocumentKnowledgeVo {

	/**
	 * 上级目录标题
	 */
	@NotNull(message = "上级目录标题不能够为空")
	private String parentTitle;

	/**
	 * 标题
	 */
	@NotNull(message = "标题不能够为空")
	private String title;

	/**
	 * 知识文档
	 */
	@NotNull(message = "知识文档不能够为空")
	private String document;


}
