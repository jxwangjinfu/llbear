package com.junfeng.platform.payment.controller.pay.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=true)
public class MiniOrderRefundResultVo extends BaseParams{

	@NotNull(message = "支付中心商户号不能为空")
	private Long payMchId ; // 支付中心商户号

    @NotNull(message = "业务系统支付号不能为空")
    private String mchOrderNo;//业务系统支付号

    @NotNull(message = "客户IP不能为空")
    private String clientIp;

}
