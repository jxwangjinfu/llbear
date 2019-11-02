package com.junfeng.platform.payment.service.responsibilitychain.barcodepay;

import com.junfeng.platform.payment.common.PrintStackTraceUtils;
import com.junfeng.platform.payment.common.RandomUtils;
import com.junfeng.platform.payment.common.httpresp.RequestResult;
import com.junfeng.platform.payment.common.httpresp.RequestResultCode;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayState;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model.BarcodePayHandleParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付订单请求记录表插入记录 生成中心支付订单
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月18日 下午5:31:56
 * @version 1.0
 */
@Component
public class BarcodePayAddRecordHandler extends AbstractHandler<BarcodePayHandleParams> {

    private final static Logger LOGGER = LogManager.getLogger(BarcodePayAddRecordHandler.class);

    @Autowired
    private PaymentOrderRequestRecordService payOrderRequestRecordService;

    @Override
    public BarcodePayHandleParams handleRequest(BarcodePayHandleParams requestParam) throws Exception {
        ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
        if (handlerState == null || handlerState.equals(ResponsibilityChainHandlerStateEnum.EXCEPTION)) {
            return requestParam;
        }
        if (requestParam.getHandlerState().equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {

            PaymentOrderRequestRecord obj = new PaymentOrderRequestRecord();
            // 生成24位中心支付订单号
            obj.setPayOrderNo(RandomUtils.getFixDateTimeAndLengthNumber(10));
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
            // 创建支付订单
            try {
                payOrderRequestRecordService.save(obj);
            } catch (Exception e) {
                LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
                requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                requestParam.setErrorRequestResult(new RequestResult(RequestResultCode.NETWORK_ERROR));
                return requestParam;
            }

            // 获得支付中心订单号
            requestParam.setPayOrderNo(obj.getPayOrderNo());
            requestParam.setPayOrderRequestRecord(obj);
        }

        return requestParam;
    }

}
