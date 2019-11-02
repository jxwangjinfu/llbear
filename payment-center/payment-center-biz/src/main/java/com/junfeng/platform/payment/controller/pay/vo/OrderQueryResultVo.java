package com.junfeng.platform.payment.controller.pay.vo;

import javax.validation.constraints.NotNull;

import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.common.validated.UpdateVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单查询请求参数
 *
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月22日 下午2:20:01
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderQueryResultVo extends BaseParams {

    @NotNull(message = "业务系统订单号不能为空", groups = {AddVo.class, UpdateVo.class})
    private String mchOrderNo;
}
