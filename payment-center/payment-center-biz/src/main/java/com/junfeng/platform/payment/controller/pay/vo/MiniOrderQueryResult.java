package com.junfeng.platform.payment.controller.pay.vo;

import lombok.Data;

/**
 * 小程序支付结果查询
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年10月29日 下午3:43:40
 * @version 1.0
 */
@Data
public class MiniOrderQueryResult {

    private Integer resultState ;// 支付结果编码<br>-1 查询订单不存在 <br> 0 未成功 <br> 1 支付成功<br> 2 已退款 <br> 3 订单关闭
    private String resultMessage ;// 支付结果说明
    private String payOrderNo ;// 支付中心支付单号，resultState=1 时返回
    private String tradeOrderNo ;// 第三方交易单号，resultState=1 时返回

}
