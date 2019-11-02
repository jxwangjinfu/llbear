package com.junfeng.platform.payment.service.responsibilitychain.refundorder;

import com.junfeng.platform.payment.common.RandomUtils;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderRefundStateEnum;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.type.RefundResultCodeEnum;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.PaymentRefundOrderService;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.model.RefundOrderHandleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 添加退款订单记录责任链
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月6日 下午4:57:26
 * @version 1.0
 */
@Service
public class RefundOrderRefundAddRecordHandler extends AbstractHandler<RefundOrderHandleParam> {

    @Autowired
    private PaymentRefundOrderService payRefundOrderService;

    @Override
    public RefundOrderHandleParam handleRequest(RefundOrderHandleParam requestParam) throws Exception {
        ResponsibilityChainHandlerStateEnum handleState = requestParam.getHandlerState();
        if (handleState.equals(ResponsibilityChainHandlerStateEnum.EXCEPTION)) {
            return requestParam;
        }

        if (handleState.equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {
            // 生成退款订单
            PaymentRefundOrder obj = new PaymentRefundOrder();
            obj.setPayOrderNo(requestParam.getPayOrderNo());
            obj.setAppShopId(requestParam.getAppShopId());
            obj.setMchOrderNo(requestParam.getMchOrderNo());
            obj.setPayAmount(requestParam.getPayAmount());
            obj.setRefundAmount(requestParam.getRefundAmount());
            obj.setPayChannelCode(requestParam.getPayChannelCode());
            obj.setPayMchId(requestParam.getPayMchId());
            obj.setPayChannelCode(requestParam.getPayChannelCode());
            obj.setPaymentModeCode(requestParam.getPaymentModeCode());
            obj.setRefundOrderNo(RandomUtils.getFixDateTimeAndLengthNumber(10));
            obj.setRemark(requestParam.getBody());
            obj.setNotifyUrl(requestParam.getNotifyUrl());
            obj.setState(OrderRefundStateEnum.CREATE.getValue());

            payRefundOrderService.save(obj);

            requestParam.setPayRefundOrder(obj);
            requestParam.setRefundOrderNo(obj.getRefundOrderNo());
            requestParam.getRefundOrderResult().setRefundOrderNo(obj.getRefundOrderNo());
            requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.CREATE.getValue());
            requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.CREATE.getDescription());
        }

        return requestParam;
    }

}
