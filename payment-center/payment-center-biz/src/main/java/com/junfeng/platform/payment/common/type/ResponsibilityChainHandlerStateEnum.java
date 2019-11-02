package com.junfeng.platform.payment.common.type;

/**
 *
 * 责任链模式业务处理状态定义
 * @author hujie
 * @date 2018-01-16
 */
public enum ResponsibilityChainHandlerStateEnum {

	/**
	 * 异常
	 */
	EXCEPTION("exception"),
	/**
	 * 完成业务处理
	 */
	COMPLETE("complete"),
	/**
	 * 成功
	 */
	SUCCESS("success");
	private String code;
	private ResponsibilityChainHandlerStateEnum(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
