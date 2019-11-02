package com.junfeng.platform.payment.service.responsibilitychain.refundorder;

import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.bank.abcbank.common.AbcBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankRefundOrderParam;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankRefundOrderResult;
import com.junfeng.platform.payment.bank.abcbank.request.AbcBankRefundOrderRequest;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankPayTypeEnum;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankCallbackResult;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankRefundParam;
import com.junfeng.platform.payment.bank.chinabank.request.ChinaBankRefundRequest;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultCodeEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultStatusEnum;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankRefundParam;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankRefundResult;
import com.junfeng.platform.payment.bank.citicbank.request.CiticBankRefundRequest;
import com.junfeng.platform.payment.bank.constructionbank.common.CcBankResultType;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankRefundParam;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankRefundResult;
import com.junfeng.platform.payment.bank.constructionbank.request.ConstructionBankRefundRequest;
import com.junfeng.platform.payment.bank.jxbank.common.JxBankResultRetcodeEnum;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankRefundOrderParam;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankRefundOrderResult;
import com.junfeng.platform.payment.bank.jxbank.request.JxBankRefundRequest;
import com.junfeng.platform.payment.bank.postbank.common.PostBankCallBankCodeEnum;
import com.junfeng.platform.payment.bank.postbank.model.PostBankRefundOrderParam;
import com.junfeng.platform.payment.bank.postbank.model.PostBankRefundOrderResult;
import com.junfeng.platform.payment.bank.postbank.request.PostBankRefundOrderRequest;
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayStateEnum;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundResult;
import com.junfeng.platform.payment.bank.unionpay.request.UnionpayRefundRequest;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.*;
import com.junfeng.platform.payment.controller.pay.vo.type.RefundResultCodeEnum;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.model.RefundOrderHandleParam;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 退款请求责任链
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月7日 上午9:45:09
 */
@Service
public class RefundOrderRefundRequestHandler extends AbstractHandler<RefundOrderHandleParam> {

	private final static Logger LOGGER = LogManager.getLogger();

