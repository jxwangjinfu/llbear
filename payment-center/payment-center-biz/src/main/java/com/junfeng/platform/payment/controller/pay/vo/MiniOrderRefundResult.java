package com.junfeng.platform.payment.controller.pay.vo;

import lombok.Data;

@Data
public class MiniOrderRefundResult {

    private Integer resultState ;// 退款结果编码<br>`1`退款中<br> `2`退款成功<br> `3`-退款失败<br> `4`业务处理完成
    private String resultMessage ;// 退款结果说明
    private String refundOrderNo ;// 支付中心退款单号

}
