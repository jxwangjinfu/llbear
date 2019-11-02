package com.junfeng.platform.payment.service.responsibilitychain.unifiedorder;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model.UnifiedOrderHandleParams;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniPaymentParam;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniPaymentResult;
import com.junfeng.platform.payment.bank.citicbankmini.request.CiticBankMiniPaymentRequest;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniStateEnum;
import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniPayResult;
import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniUnifiedorderParam;
import com.junfeng.platform.payment.bank.unionpaymini.request.UnionpayMiniUnifiedorderRequest;
import com.junfeng.platform.payment.common.type.PaymentModeCode;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model.MiniPayRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 银联云闪付统一下单责任链
 *
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年10月24日 下午4:57:27
 * @version 1.0
 */
@Component
public class UnifiedOrderUnionPayHandler extends AbstractHandler<UnifiedOrderHandleParams> {

    @Autowired
    private PaymentOrderRequestRecordService payOrderRequestRecordService;

    @Override
    public UnifiedOrderHandleParams handleRequest(UnifiedOrderHandleParams requestParam) throws Exception {

        ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
        if (handlerState == null || !ResponsibilityChainHandlerStateEnum.SUCCESS.equals(handlerState)) {
            return requestParam;
        }

        PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
        // 银联调用统一下单接口
        if (StringUtils.equals(PayChannelCode.UNIONPAY.getValue(), requestParam.getPayChannelCode())) {
            // 小程序支付
            if (StringUtils.equals(PaymentModeCode.WX_MINI.getValue(), requestParam.getPaymentModeCode())) {
                // 银联小程序下单请求
                UnionpayMiniUnifiedorderParam param = new UnionpayMiniUnifiedorderParam();

                // 消息来源
                param.setMsgSrc(UnionpayMiniConstant.MSGSRC);
                // 消息类型
                param.setMsgType("wx.unifiedOrder");
                // 报文请求时间
                param.setRequestTimestamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                // 商户订单号
                param.setMerOrderId(payOrderRequestRecord.getPayOrderNo());
                // 总金额
                param.setTotalAmount(String.valueOf(requestParam.getAmount()));
                // 终端号
                param.setTid(requestParam.getPayChannelKey());
                // 机构商户号
                param.setInstMid(UnionpayMiniConstant.MINI_INST_MID);
                // 交易类型
                param.setTradeType("MINI");
                // 银行商户账号
                param.setMid(requestParam.getPayChannelAccount());
                // 用户标识
                param.setSubOpenId(requestParam.getOpenId());

                UnionpayMiniUnifiedorderRequest rq = new UnionpayMiniUnifiedorderRequest(param);
                UnionpayMiniPayResult requestResult = rq.doRequestForObject();

                if (requestResult != null) {
                    // 下单成功
                    if (UnionpayMiniStateEnum.SUCCESS.getState().equals(requestResult.getErrCode())) {
                        MiniPayRequest result = requestResult.getMiniPayRequest();
                        result.setOutTradeNo(payOrderRequestRecord.getMchOrderNo());
                        requestParam.setMiniPayRequest(requestResult.getMiniPayRequest());

                        // payOrderRequestRecord.setState(BooleanToIntEnum.TRUE.getValue());
                        payOrderRequestRecord.setPaySuccessTime(LocalDateTime.now());
                        payOrderRequestRecordService.updateById(payOrderRequestRecord);
                    }
                }

            }
            // 中信银行
        } else if (StringUtils.equals(PayChannelCode.CITICBANK.getValue(), requestParam.getPayChannelCode())) {
            // 小程序支付
            if (StringUtils.equals(PaymentModeCode.WX_MINI.getValue(), requestParam.getPaymentModeCode())) {
                CiticBankMiniPaymentParam param = new CiticBankMiniPaymentParam();
                param.setMchId(requestParam.getPayChannelAccount());
                param.setKey(requestParam.getPayChannelKey());
                param.setBody(requestParam.getBody());
                param.setMchCreateIp(requestParam.getSpbillCreateIp());
                param.setNotifyUrl(requestParam.getNotifyUrl());
                param.setOutTradeNo(payOrderRequestRecord.getPayOrderNo());
                param.setSubAppId(requestParam.getAppId());
                param.setTotalFee(requestParam.getAmount().intValue());
                param.setSubOpenId(requestParam.getOpenId());

                CiticBankMiniPaymentRequest request = new CiticBankMiniPaymentRequest(param);
                CiticBankMiniPaymentResult requestResult = request.doPostForObject();

                if (requestResult != null) {
                    // 下单成功
                    if (StringUtils.equals(UnionpayMiniStateEnum.CITICMINISUCCESS.getState(), requestResult.getStatus())
                            && StringUtils.equals(UnionpayMiniStateEnum.CITICMINISUCCESS.getState(),
                                    requestResult.getResultCode())) {
                        String payInfo = requestResult.getPayInfo();
                        MiniPayRequest result = JSONObject.parseObject(payInfo,MiniPayRequest.class);
                        result.setOutTradeNo(payOrderRequestRecord.getMchOrderNo());
                        result.setAppid(requestParam.getAppId());
                        requestParam.setMiniPayRequest(result);

                        payOrderRequestRecord.setPaySuccessTime(LocalDateTime.now());
                        payOrderRequestRecordService.updateById(payOrderRequestRecord);
                    }
                }

            }
        }

        return requestParam;
    }

}
