package com.junfeng.platform.csc.api.result;

import lombok.Data;


/**
 * 解答问题接口返回值
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 14:09
 * @projectName pig
 */
@Data
public class QuestionResult {

	/**
	 * 用户输入的问题
	 */
	private String answer;
}
