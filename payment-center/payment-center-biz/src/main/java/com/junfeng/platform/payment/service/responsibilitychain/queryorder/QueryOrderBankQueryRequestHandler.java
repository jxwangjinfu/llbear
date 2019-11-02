package com.junfeng.platform.payment.service.responsibilitychain.queryorder;

import com.junfeng.platform.payment.bank.abcbank.common.AbcBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankQueryOrderParam;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankQueryOrderResult;
import com.junfeng.platform.payment.bank.abcbank.request.AbcBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankCallbackResult;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankQueryOrderParam;
import com.junfeng.platform.payment.bank.chinabank.request.ChinaBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultCodeEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultStatusEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankTradeStateEnum;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankQueryOrderParam;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankQueryOrderResult;
import com.junfeng.platform.payment.bank.citicbank.request.CiticBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.cmbank.common.CmBankResultCodeEnum;
import com.junfeng.platform.payment.bank.cmbank.common.CmBankResultStatusEnum;
import com.junfeng.platform.payment.bank.cmbank.common.CmBankTradeStateEnum;
import com.junfeng.platform.payment.bank.cmbank.model.CmBankQueryOrderParam;
import com.junfeng.platform.payment.bank.cmbank.model.CmBankQueryOrderResult;
import com.junfeng.platform.payment.bank.cmbank.request.CmBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.constructionbank.common.CcBankQrCodeType;
import com.junfeng.platform.payment.bank.constructionbank.common.CcBankResultType;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankQueryOrderParam;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankQueryOrderResult;
import com.junfeng.platform.payment.bank.constructionbank.request.ConstructionBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.jxbank.common.JxBankResultRetcodeEnum;
import com.junfeng.platform.payment.bank.jxbank.common.JxBankTradeStateEnum;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankOrderQueryResult;
import com.junfeng.platform.payment.bank.jxbank.request.JxBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.postbank.common.PostBankCallBankCodeEnum;
import com.junfeng.platform.payment.bank.postbank.model.PostBankQueryOrderParam;
import com.junfeng.platform.payment.bank.postbank.model.PostBankQueryOrderResult;
import com.junfeng.platform.payment.bank.postbank.request.PostBankQueryOrderRequest;
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayStateEnum;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayQueryOrderParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayQueryOrderResult;
import com.junfeng.platform.payment.bank.unionpay.request.UnionpayQueryOrderRequest;
import com.junfeng.platform.payment.common.BeanMapper;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayState;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.PaymentModeCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.OrderQueryResult;
import com.junfeng.platform.payment.controller.pay.vo.type.OrderQueryResultCodeEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.service.bank.BankTradeService;
import com.junfeng.platform.payment.service.responsibilitychain.queryorder.model.QueryOrderHandleParam;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 银行查询订单请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月21日 上午9:47:57
 * @version 1.0
 */
@Service
public class QueryOrderBankQueryRequestHandler extends AbstractHandler<QueryOrderHandleParam> {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private BankTradeService bankTradeService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public QueryOrderHandleParam handleRequest(QueryOrderHandleParam requestParams) throws Exception {

