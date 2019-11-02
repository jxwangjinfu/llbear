package com.junfeng.platform.payment.service.responsibilitychain.refundorder.model;

import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.RefundOrderResult;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import lombok.Data;

/**
 * 退款责任链所需参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月6日 下午4:49:01
 * @version 1.0
 */
@Data
public class RefundOrderHandleParam {

    /**
     * 支付中心商户号
     */
    private Long payMchId;

    /**
     * 业务订单号
     */
    private String mchOrderNo;
    /**
     * 支付中心订单号
     */
    private String payOrderNo;
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
     * 支付通道为建设银行，这个字段存的是柜台Id:posId;支付通道为邮政银行，这个字段存的是银行分配的appid
     */
    private String payChannelExtendJson;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 支付金额
     */
    private Long payAmount;

    /**
     * 退款金额
     */
    private Long refundAmount;

    /**
     * 业务系统商户ID
     */
    private String appShopId;

    /**
     * 支付编码
     */
    private String paymentModeCode;

    /**
     * 备注
     */
    private String body;
    /**
     * 回调URL
     */
    private String notifyUrl;

    /**
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 退款是否成功
     */
    private Boolean refundSuccess;
    /**
     * 支付中心支付请求记录
     */
    private PaymentOrderRequestRecord payOrderRequestRecord;
    /**
     * 退款订单记录
     */
    private PaymentRefundOrder payRefundOrder;
    /**
     * 退款结果返回值
     */
    private RefundOrderResult refundOrderResult;
    /**
     * 责任链的状态
     */
    private ResponsibilityChainHandlerStateEnum handlerState;

    /**
     * 农业银行用户私钥
     */
    private String mchPrivateKey;

}
