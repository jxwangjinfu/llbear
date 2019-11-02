package com.junfeng.platform.payment.service.responsibilitychain.barcodepay;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.junfeng.platform.payment.bank.abcbank.model.AbcBankBarcodeParam;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankBarcodeResult;
import com.junfeng.platform.payment.bank.cmbank.common.CmBankResultCodeEnum;
import com.junfeng.platform.payment.bank.cmbank.common.CmBankResultStatusEnum;
import com.junfeng.platform.payment.bank.cmbank.model.CmBankBarcodeParam;
import com.junfeng.platform.payment.bank.cmbank.model.CmBankMircoPayResult;
import com.junfeng.platform.payment.bank.cmbank.request.CmBankBarcodeRequest;
import com.junfeng.platform.payment.bank.jxbank.common.JxBankResultRetcodeEnum;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankBarcodeParam;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankMicroPayResult;
import com.junfeng.platform.payment.bank.jxbank.request.JxBankWeChatPayBarcodeRequest;
import com.junfeng.platform.payment.common.type.OrderPayState;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.abcbank.common.AbcBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.abcbank.request.AbcBankBarcodeRequest;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankBarcodeParam;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankCallbackResult;
import com.junfeng.platform.payment.bank.chinabank.request.ChinaBankBarcodeRequest;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultCodeEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultStatusEnum;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankBarcodeParam;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankMircoPayResult;
import com.junfeng.platform.payment.bank.citicbank.request.CiticBankBarcodeRequest;
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
import com.junfeng.platform.payment.common.type.PaymentModeCode;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model.BarcodePayHandleParams;

/**
 * 微信刷卡支付
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月19日 下午3:15:10
 */