        ResponsibilityChainHandlerStateEnum handlerState = requestParams.getHandlerState();
        if (handlerState == null || handlerState.equals(ResponsibilityChainHandlerStateEnum.EXCEPTION)) {
            return requestParams;
        }
        OrderQueryResult orderQueryResult = requestParams.getOrderQueryResult();
        if (handlerState.equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {
            String payChannelCode = requestParams.getPayChannelCode();
            // 支付通道是江西银行，设置江西银行查询订单参数
            if (StringUtils.equals(payChannelCode, PayChannelCode.JXBANK.getValue())) {
                JxBankQueryOrderRequest rq = new JxBankQueryOrderRequest(requestParams.getPayChannelAccount(),
                        requestParams.getPayChannelKey(), requestParams.getPayOrderNo());
                JxBankOrderQueryResult requestResult = rq.doRequestForObject();
                if (requestResult == null) {
                    LOGGER.error("requestResult is null,getPayOrderNo={}", requestParams.getPayOrderNo());
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    return requestParams;
                }

                if (JxBankResultRetcodeEnum.SUCCESS.getValue().equals(requestResult.getRetcode())) {
                    PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                    // 查询成功
                    if (StringUtils.equals(requestResult.getTradeState(), JxBankTradeStateEnum.SUCCESS.getValue())) {
                        // 支付成功

                        payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                        payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
                        payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderNo());

                        payOrderRequestRecord.setErrCode(String.valueOf(requestResult.getRetcode()));
                        payOrderRequestRecord.setErrMsg(requestResult.getRetmsg());

                        PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);

                        payOrderResponseRecord.setBankType(requestResult.getBankType());
                        payOrderResponseRecord.setFeeType(requestResult.getFeeType());

                        payOrderResponseRecord.setPayChannelAccount(requestResult.getMchId());
                        bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                        orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                        orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                        orderQueryResult.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
                        requestParams.setNotifyFlag(true);

                    } else if (StringUtils
                            .equals(requestResult.getTradeState(), JxBankTradeStateEnum.NOTPAY.getValue())) {
                        // 未支付成功
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.UNPAY.getDescription());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                        return requestParams;
                    } else if (StringUtils
                            .equals(requestResult.getTradeState(), JxBankTradeStateEnum.CLOSED.getValue())) {
                        // 已关闭
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.CLOSE.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.CLOSE.getDescription());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);

                    } else if (StringUtils
                            .equals(requestResult.getTradeState(), JxBankTradeStateEnum.REFUND.getValue())) {
                        // 已完全退款，暂不处理
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.REFUND.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.REFUND.getDescription());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }
                    return requestParams;
                }
                // 招商银行查询订单请求
            } else if (StringUtils.equals(payChannelCode, PayChannelCode.CMB.getValue())) {

                CmBankQueryOrderParam param = new CmBankQueryOrderParam();
                param.setKey(requestParams.getPayChannelKey());
                param.setMchId(requestParams.getPayChannelAccount());
                param.setOutTradeNo(requestParams.getPayOrderNo());
                CmBankQueryOrderRequest rq = new CmBankQueryOrderRequest(param);
                CmBankQueryOrderResult requestResult = rq.doPostForObject();

                if (requestResult == null) {
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    return requestParams;
                }

                if (StringUtils.equals(CmBankResultStatusEnum.SUCCESS.getValue(), requestResult.getStatus())) {
                    PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                    // 查询成功
                    if (StringUtils.equals(CmBankResultCodeEnum.SUCCESS.getValue(), requestResult.getResultCode())) {
                        if (StringUtils.equals(CmBankTradeStateEnum.SUCCESS.getValue(), requestResult.getTradeState())) {
                            // 支付成功
                            payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                            payOrderRequestRecord.setPaySuccessTime(
								LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

                            payOrderRequestRecord.setTradeOrderNo(requestResult.getTransactionId());

                            PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
								PaymentOrderResponseRecord.class);
                            payOrderResponseRecord.setBankType(requestResult.getBankType());
                            payOrderResponseRecord.setFeeType(requestResult.getFeeType());

                            bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                            orderQueryResult.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
                            requestParams.setNotifyFlag(true);
                        } else if (StringUtils.equals(CmBankTradeStateEnum.NOTPAY.getValue(),
                                requestResult.getTradeState())) {
                            // 未支付
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.UNPAY.getDescription());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                        } else if (StringUtils.equals(CmBankTradeStateEnum.CLOSED.getValue(),
                                requestResult.getTradeState())) {
                            // 已关闭的
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.CLOSE.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.CLOSE.getDescription());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);

                        } else if (StringUtils.equals(CmBankTradeStateEnum.REFUND.getValue(),
                                requestResult.getTradeState())) {
                            // 已完全退款，暂不处理
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.REFUND.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.REFUND.getDescription());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                        }
                    } else {
                        // 调用接口发生错误
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        orderQueryResult.setResultMessage(requestResult.getErrMsg());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }

                } else {
                    // 调用接口发生错误
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(requestResult.getMessage());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }

                return requestParams;

                // 中信银行查询订单
            } else if (StringUtils.equals(payChannelCode, PayChannelCode.CITICBANK.getValue())) {

                CiticBankQueryOrderParam param = new CiticBankQueryOrderParam();
                param.setKey(requestParams.getPayChannelKey());
                param.setMchId(requestParams.getPayChannelAccount());
                param.setOutTradeNo(requestParams.getPayOrderNo());
                CiticBankQueryOrderRequest rq = new CiticBankQueryOrderRequest(param);
                CiticBankQueryOrderResult requestResult = rq.doPostForObject();

                if (requestResult == null) {
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    return requestParams;
                }

                if (StringUtils.equals(CiticBankResultStatusEnum.SUCCESS.getValue(), requestResult.getStatus())) {
                    PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                    // 查询成功
                    if (StringUtils.equals(CiticBankResultCodeEnum.SUCCESS.getValue(), requestResult.getResultCode())) {
                        if (StringUtils.equals(CiticBankTradeStateEnum.SUCCESS.getValue(),
                                requestResult.getTradeState())) {
                            // 支付成功

                            RLock lock = redissonClient.getFairLock(payOrderRequestRecord.getPayOrderNo());
                            if (lock != null && lock.isLocked()) {
                                // 锁住了，说明其它业务正在处理回调成功
                                orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                                orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                                orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                                orderQueryResult.setTradeOrderNo(requestResult.getTransactionId());
                                requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                                return requestParams;
                            }
                            try {
                                if (lock != null) {
                                    lock.lock();
                                }


                                payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                                payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                payOrderRequestRecord.setTradeOrderNo(requestResult.getTransactionId());

                                PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
									PaymentOrderResponseRecord.class);
                                payOrderResponseRecord.setBankType(requestResult.getBankType());
                                payOrderResponseRecord.setFeeType(requestResult.getFeeType());

                                bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                                orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                                orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                                orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                                orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                                orderQueryResult.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
                                requestParams.setNotifyFlag(true);
                            } finally {
                                if (lock != null) {
                                    lock.unlock();
                                }
                            }

                        } else if (StringUtils.equals(CiticBankTradeStateEnum.NOTPAY.getValue(),
                                requestResult.getTradeState())) {
                            // 未支付，继续查询
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.UNPAY.getDescription());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                        } else if (StringUtils.equals(CiticBankTradeStateEnum.CLOSED.getValue(),
                                requestResult.getTradeState())) {
                            // 订单已关闭，更新支付中心订单状态
                            payOrderRequestRecord.setState(OrderPayState.CLOSE.getValue());
                            PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
								PaymentOrderResponseRecord.class);
                            bankTradeService.barcodePayFailResult(payOrderRequestRecord, payOrderResponseRecord);

                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.CLOSE.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.CLOSE.getDescription());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);

                        } else if (StringUtils.equals(CiticBankTradeStateEnum.REFUND.getValue(),
                                requestResult.getTradeState())) {
                            // 已完全退款，暂不处理
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.REFUND.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.REFUND.getDescription());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                        }
                    } else {
                        // 调用接口发生错误
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        orderQueryResult.setResultMessage(requestResult.getErrMsg());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }

                } else {
                    // 调用接口发生错误
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(requestResult.getMessage());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }

                return requestParams;
                // 银联的查询接口
            } else if (StringUtils.equals(payChannelCode, PayChannelCode.UNIONPAY.getValue())) {
                UnionpayQueryOrderParam param = new UnionpayQueryOrderParam();
                param.setMerchantCode(requestParams.getPayChannelAccount());
                param.setTerminalCode(requestParams.getPayChannelKey());
                param.setMerchantOrderId(requestParams.getPayOrderNo());

                UnionpayQueryOrderRequest rq = new UnionpayQueryOrderRequest(param);
                UnionpayQueryOrderResult requestResult = rq.doRequestForObject();
                // errCode等于00表示查询成功
                PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                if (StringUtils.equals(UnionpayStateEnum.SUCCESS.getValue(), requestResult.getErrCode())) {
                    /*
                     * queryResCode 0：成功 1：超时 2：已撤销 3：已退货 4：已冲正 5：失败（失败情况，后面追加失败描述 FF：交易状态未知
                     */
                    if (StringUtils.equals(UnionpayStateEnum.QUERYSUCCESS.getValue(), requestResult.getQueryResCode())) {
                        /**
                         * <pre>
                         * 需要同步的业务有
                         * 1.支付返回成功的处理
                         * 2.回调支付成功的处理
                         * 3.查询支付结果成功的处理
                         * </pre>
                         */
                        RLock lock = redissonClient.getFairLock(payOrderRequestRecord.getPayOrderNo());
                        if (lock != null && lock.isLocked()) {
                            // 锁住了，说明其它业务正在处理回调成功
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(requestResult.getOrderId());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                            return requestParams;
                        }

                        try {
                            if (lock != null) {
                                lock.lock();
                            }
                            payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                            payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderId());
                            payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(requestResult.getOriginalTransactionTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
                            payOrderRequestRecord.setErrCode(requestResult.getErrCode());
                            payOrderRequestRecord.setErrMsg(requestResult.getErrInfo());

                            PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
								PaymentOrderResponseRecord.class);

                            payOrderResponseRecord.setPayChannelAccount(requestParams.getPayChannelAccount());
                            bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                            orderQueryResult.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
                            requestParams.setNotifyFlag(true);
                        } finally {
                            if (lock != null) {
                                lock.unlock();
                            }
                        }

                    } else if (StringUtils.equals(UnionpayStateEnum.QUERYTIMEOUT.getValue(),
                            requestResult.getQueryResCode())) {
                        // 查询超时
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.TIMEOUT.getValue());
                        orderQueryResult.setResultMessage(requestResult.getQueryResDesc());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    } else if (StringUtils.equals(UnionpayStateEnum.QUERYFAIL.getValue(),
                            requestResult.getQueryResCode())) {
                        // 查询失败
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        orderQueryResult.setResultMessage(requestResult.getErrInfo());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }
                } else {
                    // 查询失败
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(requestResult.getErrInfo());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }

                return requestParams;
                // 建设银行
            } else if (StringUtils.equals(payChannelCode, PayChannelCode.CCB.getValue())) {
                // 查询请求参数
                ConstructionBankQueryOrderParam param = new ConstructionBankQueryOrderParam();
                param.setMchId(requestParams.getPayChannelAccount());
                param.setKey(requestParams.getPayChannelKey());
                param.setOutTradeNo(requestParams.getPayOrderNo());
                param.setPosId(requestParams.getPayChannelExtendJson());
                // 支付宝支付
                if (StringUtils.equals(PaymentModeCode.ALIPAY_BARCODE.getValue(), requestParams.getPaymentModeCode())) {
                    param.setQrCodeType(CcBankQrCodeType.ALIPAY.getValue());
                    // 微信支付
                } else if (StringUtils
                        .equals(PaymentModeCode.WX_BARCODE.getValue(), requestParams.getPaymentModeCode())) {
                    param.setQrCodeType(CcBankQrCodeType.WXPAY.getValue());
                    // 龙支付
                } else if (StringUtils.equals(PaymentModeCode.DRAGONPAY_BARCODE.getValue(), requestParams
                        .getPayOrderRequestRecord().getPaymentModeCode())) {
                    param.setQrCodeType(CcBankQrCodeType.DRAGONPAY.getValue());
                } else if (StringUtils.equals(PaymentModeCode.UNIONPAY_BARCODE.getValue(), requestParams
                        .getPayOrderRequestRecord().getPaymentModeCode())) {
                    requestParams.getPayOrderRequestRecord().setPayChannelCode(
                            PaymentModeCode.UNIONPAY_BARCODE.getValue());
                    param.setQrCodeType(CcBankQrCodeType.DRAGONPAY.getValue());
                }

                // 查询请求
                ConstructionBankQueryOrderRequest request = new ConstructionBankQueryOrderRequest(param);
                ConstructionBankQueryOrderResult requestResult = request.doRequestForObject();
                PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                if (requestResult == null) {
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    return requestParams;
                }
                // 查询成功
                if (StringUtils.equals(CcBankResultType.SUCCESS.getValue(), requestResult.getResult())) {
                    /**
                     * <pre>
                     * 需要同步的业务有
                     * 1.支付返回成功的处理
                     * 2.回调支付成功的处理
                     * 3.查询支付结果成功的处理
                     * </pre>
                     */
                    RLock lock = redissonClient.getFairLock(payOrderRequestRecord.getPayOrderNo());
                    if (lock != null && lock.isLocked()) {
                        // 锁住了，说明其它业务正在处理回调成功
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                        orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                        orderQueryResult.setTradeOrderNo(requestResult.getOrderId());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                        return requestParams;
                    }

                    try {
                        if (lock != null) {
                            lock.lock();
                        }
                        payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                        payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderId());
                        // payOrderRequestRecord.setPaySuccessTime(new Date());
                        payOrderRequestRecord.setErrCode(requestResult.getErrCode());
                        payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());

                        PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);

                        payOrderResponseRecord.setPayChannelAccount(requestParams.getPayChannelAccount());
                        bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                        orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                        orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                        // 将龙支付方式返回给业务系统端
                        orderQueryResult.setPayMode(payOrderRequestRecord.getPaymentModeCode());

                        requestParams.setNotifyFlag(true);
                    } finally {
                        if (lock != null) {
                            lock.unlock();
                        }
                    }
                } else if (StringUtils.equals(CcBankResultType.FAIL.getValue(), requestResult.getResult())) {
                    // 查询失败 继续查询
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
                    orderQueryResult.setResultMessage(requestResult.getErrMsg());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                } else {
                    // 继续查询
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(requestResult.getErrMsg());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }
            }

            // 郵政銀行
            if (StringUtils.equals(payChannelCode, PayChannelCode.POSTBANK.getValue())) {
                PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                // 參數封裝
                PostBankQueryOrderParam param = new PostBankQueryOrderParam();
                param.setMchId(requestParams.getPayChannelAccount());
                param.setKey(requestParams.getPayChannelKey());
                param.setAppId(requestParams.getPayChannelExtendJson());
                param.setOutTradeNo(requestParams.getPayOrderNo());

                PostBankQueryOrderRequest request = PostBankQueryOrderRequest.getInstance(param);
                PostBankQueryOrderResult result = request.doRequestForObject();

                if (result == null) {
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    return requestParams;
                }

                if (StringUtils.equals(PostBankCallBankCodeEnum.SUCCESS.getStatus(), result.getRetCode())) {
                    if (StringUtils.equals(PostBankCallBankCodeEnum.TRADE_SUCCESS.getStatus(), result.getTrxStatus())) {
                        /**
                         * <pre>
                         * 需要同步的业务有
                         * 1.支付返回成功的处理
                         * 2.回调支付成功的处理
                         * 3.查询支付结果成功的处理
                         * </pre>
                         */
                        RLock lock = redissonClient.getFairLock(payOrderRequestRecord.getPayOrderNo());
                        if (lock != null && lock.isLocked()) {
                            // 锁住了，说明其它业务正在处理回调成功
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(result.getTrxId());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                            return requestParams;
                        }
                        try {
                            if (lock != null) {
                                lock.lock();
                            }
                            // 查詢成功
                            payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                            payOrderRequestRecord.setTradeOrderNo(result.getTrxId());
                            payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(result.getFinTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
                            payOrderRequestRecord.setErrCode(result.getRetCode());
                            payOrderRequestRecord.setErrMsg(result.getRetMsg());

                            PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
								PaymentOrderResponseRecord.class);

                            payOrderResponseRecord.setPayChannelAccount(requestParams.getPayChannelAccount());
                            bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                            orderQueryResult.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
                            requestParams.setNotifyFlag(true);
                        } finally {
                            if (lock != null) {
                                lock.unlock();
                            }
                        }

                    } else {
                        // 查詢失敗
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        orderQueryResult.setResultMessage(result.getRetMsg());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }
                } else {
                    // 查詢失敗
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(result.getRetMsg());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }

            }

            // 中国银行
            if (StringUtils.equals(PayChannelCode.CHINABANK.getValue(), requestParams.getPayChannelCode())) {
                PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();

                // 查询请求参数
                ChinaBankQueryOrderParam param = new ChinaBankQueryOrderParam();
                param.setMchId(requestParams.getPayChannelAccount());
                param.setKey(requestParams.getPayChannelKey());
                param.setPosNo(requestParams.getPayChannelExtendJson());
                param.setOrgSysTrace(payOrderRequestRecord.getTradeOrderNo());
                param.setOutTradeNo(requestParams.getPayOrderNo());
                // 请求查询接口
                ChinaBankQueryOrderRequest request = new ChinaBankQueryOrderRequest(param);
                ChinaBankCallbackResult result = request.doPostForObject();
                if (!ObjectUtils.isEmpty(result)) {
                    if (StringUtils.equals(ChinaBankCallbackResultCodeEnum.SUCCESS.getValue(), result.getRespCode())) {
                        /**
                         * <pre>
                         * 需要同步的业务有
                         * 1.支付返回成功的处理
                         * 2.回调支付成功的处理
                         * 3.查询支付结果成功的处理
                         * </pre>
                         */
                        RLock lock = redissonClient.getFairLock(payOrderRequestRecord.getPayOrderNo());
                        if (lock != null && lock.isLocked()) {
                            // 锁住了，说明其它业务正在处理回调成功
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(result.getTradeNo());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                            return requestParams;
                        }
                        try {
                            if (lock != null) {
                                lock.lock();
                            }
                            // 查询成功
                            payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                            payOrderRequestRecord.setTradeOrderNo(result.getSysTrace());
                            payOrderRequestRecord.setErrCode(result.getRespCode());
                            payOrderRequestRecord.setErrMsg(result.getRespMsg());
                            PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
								PaymentOrderResponseRecord.class);
                            payOrderResponseRecord.setPayChannelAccount(requestParams.getPayChannelAccount());
                            bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                            requestParams.setNotifyFlag(true);
                        } finally {
                            if (lock != null) {
                                lock.unlock();
                            }
                        }

                    } else {
                        // 查询失败
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }
                } else {
                    // 查询失败
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }
            }

            // 农业银行
            if (StringUtils.equals(payChannelCode, PayChannelCode.ABCBANK.getValue())) {
                // 查询请求参数
                AbcBankQueryOrderParam param = new AbcBankQueryOrderParam();
                param.setMchId(requestParams.getPayChannelAccount());
                param.setTerminateCode(requestParams.getPayChannelKey());
                param.setMchPrivateKey(requestParams.getMchPrivateKey());
                param.setOutTradeNo(requestParams.getPayOrderNo());

                AbcBankQueryOrderRequest request = new AbcBankQueryOrderRequest(param);
                AbcBankQueryOrderResult result = request.doPostForObject();
                PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
                if (!ObjectUtils.isEmpty(result)) {
                    if (StringUtils.equals(AbcBankCallbackResultCodeEnum.SUCCESS.getValue(), result.getReturnCode())) {

                        /**
                         * <pre>
                         * 需要同步的业务有
                         * 1.支付返回成功的处理
                         * 2.回调支付成功的处理
                         * 3.查询支付结果成功的处理
						 * </pre>
                         */
                        RLock lock = redissonClient.getFairLock(payOrderRequestRecord.getPayOrderNo());
                        if (lock != null && lock.isLocked()) {
                            // 锁住了，说明其它业务正在处理回调成功
                            orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                            orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                            orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                            orderQueryResult.setTradeOrderNo(result.getOrderId());
                            requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                            return requestParams;
                        }
                        // 查询成功
                        payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                        payOrderRequestRecord.setTradeOrderNo(result.getOrderId());
                        payOrderRequestRecord.setErrCode(result.getReturnCode());
                        payOrderRequestRecord.setErrMsg(result.getReturnMsg());
                        PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
                        payOrderResponseRecord.setPayChannelAccount(requestParams.getPayChannelAccount());
                        bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
                        orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
                        orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
                        orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
                        requestParams.setNotifyFlag(true);
                    } else {
                        // 查询失败
                        orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                        orderQueryResult.setResultMessage(result.getReturnMsg());
                        requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                    }
                } else {
                    // 查询失败
                    orderQueryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
                    orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
                    requestParams.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
                }
            }

        }
        return requestParams;
    }

}
