package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 训练问答接口请求参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 11:23
 * @projectName pig
 */
@Data
public class TrainQuestionAnswerVo {

	/**
	 * 需要训练的问题数量
	 */
	@NotNull(message = "num不能够为空")
	private Integer num;

	/**
	 * 需训练的问题
	 */
	private String title;
}
