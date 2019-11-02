package com.junfeng.platform.payment.controller.pay.vo;

import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.common.validated.UpdateVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 订单退款参数
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月16日 下午4:58:39
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class OrderRefundVo extends BaseParams {

    @NotNull(message = "业务系统支付号不能为空", groups = {AddVo.class, UpdateVo.class})
    private String mchOrderNo;//业务系统支付号

    @NotNull(message = "客户端IP不能为空", groups = {AddVo.class, UpdateVo.class})
    private String clientIp ; // 客户端IP

}
