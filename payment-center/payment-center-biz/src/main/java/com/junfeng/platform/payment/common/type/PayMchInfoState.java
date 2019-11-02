package com.junfeng.platform.payment.common.type;

/**
 * 中心商户状态
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月17日 下午4:34:47
 * @version 1.0
 */
public enum PayMchInfoState {

    DISABLE(0, "停止使用"), ENABLE(1, "使用中"), AUDITED(2, "待审核");

	private Integer value;
	private String description;

	private PayMchInfoState(Integer value, String description) {
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
