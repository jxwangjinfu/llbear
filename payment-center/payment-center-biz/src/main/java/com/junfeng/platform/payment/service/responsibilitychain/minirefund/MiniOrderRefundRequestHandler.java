package com.junfeng.platform.payment.service.responsibilitychain.minirefund;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultCodeEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultStatusEnum;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniRefundParam;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniRefundResult;
import com.junfeng.platform.payment.bank.citicbankmini.request.CiticBankMiniRefundRequest;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniStateEnum;
import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniRefundResult;
import com.junfeng.platform.payment.bank.unionpaymini.request.UnionpayMiniRefundRequest;
import com.junfeng.platform.payment.bank.unionpaymini.utils.UnionpayMiniSignUtils;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayRefundState;
import com.junfeng.platform.payment.common.type.OrderRefundStateEnum;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.type.RefundResultCodeEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.model.MiniOrderRefundHandleParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Component
public class MiniOrderRefundRequestHandler extends AbstractHandler<MiniOrderRefundHandleParam> {

    @Autowired
    private UnionpayMiniRefundRequest unionpayMiniRefundRequest;

    @Override
    public MiniOrderRefundHandleParam handleRequest(MiniOrderRefundHandleParam requestParam) throws Exception {
        ResponsibilityChainHandlerStateEnum handleState = requestParam.getHandlerState();
        if (ResponsibilityChainHandlerStateEnum.EXCEPTION.equals(handleState)) {
            return requestParam;
        }

        if (ResponsibilityChainHandlerStateEnum.SUCCESS.equals(handleState)) {

            String payChannelCode = requestParam.getPayChannelCode();
            PaymentRefundOrder payRefundOrder = requestParam.getPayRefundOrder();
            PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
            // 银联
            if (PayChannelCode.UNIONPAY.getValue().equals(payChannelCode)) {

                // 设置请求参数

                JSONObject json = new JSONObject();

                json.put("msgSrc", UnionpayMiniConstant.MSGSRC);
                json.put("msgType", "refund");
                json.put("requestTimestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                json.put("merOrderId", payOrderRequestRecord.getPayOrderNo());
                json.put("instMid", UnionpayMiniConstant.MINI_INST_MID);
                json.put("mid", requestParam.getPayChannelAccount());
                json.put("tid", requestParam.getPayChannelKey());
                json.put("refundAmount", String.valueOf(requestParam.getRefundAmount()));

                Map<String, String> paramsMap = UnionpayMiniSignUtils.jsonToMap(json);

                String sign = UnionpayMiniSignUtils.makeSign(UnionpayMiniConstant.MD5KEY, json);
                paramsMap.put("sign", sign);

                String obj = JSON.toJSONString(paramsMap);

                UnionpayMiniRefundResult requestResult = unionpayMiniRefundRequest.getMiniRefundRequest(obj);
                if (requestResult != null) {
                    if (StringUtils.equals(UnionpayMiniStateEnum.SUCCESS.getState(), requestResult.getErrCode())) {
                        // 退款成功
                        requestParam.setRefundSuccess(true);
                        payRefundOrder.setErrCode(requestResult.getErrCode());
                        payRefundOrder.setErrMsg(requestResult.getErrMsg());
                        payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
                        payRefundOrder.setRefundSuccessTime(
                        	LocalDateTime.parse(requestResult.getResponseTimestamp(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                        payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

                        requestParam.getMiniOrderRefundResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
                        requestParam.getMiniOrderRefundResult()
                                .setResultMessage(RefundResultCodeEnum.SUCCESS.getDescription());
                    } else {
                        // 退款失败
                        requestParam.setRefundSuccess(false);
                        requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                        payRefundOrder.setErrCode(requestResult.getErrCode());
                        payRefundOrder.setErrMsg(requestResult.getErrMsg());
                        payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
                        requestParam.getMiniOrderRefundResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
                        requestParam.getMiniOrderRefundResult()
                                .setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
                    }
                }
                // 中信银行
            } else if (StringUtils.equals(PayChannelCode.CITICBANK.getValue(), payChannelCode)) {

                CiticBankMiniRefundParam param = new CiticBankMiniRefundParam();
                param.setMchId(requestParam.getPayChannelAccount());
                param.setKey(requestParam.getPayChannelKey());
                param.setRefundFee(requestParam.getRefundAmount());
                param.setTotalFee(requestParam.getRefundAmount());
                param.setOutTradeNo(payOrderRequestRecord.getPayOrderNo());
                param.setOutRefundNo(payRefundOrder.getRefundOrderNo());

                CiticBankMiniRefundRequest rq = new CiticBankMiniRefundRequest(param);
                CiticBankMiniRefundResult result = rq.doRequestForObject();

                if (StringUtils.equals(result.getStatus(), CiticBankResultStatusEnum.SUCCESS.getValue())) {
                    if (StringUtils.equals(result.getResultCode(), CiticBankResultCodeEnum.SUCCESS.getValue())) {
                        // 退款成功
                        requestParam.setRefundSuccess(true);
                        payRefundOrder.setErrCode(result.getResultCode());
                        payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
                        payRefundOrder.setRefundSuccessTime(LocalDateTime.now());

                        payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

                        requestParam.getMiniOrderRefundResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
                        requestParam.getMiniOrderRefundResult()
                                .setResultMessage(RefundResultCodeEnum.SUCCESS.getDescription());
                    } else {
                        // 退款失败
                        requestParam.setRefundSuccess(false);
                        requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                        payRefundOrder.setErrCode(result.getResultCode());
                        payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
                        requestParam.getMiniOrderRefundResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
                        requestParam.getMiniOrderRefundResult()
                                .setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
                    }
                } else {
                    // 退款失败
                    requestParam.setRefundSuccess(false);
                    requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                    payRefundOrder.setErrCode(result.getStatus());
                    payRefundOrder.setErrMsg(result.getMessage());
                    payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
                    requestParam.getMiniOrderRefundResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
                    requestParam.getMiniOrderRefundResult()
                            .setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
                }

            }

        }

        return requestParam;
    }

}
