package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 导入问答知识接口请求参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 10:50
 * @projectName pig
 */
@Data
public class QuestionAnswerVo {

	/**
	 * 问题
	 */
	@NotNull(message = "question不能够为空")
	private String question;

	/**
	 * 答案
	 */
	@NotNull(message = "answer不能够为空")
	private String answer;

}
