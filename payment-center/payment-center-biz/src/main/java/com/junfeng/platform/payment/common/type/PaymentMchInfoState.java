package com.junfeng.platform.payment.common.type;

/**
 * 2fx0one
 * 2019-09-25 16:42
 **/
public enum PaymentMchInfoState {

	DISABLE(0, "停止使用"), ENABLE(1, "使用中"), AUDITED(2, "待审核");

	private Integer value;
	private String description;

	private PaymentMchInfoState(Integer value, String description) {
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
