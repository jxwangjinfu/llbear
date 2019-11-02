package com.junfeng.platform.payment.common.lock;
/**
 * 锁类型
 * @projectName:tobacco-cloud-common
 * @author:chenyx
 * @date:2018年6月22日 下午3:31:53
 * @version 1.0
 */
public enum LockType {
    PAY_BARCODE("payBarcode","条码支付"),
	REFUND_ORDER("refundOrder","退款"),
	UNIFIEDORDER("unifiedorder","统一下单")
	;


	private LockType(String value, String description) {
		this.value = value;
		this.description = description;
	}
	private String value;
	private String description;
	public String getValue() {
		return value;
	}
	public String getDescription() {
		return description;
	}

}
