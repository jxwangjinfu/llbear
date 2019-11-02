package com.junfeng.platform.payment.common.type;

/**
 * 商户 支付通道状态
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月17日 下午4:34:47
 * @version 1.0
 */
public enum PayMchChannelRelationState {

    DISABLE(0, "停用"), ENABLE(1, "启用");

	private Integer value;
	private String description;

	private PayMchChannelRelationState(Integer value, String description) {
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
