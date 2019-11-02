package com.junfeng.platform.payment.common.type;

/**
 * 订单退款状态
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年9月7日 上午10:23:36
 * @version 1.0
 */
public enum OrderPayRefundState {

    NON_REFUNDS(0, "未退款"), REFUNDED(1, "已退款");

	private Integer value;
	private String description;

	private OrderPayRefundState(Integer value, String description) {
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
