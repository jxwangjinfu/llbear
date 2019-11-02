package com.junfeng.platform.payment.service.responsibilitychain.barcodepay;

import com.junfeng.platform.payment.bank.constructionbank.common.CcBankQrCodeType;
import com.junfeng.platform.payment.bank.constructionbank.common.CcBankResultType;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankBarcodeParam;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankBarcodeResult;
import com.junfeng.platform.payment.bank.constructionbank.request.ConstructionBankBarcodeRequest;
import com.junfeng.platform.payment.bank.postbank.common.PostBankCallBankCodeEnum;
import com.junfeng.platform.payment.bank.postbank.model.PostBankBarcodeParam;
import com.junfeng.platform.payment.bank.postbank.model.PostBankBarcodeResult;
import com.junfeng.platform.payment.bank.postbank.request.PostBankBarcodeRequest;
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayStateEnum;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayBarcodeParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayMircoPayResult;
import com.junfeng.platform.payment.bank.unionpay.request.UnionpayBarcodeRequest;
import com.junfeng.platform.payment.common.BeanMapper;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayState;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.PaymentModeCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model.BarcodePayHandleParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 银联云闪付责任链
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月29日 上午9:56:36
 * @version 1.0
 */
@Component
public class BarcodePayUnionPayHandler extends AbstractHandler<BarcodePayHandleParams> {

