package com.junfeng.platform.payment.service.responsibilitychain.unifiedorder;

import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.bank.unionpaymini.utils.UnionpayMiniSignUtils;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayState;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model.UnifiedOrderHandleParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 支付订单请求记录表插入记录
 * 生成中心支付订单
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年10月24日 下午4:09:42
 * @version 1.0
 */
@Component
public class UnifiedOrderAddRecordHandler extends AbstractHandler<UnifiedOrderHandleParams> {

    @Autowired
    private PaymentOrderRequestRecordService payOrderRequestRecordService;

    @Override
    public UnifiedOrderHandleParams handleRequest(UnifiedOrderHandleParams requestParam) throws Exception {
        ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
        if (handlerState == null || handlerState.equals(ResponsibilityChainHandlerStateEnum.EXCEPTION)) {
            return requestParam;
        }
        if (requestParam.getHandlerState().equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {

            PaymentOrderRequestRecord obj = new PaymentOrderRequestRecord();
            // 生成24位中心支付订单号
            obj.setPayOrderNo(UnionpayMiniSignUtils.genMerOrderId(UnionpayMiniConstant.MSGSRCID));
            obj.setPayMchId(requestParam.getPayMchId());
            obj.setAppShopId(requestParam.getAppShopId());
            obj.setMchOrderNo(requestParam.getMchOrderNo());
            obj.setPaymentModeCode(requestParam.getPaymentModeCode());
			obj.setPayChannelCode(requestParam.getPayChannelCode());
            obj.setAmount(requestParam.getAmount());
            // 设置订单状态为创建
            obj.setState(OrderPayState.CREATE.getValue());
            obj.setClientIp(requestParam.getSpbillCreateIp());
            obj.setBody(requestParam.getBody());
            obj.setSubject(requestParam.getBody());
            obj.setNotifyUrl(requestParam.getNotifyUrl());
            //创建支付订单
            payOrderRequestRecordService.save(obj);

            //获得支付中心订单号
            requestParam.setPayOrderNo(obj.getPayOrderNo());
            requestParam.setPayOrderRequestRecord(obj);
        }

        return requestParam;
    }

}
