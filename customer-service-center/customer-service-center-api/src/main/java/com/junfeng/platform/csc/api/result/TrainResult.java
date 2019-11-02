package com.junfeng.platform.csc.api.result;

import lombok.Data;


/**
 * 获取训练结果接口返回值
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/18 13:50
 * @projectName pig
 */
@Data
public class TrainResult {

	/**
	 * 训练前的原始问题
	 */
	private String sourceask;

	/**
	 * 训练后的问题
	 */
	private String ask;

	/**
	 * 答案
	 */
	private String answer;
}
