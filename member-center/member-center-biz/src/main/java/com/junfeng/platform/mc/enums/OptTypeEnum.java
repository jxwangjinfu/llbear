package com.junfeng.platform.mc.enums;

/**
 * 操作类型枚举
 *
 * @author daiysh
 * @date 2019-10-14 11:30
 **/
public enum OptTypeEnum {

	ADD("1", "获取"), SUB("0", "消耗");

	private String value;
	private String description;

	private OptTypeEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
