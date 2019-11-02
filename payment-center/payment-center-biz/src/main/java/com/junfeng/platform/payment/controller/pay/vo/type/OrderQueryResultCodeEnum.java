package com.junfeng.platform.payment.controller.pay.vo.type;

/**
 * 查询支付结果 返回状态码枚举
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月20日 下午7:06:07
 * @version 1.0
 */
public enum OrderQueryResultCodeEnum {
	TIMEOUT(-3,"查询超时"),
	ERROR(-2,"查询异常"),
	NONE(-1, "查询订单不存在"),
	UNPAY(0, "未支付成功"),
	SUCCESS(1, "支付成功"),
	REFUND(2, "已退款"),
	CLOSE(3, "订单关闭");

	private Integer value;
	private String description;

	private OrderQueryResultCodeEnum(Integer value, String description) {
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
