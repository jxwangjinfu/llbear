package com.junfeng.platform.payment.service.responsibilitychain.queryorder.model;

import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.OrderQueryResult;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;

import lombok.Data;

/**
 * 查询支付订单责任链参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月21日 上午9:38:54
 * @version 1.0
 */
@Data
public class QueryOrderHandleParam {
    /**
     * 支付中心商户号
     */
    private Long payMchId;
    /**
     * 支付中心订单号
     */
    private String payOrderNo;

    // 支付类型
    private String paymentModeCode;

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
     * 支付通道为建设银行，这个字段存的是柜台Id:posId
     */
    private String payChannelExtendJson;
    /**
     * 查询支付结果返回值
     */
    private OrderQueryResult orderQueryResult;

    /**
     * 是否要通知
     */
    private Boolean notifyFlag = false;

    /**
     * 责任链的状态
     */
    private ResponsibilityChainHandlerStateEnum handlerState;

    private PaymentOrderRequestRecord payOrderRequestRecord;

    /**
     * 农业银行用户私钥
     */
    private String mchPrivateKey;

}