    @Override
    public BarcodePayHandleParams handleRequest(BarcodePayHandleParams requestParam) throws Exception {

        ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
        if (handlerState == null || !ResponsibilityChainHandlerStateEnum.SUCCESS.equals(handlerState)) {
            return requestParam;
        }

        PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
        // 银联调用接口
        if (StringUtils.equals(PayChannelCode.UNIONPAY.getValue(), requestParam.getPayChannelCode())) {
            // 云闪付
            if (StringUtils.equals(PaymentModeCode.UNIONPAY_BARCODE.getValue(), requestParam.getPaymentModeCode())) {
                // 银联支付请求
                UnionpayBarcodeParam param = new UnionpayBarcodeParam();
                param.setMerchantCode(requestParam.getPayChannelAccount());
                param.setTerminalCode(requestParam.getPayChannelKey());
                param.setMerchantOrderId(requestParam.getPayOrderNo());
                param.setMerchantRemark(requestParam.getBody());
                param.setTransactionAmount(String.valueOf(requestParam.getAmount()));
                param.setPayCode(requestParam.getAuthCode());
                param.setBody(requestParam.getBody());

                UnionpayBarcodeRequest rq = new UnionpayBarcodeRequest(param);
                UnionpayMircoPayResult requestResult = rq.doRequestForObject();

                // 先根据返回错误码判断是否支付成功
                // 错误为00代表支付成功
                if (StringUtils.equals(UnionpayStateEnum.SUCCESS.getValue(), requestResult.getErrCode())) {
                    requestParam.setPaySuccess(true);

					LocalDateTime paySuccessTime = getPaySuccessTime(requestResult);
                    // 设置请求支付订单信息
                    payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderId());
                    payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                    payOrderRequestRecord.setPaySuccessTime(paySuccessTime);
                    payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
                    payOrderRequestRecord.setErrCode(requestResult.getErrCode());
                    payOrderRequestRecord.setErrMsg(requestResult.getErrInfo());

                    // 支付成功,需要生成订单返回表
                    PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
						PaymentOrderResponseRecord.class);
                    payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
                    requestParam.setPayOrderResponseRecord(payOrderResponseRecord);
                } else {
                    requestParam.setPaySuccess(false);
                    // 支付失败
                    payOrderRequestRecord.setErrCode(requestResult.getErrCode());
                    payOrderRequestRecord.setErrMsg(requestResult.getErrInfo());
                    requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                }
            }
            // 建行调用接口
        } else if (StringUtils.equals(PayChannelCode.CCB.getValue(), requestParam.getPayChannelCode())) {
            // 龙支付
            if (StringUtils.equals(PaymentModeCode.UNIONPAY_BARCODE.getValue(), requestParam.getPaymentModeCode())) {
                // 建行支付请求参数
                ConstructionBankBarcodeParam param = new ConstructionBankBarcodeParam();
                param.setMchId(requestParam.getPayChannelAccount());
                param.setKey(requestParam.getPayChannelKey());
                param.setOutTradeNo(requestParam.getPayOrderNo());
                param.setAmount(requestParam.getAmount().doubleValue() / 100);
                param.setQrCode(requestParam.getAuthCode());
                param.setPosId(requestParam.getPayChannelExtendJson());
                param.setBody(requestParam.getBody());

                // 支付请求
                ConstructionBankBarcodeRequest request = new ConstructionBankBarcodeRequest(param);
                ConstructionBankBarcodeResult requestResult = request.doRequestForObject();

                if (StringUtils.equals(CcBankResultType.SUCCESS.getValue(), requestResult.getResult())) {
                    // 支付成功
                    requestParam.setPaySuccess(true);

                    // 判断支付码是龙支付
                    if (StringUtils.equals(requestResult.getQrCodeType(), CcBankQrCodeType.DRAGONPAY.getValue())) {
                        payOrderRequestRecord.setPaymentModeCode(PaymentModeCode.DRAGONPAY_BARCODE.getValue());
                    }

                    // 设置请求支付订单信息
                    payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderId());
                    payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                    // payOrderRequestRecord.setPaySuccessTime(new Date());
                    payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
                    payOrderRequestRecord.setErrCode(requestResult.getErrCode());
                    payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());

                    // 支付成功,需要生成订单返回表
                    PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
						PaymentOrderResponseRecord.class);
                    payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
                    requestParam.setPayOrderRequestRecord(payOrderRequestRecord);
                    requestParam.setPayOrderResponseRecord(payOrderResponseRecord);

                } else {
                    // 支付失败
                    requestParam.setPaySuccess(false);
                    payOrderRequestRecord.setErrCode(requestResult.getErrCode());
                    payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());
                    requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                }
            }
        }

        // 邮政银行
        if (StringUtils.equals(requestParam.getPayChannelCode(), PayChannelCode.POSTBANK.getValue())) {
            // 微信支付
            if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.UNIONPAY_BARCODE.getValue())) {
                // 参数封装
                PostBankBarcodeParam param = new PostBankBarcodeParam();
                param.setMchId(requestParam.getPayChannelAccount());
                param.setKey(requestParam.getPayChannelKey());
                param.setOutTradeNo(requestParam.getPayOrderNo());
                param.setTotalFee(requestParam.getAmount().toString());
                param.setAuthCode(requestParam.getAuthCode());
                param.setBody(requestParam.getBody());

                // 請求銀行接口
                PostBankBarcodeRequest request = PostBankBarcodeRequest.getInstance(param);
                PostBankBarcodeResult result = request.doRequestForObject();
                if (StringUtils.equals(PostBankCallBankCodeEnum.SUCCESS.getStatus(), result.getRetCode())) {
                    if (StringUtils.equals(PostBankCallBankCodeEnum.TRADE_SUCCESS.getStatus(), result.getTrxStatus())) {
                        // 支付成功
                        requestParam.setPaySuccess(true);

                        // 设置请求支付订单信息
                        payOrderRequestRecord.setTradeOrderNo(result.getTrxId());
                        payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
                        payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(result.getFinTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
                        payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
                        payOrderRequestRecord.setErrCode(result.getRetCode());
                        payOrderRequestRecord.setErrMsg(result.getRetMsg());

                        // 支付成功,需要生成订单返回表
                        PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
                        payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
                        requestParam.setPayOrderResponseRecord(payOrderResponseRecord);
                    } else {
                        // 支付失敗
                        requestParam.setPaySuccess(false);
                        payOrderRequestRecord.setErrCode(result.getRetCode());
                        payOrderRequestRecord.setErrMsg(result.getRetMsg());
                        requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                    }
				} else {
                    // 支付失敗
                    requestParam.setPaySuccess(false);
                    payOrderRequestRecord.setErrCode(result.getRetCode());
                    payOrderRequestRecord.setErrMsg(result.getRetMsg());
                    requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
                }

            }
        }

        return requestParam;
    }

    /**
     * 获得支付成功时间
     *
     * @param requestResult
     * @return
     * @author:xionghui
     * @throws ParseException
     * @createTime:2018年8月28日 下午3:52:15
     */
    private LocalDateTime getPaySuccessTime(UnionpayMircoPayResult requestResult) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);

        // 转换格式
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = String.valueOf(year) + requestResult.getTransactionDate() + requestResult.getTransactionTime();
        Date paySuccessTime = format.parse(date);
		Instant instant = paySuccessTime.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		return instant.atZone(zoneId).toLocalDateTime();
    }

}
