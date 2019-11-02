package com.junfeng.platform.payment.controller.pay.vo;

import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.common.validated.UpdateVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 退款订单查询请求参数
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年9月7日 下午5:02:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RefundOrderQueryResultVo extends BaseParams {

	@NotNull(message = "支付中心退款单号不能为空", groups = {AddVo.class, UpdateVo.class})
	private String refundOrderNo;
	@NotNull(message = "业务系统支付号 不能为空", groups = {AddVo.class, UpdateVo.class})
	private String mchOrderNo; // 业务系统支付号
}
