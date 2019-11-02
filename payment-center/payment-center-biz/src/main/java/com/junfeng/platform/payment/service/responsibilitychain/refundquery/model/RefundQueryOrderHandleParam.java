package com.junfeng.platform.payment.service.responsibilitychain.refundquery.model;

import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.RefundOrderQueryResult;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import lombok.Data;

/**
 * 退款查询责任链参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月7日 下午4:36:21
 * @version 1.0
 */
@Data
public class RefundQueryOrderHandleParam {
    /**
     * 支付中心商户号
     */
    private Long payMchId;
    /**
     * 支付中心订单号
     */
    private String payOrderNo;

    /**
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 支付渠道编码
     */
    private String payChannelCode;

    /**
     * 商户银行账号
     */
    private String payChannelAccount;
    /**
     * 商户加密key
     */
    private String payChannelKey;
    /**
     * 查询支付结果返回值
     */
    private RefundOrderQueryResult refundOrderQueryResult;

    /**
     * 退款表
     */
    private PaymentRefundOrder payRefundOrder;

    /**
     * 是否要通知
     */
    private Boolean notifyFlag = false;

    /**
     * 责任链的状态
     */
    private ResponsibilityChainHandlerStateEnum handlerState;

	private PaymentOrderRequestRecord payOrderRequestRecord;
}
