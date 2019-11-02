package com.junfeng.platform.payment.controller.pay.vo;

import lombok.Data;

/**
 * 查询退款结果返回值VO
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年9月7日 下午4:57:34
 * @version 1.0
 */
@Data
public class RefundOrderQueryResult {
    private Integer resultState ;// 退款结果编码<br>`-1` 查询订单不存在 <br> `0`订单生成<br> `1`退款中<br> `2`退款成功<br> `3`-退款失败<br> `4`业务处理完成
    private String resultMessage ;// 退款结果说明
    private String refundOrderNo ;// 支付中心退款单号，resultState=1 时返回
    private String tradeOrderNo ;// 第三方交易单号，resultState=1 时返回
}
