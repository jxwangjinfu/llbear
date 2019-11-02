package com.junfeng.platform.payment.controller.pay.vo;

import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.common.validated.UpdateVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class MiniOrderQueryResultVo extends BaseParams {

    @NotNull(message = "业务系统订单号不能为空", groups = {AddVo.class, UpdateVo.class})
    private String mchOrderNo;

}
