package com.junfeng.platform.payment.controller.pay.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * 查询支付结果返回值VO
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月16日 下午4:58:58
 * @version 1.0
 */
@Data
public class OrderQueryResult {
    private Integer resultState ;// 支付结果编码<br>-1 查询订单不存在 <br> 0 未成功 <br> 1 支付成功<br> 2 已退款 <br> 3 订单关闭
    private String resultMessage ;// 支付结果说明
    private String payOrderNo ;// 支付中心支付单号，resultState=1 时返回
    private String tradeOrderNo ;// 第三方交易单号，resultState=1 时返回
    private String payMode;//支付方式
    private LocalDateTime paySuccessTime;//支付成功时间
}
