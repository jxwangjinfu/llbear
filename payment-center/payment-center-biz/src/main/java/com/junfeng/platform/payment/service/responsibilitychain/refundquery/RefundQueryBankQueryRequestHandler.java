package com.junfeng.platform.payment.service.responsibilitychain.refundquery;

import com.junfeng.platform.payment.bank.unionpay.common.UnionpayStateEnum;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundQueryParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundQueryResult;
import com.junfeng.platform.payment.bank.unionpay.request.UnionpayRefundQueryRequest;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayRefundState;
import com.junfeng.platform.payment.common.type.OrderRefundStateEnum;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.RefundOrderQueryResult;
import com.junfeng.platform.payment.controller.pay.vo.type.OrderQueryResultCodeEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.bank.BankTradeService;
import com.junfeng.platform.payment.service.responsibilitychain.refundquery.model.RefundQueryOrderHandleParam;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退款订单查询请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月7日 下午4:50:32
 * @version 1.0
 */
@Service
public class RefundQueryBankQueryRequestHandler extends AbstractHandler<RefundQueryOrderHandleParam> {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private BankTradeService bankTradeService;

    @Override
    public RefundQueryOrderHandleParam handleRequest(RefundQueryOrderHandleParam requestParam) throws Exception {

        ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
        if (handlerState == null || ResponsibilityChainHandlerStateEnum.EXCEPTION.equals(handlerState)) {
            return requestParam;
        }

        RefundOrderQueryResult queryResult = requestParam.getRefundOrderQueryResult();
        if (ResponsibilityChainHandlerStateEnum.SUCCESS.equals(handlerState)) {
            String payChannelCode = requestParam.getPayChannelCode();
            // 银联
            if (StringUtils.equals(payChannelCode, PayChannelCode.UNIONPAY.getValue())) {
                UnionpayRefundQueryParam param = new UnionpayRefundQueryParam();
                param.setMerchantCode(requestParam.getPayChannelAccount());
                param.setTerminalCode(requestParam.getPayChannelKey());
                param.setMerchantOrderId(requestParam.getPayOrderNo());

                UnionpayRefundQueryRequest rq = new UnionpayRefundQueryRequest(param);
                UnionpayRefundQueryResult requestResult = rq.doRequestForObject();

                PaymentRefundOrder payRefundOrder = requestParam.getPayRefundOrder();
                PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
                // errCode等于00表示查询成功
                if (StringUtils.equals(UnionpayStateEnum.SUCCESS.getValue(), requestResult.getErrCode())) {
                    /*
                     * queryResCode 0：成功 1：超时 2：已撤销 3：已退货 4：已冲正 5：失败（失败情况，后面追加失败描述 FF：交易状态未知
                     */
                    if (StringUtils.equals(UnionpayStateEnum.RETURNED_GOODS.getValue(), requestResult.getQueryResCode())) {
                        //已退货
                        payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
                        payRefundOrder.setErrCode(requestResult.getErrCode());
                        payRefundOrder.setErrMsg(requestResult.getErrInfo());
                        payRefundOrder.setTradeOrderNo(requestResult.getOrderId());

                        payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());
                        bankTradeService.refundOrderSuccessResult(payRefundOrder, payOrderRequestRecord);

                        queryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                        queryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                        queryResult.setRefundOrderNo(payRefundOrder.getRefundOrderNo());
                        queryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());

                        requestParam.setNotifyFlag(true);
                    }else if (StringUtils.equals(UnionpayStateEnum.QUERYTIMEOUT.getValue(), requestResult.getQueryResCode())) {
                        // 查询超时
                        queryResult.setResultState(Integer.valueOf(UnionpayStateEnum.QUERYTIMEOUT.getValue()));
                        queryResult.setResultMessage(requestResult.getErrInfo());
                        requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }else if(StringUtils.equals(UnionpayStateEnum.QUERYFAIL.getValue(), requestResult.getQueryResCode())) {
                        //退款失败
                        queryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        queryResult.setResultMessage(requestResult.getErrInfo());
                        requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }
                } else {
                    // 查询失败
                    queryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    queryResult.setResultMessage(requestResult.getErrInfo());
                    requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }
            }
        }
        return requestParam;
    }

}
