package com.junfeng.platform.payment.common.type;

/**
 * 兼容旧接口返回类型
 *
 * @author chenyx
 *
 */
public enum BooleanToIntEnum {
	TRUE(1, true,"是"), FALSE(0,false, "否");

	private Integer value;
	private Boolean booleanValue;
	private String description;

	private BooleanToIntEnum(Integer value, Boolean booleanValue, String description) {
		this.value = value;
		this.booleanValue = booleanValue;
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

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

}