@Component
public class BarcodePayWeChatPayHandler extends AbstractHandler<BarcodePayHandleParams> {

	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public BarcodePayHandleParams handleRequest(BarcodePayHandleParams requestParam) throws Exception {
		ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
		if (handlerState == null || !ResponsibilityChainHandlerStateEnum.SUCCESS.equals(handlerState)) {
			return requestParam;
		}
		PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
		// 江西银行微信刷卡支付接口
		if (StringUtils.equals(requestParam.getPayChannelCode(), PayChannelCode.JXBANK.getValue())) {

			if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.WX_BARCODE.getValue())) {
				JxBankBarcodeParam jxBankBarcodeParam = new JxBankBarcodeParam();
				jxBankBarcodeParam.setAuthCode(requestParam.getAuthCode());
				jxBankBarcodeParam.setBody(requestParam.getBody());
				jxBankBarcodeParam.setKey(requestParam.getPayChannelKey());
				jxBankBarcodeParam.setOutTradeNo(requestParam.getPayOrderNo());
				jxBankBarcodeParam.setMchId(requestParam.getPayChannelAccount());
				jxBankBarcodeParam.setSpbillCreateIp(requestParam.getSpbillCreateIp());
				jxBankBarcodeParam.setTotalFee(String.valueOf(requestParam.getAmount()));
				JxBankWeChatPayBarcodeRequest request = new JxBankWeChatPayBarcodeRequest(jxBankBarcodeParam);
				JxBankMicroPayResult requestResult = request.doRequestForObject();

				// 先根据返回错误码判断是否支付成功
				// 支付成功
				if (JxBankResultRetcodeEnum.SUCCESS.getValue().equals(requestResult.getRetcode())) {
					requestParam.setPaySuccess(true);
					// 设置请求支付订单信息
					payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderNo());
					payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
					payOrderRequestRecord.setPaySuccessTime(
						LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
					payOrderRequestRecord.setErrCode(String.valueOf(requestResult.getRetcode()));
					payOrderRequestRecord.setErrMsg(requestResult.getRetmsg());
					// 需要保存结果入库

					// 支付成功,需要生成订单返回表
					PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
						PaymentOrderResponseRecord.class);
					payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
					requestParam.setPayOrderResponseRecord(payOrderResponseRecord);

				} else if (JxBankResultRetcodeEnum.NEED_PASSWORD.getValue().equals(requestResult.getRetcode())) {
					requestParam.setPaySuccess(false);
					// retcode=-2010 时，表示用户需要输入密码。出现该错误码时，依然会返回 order_no，请注意保存 order_no，且会
					// 通知回调地址，其他情况不会通知。
					payOrderRequestRecord.setErrCode(String.valueOf(requestResult.getRetcode()));
					payOrderRequestRecord.setErrMsg(requestResult.getRetmsg());
					payOrderRequestRecord.setTradeOrderNo(requestResult.getOrderNo());

				} else {
					// 支付失败
					requestParam.setPaySuccess(false);
					payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
					payOrderRequestRecord.setErrCode(String.valueOf(requestResult.getRetcode()));
					payOrderRequestRecord.setErrMsg(requestResult.getRetmsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
				}

			}
			// 招商银行微信刷卡支付接口
		} else if (StringUtils.equals(requestParam.getPayChannelCode(), PayChannelCode.CMB.getValue())) {
			if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.WX_BARCODE.getValue())) {
				// 请求招商银行接口
				CmBankBarcodeParam param = new CmBankBarcodeParam();
				param.setAuthCode(requestParam.getAuthCode());
				param.setBody(requestParam.getBody());
				param.setKey(requestParam.getPayChannelKey());
				param.setMchCreateIp(requestParam.getSpbillCreateIp());
				param.setMchId(requestParam.getPayChannelAccount());
				param.setOutTradeNo(requestParam.getPayOrderRequestRecord().getPayOrderNo());
				param.setTotalFee(requestParam.getPayOrderRequestRecord().getAmount());
				CmBankBarcodeRequest rq = new CmBankBarcodeRequest(param);
				CmBankMircoPayResult requestResult = rq.doPostForObject();

				// 先根据返回错误码判断是否支付成功
				// status和result_code字段返回都为0时，判定订单支付成功
				if (StringUtils.equals(CmBankResultStatusEnum.SUCCESS.getValue(), requestResult.getStatus())) {
					if (StringUtils.equals(CmBankResultCodeEnum.SUCCESS.getValue(), requestResult.getResultCode())) {
						// 支付成功
						requestParam.setPaySuccess(true);
						// 设置请求支付订单信息
						payOrderRequestRecord.setTradeOrderNo(requestResult.getTransactionId());
						payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
						payOrderRequestRecord.setPaySuccessTime(
							LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
						payOrderRequestRecord.setErrCode(requestResult.getStatus());
						payOrderRequestRecord.setErrMsg("SUCCESS");

						// 支付成功,需要生成订单返回表
						PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
						payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
						requestParam.setPayOrderResponseRecord(payOrderResponseRecord);
					} else {
						requestParam.setPaySuccess(false);
						// 所传参数不对，或者缺少参数,会返回状态码和信息描述，但不会返回平台订单号
						// 通知回调地址，其他情况不会通知。
						payOrderRequestRecord.setErrCode(requestResult.getErrCode());
						payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					}
				} else {
					// 支付失败,这里会返回平台订单号
					requestParam.setPaySuccess(false);
					payOrderRequestRecord.setErrCode(requestResult.getStatus());
					payOrderRequestRecord.setErrMsg(requestResult.getMessage());
					payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
				}
			}

			// 中信银行
		} else if (StringUtils.equals(requestParam.getPayChannelCode(), PayChannelCode.CITICBANK.getValue())) {
			if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.WX_BARCODE.getValue())) {
				// 请求中信银行接口
				CiticBankBarcodeParam param = new CiticBankBarcodeParam();
				param.setAuthCode(requestParam.getAuthCode());
				param.setBody(requestParam.getBody());
				param.setKey(requestParam.getPayChannelKey());
				param.setMchCreateIp(requestParam.getSpbillCreateIp());
				param.setMchId(requestParam.getPayChannelAccount());
				param.setOutTradeNo(requestParam.getPayOrderRequestRecord().getPayOrderNo());
				param.setTotalFee(requestParam.getPayOrderRequestRecord().getAmount());
				CiticBankBarcodeRequest rq = new CiticBankBarcodeRequest(param);
				CiticBankMircoPayResult requestResult = rq.doPostForObject();

				// 先根据返回错误码判断是否支付成功
				if (StringUtils.equals(CiticBankResultStatusEnum.SUCCESS.getValue(), requestResult.getStatus())) {
					// need_query为N时判定支付失败
					if (StringUtils.equals(CiticBankResultStatusEnum.NEED_QUERY_N.getValue(),
						requestResult.getNeedQuery())) {
						// 支付失败
						requestParam.setPaySuccess(false);
						payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
						payOrderRequestRecord.setErrCode(requestResult.getErrCode());
						payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					}
					// status和result_code字段返回都为0时，判定订单支付成功
					if (StringUtils.equals(CiticBankResultCodeEnum.SUCCESS.getValue(), requestResult.getResultCode())) {
						// 支付成功
						requestParam.setPaySuccess(true);
						// 设置请求支付订单信息
						payOrderRequestRecord.setTradeOrderNo(requestResult.getTransactionId());
						payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
						payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
						payOrderRequestRecord.setErrCode(requestResult.getStatus());
						payOrderRequestRecord.setErrMsg("SUCCESS");

						// 支付成功,需要生成订单返回表
						PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
						payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
						requestParam.setPayOrderResponseRecord(payOrderResponseRecord);
					} else {
						// 支付失败
						requestParam.setPaySuccess(false);
						payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
						payOrderRequestRecord.setErrCode(requestResult.getErrCode());
						payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					}
				} else {
					if (StringUtils.equals(CiticBankResultStatusEnum.NEED_QUERY_N.getValue(),
						requestResult.getNeedQuery())) {
						// 支付失败
						requestParam.setPaySuccess(false);
						// 所传参数不对，或者缺少参数,会返回状态码和信息描述，但不会返回平台订单号
						// 通知回调地址，其他情况不会通知。
						payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
						payOrderRequestRecord.setErrCode(requestResult.getErrCode());
						payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					}
				}
			}

			// 银联微信支付接口
		} else if (StringUtils.equals(requestParam.getPayChannelCode(), PayChannelCode.UNIONPAY.getValue())) {
			if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.WX_BARCODE.getValue())) {
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

				LOGGER.info("UnionpayMircoPayResult:{}", JSON.toJSONString(requestResult));

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
					payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
					payOrderRequestRecord.setErrCode(requestResult.getErrCode());
					payOrderRequestRecord.setErrMsg(requestResult.getErrInfo());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
				}
			}
			// 建设银行
		} else if (StringUtils.equals(requestParam.getPayChannelCode(), PayChannelCode.CCB.getValue())) {
			if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.WX_BARCODE.getValue())) {
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

				LOGGER.info("ConstructionBankBarcodeResult:{}", JSON.toJSONString(requestResult));
				if (StringUtils.equals(CcBankResultType.SUCCESS.getValue(), requestResult.getResult())) {
					// 支付成功
					requestParam.setPaySuccess(true);

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
					requestParam.setPayOrderResponseRecord(payOrderResponseRecord);

				} else if (StringUtils.equals(CcBankResultType.FAIL.getValue(), requestResult.getResult())) {
					// 支付失败
					requestParam.setPaySuccess(false);
					payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
					payOrderRequestRecord.setErrCode(requestResult.getErrCode());
					payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
				} else {
					// 待查询或者不确定的情况，发起轮询
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
			if (StringUtils.equals(requestParam.getPaymentModeCode(), PaymentModeCode.WX_BARCODE.getValue())) {
				// 参数封装
				PostBankBarcodeParam param = new PostBankBarcodeParam();
				param.setMchId(requestParam.getPayChannelAccount());
				param.setKey(requestParam.getPayChannelKey());
				param.setOutTradeNo(requestParam.getPayOrderNo());
				param.setTotalFee(requestParam.getAmount().toString());
				param.setAuthCode(requestParam.getAuthCode());
				param.setAppId(requestParam.getPayChannelExtendJson());
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
					payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
					payOrderRequestRecord.setErrCode(result.getRetCode());
					payOrderRequestRecord.setErrMsg(result.getRetMsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
				}

			}
		}

		// 中国银行
		if (StringUtils.equals(PayChannelCode.CHINABANK.getValue(), requestParam.getPayChannelCode())) {
			// 微信支付
			if (StringUtils.equals(PaymentModeCode.WX_BARCODE.getValue(), requestParam.getPaymentModeCode())) {
				ChinaBankBarcodeParam param = new ChinaBankBarcodeParam();
				param.setMchId(requestParam.getPayChannelAccount());
				param.setKey(requestParam.getPayChannelKey());
				param.setPosNo(requestParam.getPayChannelExtendJson());
				param.setAmount(requestParam.getAmount());
				param.setAuthCode(requestParam.getAuthCode());
				param.setOutTradeNo(requestParam.getPayOrderNo());

				ChinaBankBarcodeRequest request = new ChinaBankBarcodeRequest(param);
				ChinaBankCallbackResult result = request.doPostForObject();
				if (!ObjectUtils.isEmpty(result)) {
					if (StringUtils.equals(ChinaBankCallbackResultCodeEnum.SUCCESS.getValue(), result.getRespCode())) {
						// 支付成功
						requestParam.setPaySuccess(true);

						// 设置请求支付订单信息
						payOrderRequestRecord.setTradeOrderNo(result.getSysTrace());
						payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
						payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(result.getTxnTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
						payOrderRequestRecord.setErrCode(result.getRespCode());
						payOrderRequestRecord.setErrMsg(result.getRespMsg());

						// 支付成功,需要生成订单返回表
						PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
						payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
						requestParam.setPayOrderResponseRecord(payOrderResponseRecord);
					} else {
						// 支付失败
						requestParam.setPaySuccess(false);
						payOrderRequestRecord.setErrCode(result.getRespCode());
						payOrderRequestRecord.setErrMsg(result.getRespMsg());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
					}
				} else {
					// 支付失败
					requestParam.setPaySuccess(false);
					payOrderRequestRecord.setState(OrderPayState.ERROR.getValue());
					payOrderRequestRecord.setErrCode(result.getRespCode());
					payOrderRequestRecord.setErrMsg(result.getRespMsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);
				}
			}
		}

		// 农业银行
		if (StringUtils.equals(PayChannelCode.ABCBANK.getValue(), requestParam.getPayChannelCode())) {
			// 微信支付
			if (StringUtils.equals(PaymentModeCode.WX_BARCODE.getValue(), requestParam.getPaymentModeCode())) {
				AbcBankBarcodeParam param = new AbcBankBarcodeParam();
				param.setMchId(requestParam.getPayChannelAccount());
				param.setTerminateCode(requestParam.getPayChannelKey());
				param.setBankPublicKey(requestParam.getPayChannelExtendJson());
				param.setMchPrivateKey(requestParam.getMchPrivateKey());
				param.setAuthCode(requestParam.getAuthCode());
				param.setOutTradeNo(requestParam.getPayOrderNo());
				param.setAmount(requestParam.getAmount().toString());

				AbcBankBarcodeRequest request = new AbcBankBarcodeRequest(param);
				AbcBankBarcodeResult result = request.doPostForObject();
				if (!ObjectUtils.isEmpty(result)) {
					if (StringUtils.equals(AbcBankCallbackResultCodeEnum.SUCCESS.getValue(), result.getReturnCode())) {
						// 支付成功
						requestParam.setPaySuccess(true);

						// 设置请求支付订单信息
						payOrderRequestRecord.setTradeOrderNo(result.getOrderId());
						payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
						payOrderRequestRecord.setPaySuccessTime(LocalDateTime.now());
						payOrderRequestRecord.setUpdateTime(LocalDateTime.now());
						payOrderRequestRecord.setErrCode(result.getReturnCode());
						payOrderRequestRecord.setErrMsg(result.getReturnMsg());

						// 支付成功,需要生成订单返回表
						PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
						payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
						requestParam.setPayOrderResponseRecord(payOrderResponseRecord);
					} else {
						// 支付失败
						requestParam.setPaySuccess(false);
						payOrderRequestRecord.setErrCode(result.getReturnCode());
						payOrderRequestRecord.setErrMsg(result.getReturnMsg());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.EXCEPTION);

					}
				} else {
					// 支付失败
					requestParam.setPaySuccess(false);
					payOrderRequestRecord.setErrCode(result.getReturnCode());
					payOrderRequestRecord.setErrMsg(result.getReturnMsg());
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
	 * @throws ParseException
	 * @author:xionghui
	 * @createTime:2018年8月28日 下午3:52:15
	 */
	private LocalDateTime getPaySuccessTime(UnionpayMircoPayResult requestResult) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);

		// 转换格式
		String date = String.valueOf(year) + requestResult.getTransactionDate() + requestResult.getTransactionTime();
		Date paySuccessTime = DateUtils.parseDate(date, Locale.CHINA, "yyyyMMddHHmmss");

		Instant instant = paySuccessTime.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		return instant.atZone(zoneId).toLocalDateTime();
	}
}
