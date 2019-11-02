package com.junfeng.platform.payment.bank.jxbank.common;


/**
 * 江西銀行返回错误码枚举
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月21日 下午3:10:10
 * @version 1.0
 */
public enum JxBankResultRetcodeEnum {

	NEED_PASSWORD(-2010, "需要输入密码"), SUCCESS(0, "支付成功");

	private Integer value;
	private String description;

	private JxBankResultRetcodeEnum(Integer value, String description) {
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
