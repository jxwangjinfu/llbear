package com.junfeng.platform.payment.controller.pay.vo.type;

/**
 * 退款结果 返回状态码枚举
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月20日 下午7:06:07
 * @version 1.0
 *
 */
public enum RefundResultCodeEnum {
	CREATE(0, "订单生成"),
	PROCESSING(1, "退款中"),
	SUCCESS(2, "退款成功"),
	FAIL(3, "退款失败");

	private Integer value;
	private String description;

	private RefundResultCodeEnum(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
