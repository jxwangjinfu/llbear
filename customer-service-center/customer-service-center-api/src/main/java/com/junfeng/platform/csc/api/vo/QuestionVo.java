package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 解答问题接口请求参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 14:06
 * @projectName pig
 */
@Data
public class QuestionVo {

	/**
	 * 用户输入的问题
	 */
	@NotNull(message = "question不能够为空")
	private String question;
}
