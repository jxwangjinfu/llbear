package com.junfeng.platform.csc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 删除训练结果接口请求参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 13:59
 * @projectName pig
 */
@Data
public class DeleteTrainResultVo {

	/**
	 * 机器训练的问题
	 */
	@NotNull(message = "question不能够为空")
	private String question;
}
