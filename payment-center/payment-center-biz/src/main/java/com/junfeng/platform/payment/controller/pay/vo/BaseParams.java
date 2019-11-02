package com.junfeng.platform.payment.controller.pay.vo;

import javax.validation.constraints.NotNull;

import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.common.validated.UpdateVo;

import lombok.Data;

@Data
public class BaseParams {
  //请求公共参数
    @NotNull(message = "时间戳不能为空", groups = {AddVo.class, UpdateVo.class})
    private Long timestamp ; // 时间戳：单位：毫秒

    @NotNull(message = "随机字符串不能为空", groups = {AddVo.class, UpdateVo.class})
    private String nonceString ; // 随机字符串，不长于32位

    @NotNull(message = "支付中心商户号不能为空", groups = {AddVo.class, UpdateVo.class})
    private Long payMchId ; // 支付中心商户号

    @NotNull(message = "签名不能为空", groups = {AddVo.class, UpdateVo.class})
    private String sign ; // 签名
}