	@Override
	public RefundOrderHandleParam handleRequest(RefundOrderHandleParam requestParam) throws Exception {
		ResponsibilityChainHandlerStateEnum handleState = requestParam.getHandlerState();
		if (handleState == null || handleState.equals(ResponsibilityChainHandlerStateEnum.EXCEPTION)) {
			return requestParam;
		}

		if (handleState.equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {
			// 支付渠道
			String payChannelCode = requestParam.getPayChannelCode();
			PaymentRefundOrder payRefundOrder = requestParam.getPayRefundOrder();
			PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
			// 银联
			if (StringUtils.equals(payChannelCode, PayChannelCode.UNIONPAY.getValue())) {
				// 设置参数
				UnionpayRefundParam param = new UnionpayRefundParam();
				param.setMerchantCode(requestParam.getPayChannelAccount());
				param.setTerminalCode(requestParam.getPayChannelKey());
				param.setMerchantOrderId(requestParam.getPayOrderNo());
				param.setRefundRequestId(requestParam.getRefundOrderNo());
				param.setTransactionAmount(requestParam.getRefundAmount());

				// 请求银联接口
				UnionpayRefundRequest rq = new UnionpayRefundRequest(param);
				UnionpayRefundResult requestResult = rq.doRequestForObject();
				// 退款成功
				if (StringUtils.equals(requestResult.getErrCode(), UnionpayStateEnum.SUCCESS.getValue())) {
					requestParam.setRefundSuccess(true);
					payRefundOrder.setErrCode(requestResult.getErrCode());
					payRefundOrder.setErrMsg(requestResult.getErrInfo());
					payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
					payRefundOrder.setRefundSuccessTime(getRefundSuccessTime(requestResult));

					payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.SUCCESS.getDescription());
				} else {
					// 退款失败
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(requestResult.getErrCode());
					payRefundOrder.setErrMsg(requestResult.getErrInfo());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
				// 中信银行
			} else if (StringUtils.equals(payChannelCode, PayChannelCode.CITICBANK.getValue())) {
				CiticBankRefundParam param = new CiticBankRefundParam();
				param.setKey(requestParam.getPayChannelKey());
				param.setMchId(requestParam.getPayChannelAccount());
				param.setOutTradeNo(requestParam.getPayOrderNo());
				param.setOutRefundNo(requestParam.getRefundOrderNo());
				param.setTotalFee(requestParam.getRefundAmount());
				param.setRefundFee(requestParam.getRefundAmount());

				CiticBankRefundRequest request = new CiticBankRefundRequest(param);
				CiticBankRefundResult result = request.doRequestForObject();

				LOGGER.info("CiticBankRefundResult:{}", result.toString());

				if (StringUtils.equals(result.getStatus(), CiticBankResultStatusEnum.SUCCESS.getValue())) {
					if (StringUtils.equals(result.getResultCode(), CiticBankResultCodeEnum.SUCCESS.getValue())) {
						// 退款成功
						requestParam.setRefundSuccess(true);
						payRefundOrder.setErrCode(result.getResultCode());
						payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
						payRefundOrder.setRefundSuccessTime(LocalDateTime.now());

						payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
						requestParam.getRefundOrderResult().setResultMessage(
							RefundResultCodeEnum.SUCCESS.getDescription());
					} else {
						// 退款失败
						requestParam.setRefundSuccess(false);
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
						payRefundOrder.setErrCode(result.getResultCode());
						payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
						requestParam.getRefundOrderResult()
							.setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
					}
				} else {
					// 退款失败
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(result.getStatus());
					payRefundOrder.setErrMsg(result.getMessage());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
				// 江西银行
			} else if (StringUtils.equals(payChannelCode, PayChannelCode.JXBANK.getValue())) {
				// 传入退款请求参数
				JxBankRefundOrderParam param = new JxBankRefundOrderParam();
				param.setKey(requestParam.getPayChannelKey());
				param.setMchId(requestParam.getPayChannelAccount());
				param.setPayOrderNo(requestParam.getPayOrderNo());
				param.setRefundOrderNo(requestParam.getRefundOrderNo());
				param.setRefundFee(requestParam.getRefundAmount());

				JxBankRefundRequest request = new JxBankRefundRequest(param);
				JxBankRefundOrderResult result = request.doRequestForObject();

				if (JxBankResultRetcodeEnum.SUCCESS.getValue().equals(result.getRetcode())) {
					// 退款成功
					requestParam.setRefundSuccess(true);
					payRefundOrder.setErrCode(result.getRetmsg());
					payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
					payRefundOrder.setRefundSuccessTime(LocalDateTime.now());

					payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.SUCCESS.getDescription());
				} else {
					// 退款失败
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(result.getRetcode().toString());
					payRefundOrder.setErrMsg(result.getRetmsg());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
				// 建设银行
			} else if (StringUtils.equals(payChannelCode, PayChannelCode.CCB.getValue())) {
				// 传入退款请求参数
				ConstructionBankRefundParam param = new ConstructionBankRefundParam();
				param.setAmount(Double.valueOf(requestParam.getPayAmount()) / 100);
				param.setMrchNo(requestParam.getPayChannelAccount());
				param.setOutTradeNo(requestParam.getPayOrderNo());
				param.setRefundNo(String.valueOf(System.currentTimeMillis()));

				ConstructionBankRefundRequest request = new ConstructionBankRefundRequest(param);
				ConstructionBankRefundResult requestResult = request.doRequestForObject();
				// 退款成功
				if (StringUtils.equals(CcBankResultType.REFUND_SUCCESS.getValue(), requestResult.getReturnCode())) {
					requestParam.setRefundSuccess(true);
					payRefundOrder.setErrCode(CcBankResultType.REFUND_SUCCESS.getDescription());
					payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
					payRefundOrder.setRefundSuccessTime(LocalDateTime.now());

					payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.SUCCESS.getDescription());
				} else {
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(requestResult.getReturnCode());
					payRefundOrder.setErrMsg(requestResult.getReturnMsg());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
			}

			// 邮政银行
			if (StringUtils.equals(PayChannelCode.POSTBANK.getValue(), payChannelCode)) {
				// 封装参数
				PostBankRefundOrderParam param = new PostBankRefundOrderParam();
				param.setMchId(requestParam.getPayChannelAccount());
				param.setKey(requestParam.getPayChannelKey());
				param.setAppId(requestParam.getPayChannelExtendJson());
				param.setOutTradeNo(requestParam.getPayOrderNo());
				param.setRefundAmount(requestParam.getRefundAmount().toString());
				param.setRefundOrderNo(requestParam.getRefundOrderNo());

				PostBankRefundOrderRequest request = PostBankRefundOrderRequest.getInstance(param);
				PostBankRefundOrderResult result = request.doRequestForObject();

				if (StringUtils.equals(PostBankCallBankCodeEnum.SUCCESS.getStatus(), result.getRetCode())) {
					if (StringUtils.equals(PostBankCallBankCodeEnum.TRADE_SUCCESS.getStatus(), result.getTrxStatus())) {
						// 退款成功
						String finTime = result.getFinTime();
						requestParam.setRefundSuccess(true);
						payRefundOrder.setErrCode(PostBankCallBankCodeEnum.TRADE_SUCCESS.getStatus());
						payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
						payRefundOrder.setRefundSuccessTime(LocalDateTime.parse(result.getFinTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

						payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
						requestParam.getRefundOrderResult().setResultMessage(
							RefundResultCodeEnum.SUCCESS.getDescription());
					} else {
						// 退款失败
						requestParam.setRefundSuccess(false);
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
						payRefundOrder.setErrCode(result.getRetCode());
						payRefundOrder.setErrMsg(result.getRetMsg());
						payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
						requestParam.getRefundOrderResult()
							.setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
					}
				} else {
					// 退款失败
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(result.getRetCode());
					payRefundOrder.setErrMsg(result.getRetMsg());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
			}

			// 中国银行
			if (StringUtils.equals(PayChannelCode.CHINABANK.getValue(), requestParam.getPayChannelCode())) {
				// 退款请求参数
				ChinaBankRefundParam param = new ChinaBankRefundParam();
				param.setMchId(requestParam.getPayChannelAccount());
				param.setKey(requestParam.getPayChannelKey());
				param.setPosNo(requestParam.getPayChannelExtendJson());
				param.setOrgSysTrace(payOrderRequestRecord.getTradeOrderNo());
				param.setOrgTxnTime(payOrderRequestRecord.getPaySuccessTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				param.setOutTradeNo(payOrderRequestRecord.getPayOrderNo());
				param.setVFTradeNo(requestParam.getRefundOrderNo());
				param.setTxnAmt(payOrderRequestRecord.getAmount());
				// 微信支付
				if (StringUtils.equals(payOrderRequestRecord.getPaymentModeCode(),
					PaymentModeCode.WX_BARCODE.getValue())) {
					param.setPayType(ChinaBankPayTypeEnum.WEIX.getValue());
				}
				// 支付宝支付
				if (StringUtils.equals(payOrderRequestRecord.getPaymentModeCode(),
					PaymentModeCode.ALIPAY_BARCODE.getValue())) {
					param.setPayType(ChinaBankPayTypeEnum.ZFBA.getValue());
				}
				// 退款请求接口
				ChinaBankRefundRequest request = new ChinaBankRefundRequest(param);
				ChinaBankCallbackResult result = request.doPostForObject();
				if (!ObjectUtils.isEmpty(result)) {
					if (StringUtils.equals(ChinaBankCallbackResultCodeEnum.SUCCESS.getValue(), result.getRespCode())) {
						// 退款成功
						requestParam.setRefundSuccess(true);
						payRefundOrder.setErrCode(CcBankResultType.REFUND_SUCCESS.getDescription());
						payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
						payRefundOrder.setRefundSuccessTime(LocalDateTime.now());
						payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());
						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
						requestParam.getRefundOrderResult().setResultMessage(
							RefundResultCodeEnum.SUCCESS.getDescription());
					} else {
						// 退款失败
						requestParam.setRefundSuccess(false);
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
						payRefundOrder.setErrCode(result.getRespCode());
						payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
						requestParam.getRefundOrderResult()
							.setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
					}
				} else {
					// 退款失败
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(result.getRespCode());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
			}

			// 农业银行
			if (StringUtils.equals(payChannelCode, PayChannelCode.ABCBANK.getValue())) {
				// 退款请求参数
				AbcBankRefundOrderParam param = new AbcBankRefundOrderParam();
				param.setMchId(requestParam.getPayChannelAccount());
				param.setTerminateCode(requestParam.getPayChannelKey());
				param.setBankPublicKey(requestParam.getPayChannelExtendJson());
				param.setMchPrivateKey(requestParam.getMchPrivateKey());
				param.setOutTradeNo(requestParam.getPayOrderNo());
				// param.setTradeOrderId(requestParam.getPayOrderRequestRecord().getTradeOrderNo());
				param.setRefundNo(requestParam.getRefundOrderNo());
				param.setRefundAmount(requestParam.getRefundAmount().toString());

				AbcBankRefundOrderRequest request = new AbcBankRefundOrderRequest(param);
				AbcBankRefundOrderResult result = request.doPostForObject();

				if (!ObjectUtils.isEmpty(result)) {
					if (StringUtils.equals(AbcBankCallbackResultCodeEnum.SUCCESS.getValue(), result.getReturnCode())) {
						// 退款成功
						requestParam.setRefundSuccess(true);
						payRefundOrder.setErrCode(CcBankResultType.REFUND_SUCCESS.getDescription());
						payRefundOrder.setState(OrderRefundStateEnum.SUCCESS.getValue());
						payRefundOrder.setRefundSuccessTime(LocalDateTime.now());

						payOrderRequestRecord.setRefundState(OrderPayRefundState.REFUNDED.getValue());

						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.SUCCESS.getValue());
						requestParam.getRefundOrderResult().setResultMessage(
							RefundResultCodeEnum.SUCCESS.getDescription());
					} else {
						// 退款失败
						requestParam.setRefundSuccess(false);
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
						payRefundOrder.setErrCode(result.getReturnCode());
						payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
						requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
						requestParam.getRefundOrderResult()
							.setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
					}
				} else {
					// 退款失败
					requestParam.setRefundSuccess(false);
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					payRefundOrder.setErrCode(result.getReturnCode());
					payRefundOrder.setState(OrderRefundStateEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultState(RefundResultCodeEnum.FAIL.getValue());
					requestParam.getRefundOrderResult().setResultMessage(RefundResultCodeEnum.FAIL.getDescription());
				}
			}
		}
		return requestParam;
	}

	/**
	 * 退款成功时间
	 *
	 * @param requestResult
	 * @return
	 * @throws ParseException
	 * @author:xionghui
	 * @createTime:2018年9月7日 上午10:52:39
	 */
	private LocalDateTime getRefundSuccessTime(UnionpayRefundResult requestResult) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);

		// 转换格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = String.valueOf(year) + requestResult.getTransactionDate() + requestResult.getTransactionTime();
		Date refundSuccessTime = format.parse(date);
		Instant instant = refundSuccessTime.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		return instant.atZone(zoneId).toLocalDateTime();
	}

}
