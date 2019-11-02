package com.junfeng.platform.payment.uitls.notify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付中心通知业务系统支付成功消息定义
 * @projectName:tobacco-cloud-common
 * @author:chenyx
 * @date:2018年8月16日 下午2:39:31
 * @version 1.0
 */
@Data
@Accessors(chain = true)
//@AllArgsConstructor
public class PaymentCenterNotifyPaySuccess {

	public PaymentCenterNotifyPaySuccess(Long timestamp, String nonceString, String payMchId, String mchOrderNo, String tradeOrderNo, String payOrderNo, Long paySuccessTimestamp, String sign, String paymentModeCode) {
		this.timestamp = timestamp;
		this.nonceString = nonceString;
		this.payMchId = payMchId;
		this.mchOrderNo = mchOrderNo;
		this.tradeOrderNo = tradeOrderNo;
		this.payOrderNo = payOrderNo;
		this.paySuccessTimestamp = paySuccessTimestamp;
		this.sign = sign;
		this.paymentModeCode = paymentModeCode;
	}

	/**
	 * 消息当前时间戳,单位：毫秒
	 */
	private Long timestamp;
	/**
	 * 随机字符串，不长于32位
	 */
	private String nonceString;
	/**
	 * 支付中心商户号
	 */
	private String payMchId;
	/**
	 * 业务系统支付号
	 */
	private String mchOrderNo;
	/**
	 * 第三方交易单号
	 */
	private String tradeOrderNo;
	/**
	 * 支付中心订单号
	 */
	private String payOrderNo;
	/**
	 * 支付成功时间戳，单位：毫秒
	 */
	private Long paySuccessTimestamp;
	/**
	 * 签名
	 */
	private String sign;

	private String paymentModeCode;

}
