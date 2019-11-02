package com.junfeng.platform.payment.service.responsibilitychain.miniqueryorder.model;

import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.MiniOrderQueryResult;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import lombok.Data;

/**
 * 小程序支付结果查询责任链参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年10月29日 下午4:08:47
 * @version 1.0
 */
@Data
public class MiniOrderQueryHandleParam {

    /**
     * 支付中心商户号
     */
    private Long payMchId;
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
     * 查询支付结果返回值
     */
    private MiniOrderQueryResult miniOrderQueryResult;

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
