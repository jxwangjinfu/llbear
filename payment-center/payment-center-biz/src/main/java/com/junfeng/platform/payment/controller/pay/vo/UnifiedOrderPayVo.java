package com.junfeng.platform.payment.controller.pay.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 2fx0one
 * 2019-09-25 17:40
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UnifiedOrderPayVo  extends BaseParams {
	@NotNull(message = "业务系统支付号 不能为空")
	private String mchOrderNo; // 业务系统支付号

	@NotNull(message = "支付方式编码不能为空")
	private String paymentModeCode; // 支付方式编码<br>微信条码 `WX_BARCODE`<br>支付宝条码 `ALIPAY_BARCODE`

	@NotNull(message = "支付金额不能为空")
	private Long amount; // 支付金额, 单位:分

	@NotNull(message = "客户端IP不能为空")
	private String clientIp; // 客户端IP

	@NotNull(message = "商品描述不能为空")
	private String body; // 商品描述信息

	@NotNull(message = "通知URL不能为空")
	private String notifyUrl; // 支付成功后的通知URL

	@NotNull(message = "openId不能为空")
	private String openId;// 用户标识

	@NotNull(message = "appId不能为空")
	private String appId;//小程序appId

	private String remark;
}
