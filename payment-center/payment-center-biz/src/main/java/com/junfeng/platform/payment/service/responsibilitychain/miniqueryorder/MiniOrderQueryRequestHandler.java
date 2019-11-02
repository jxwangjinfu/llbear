package com.junfeng.platform.payment.service.responsibilitychain.miniqueryorder;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultCodeEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankResultStatusEnum;
import com.junfeng.platform.payment.bank.citicbank.common.CiticBankTradeStateEnum;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniQueryParam;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniQueryResult;
import com.junfeng.platform.payment.bank.citicbankmini.request.CiticBankMiniQueryRequest;
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayStateEnum;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniStateEnum;
import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniQueryResult;
import com.junfeng.platform.payment.bank.unionpaymini.request.UnionpayMiniQueryResultRequest;
import com.junfeng.platform.payment.bank.unionpaymini.utils.UnionpayMiniSignUtils;
import com.junfeng.platform.payment.common.BeanMapper;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderPayState;
import com.junfeng.platform.payment.common.type.PayChannelCode;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.MiniOrderQueryResult;
import com.junfeng.platform.payment.controller.pay.vo.type.OrderQueryResultCodeEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.service.bank.BankTradeService;
import com.junfeng.platform.payment.service.responsibilitychain.miniqueryorder.model.MiniOrderQueryHandleParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Component
public class MiniOrderQueryRequestHandler extends AbstractHandler<MiniOrderQueryHandleParam> {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private BankTradeService bankTradeService;

	@Autowired
	private UnionpayMiniQueryResultRequest unionpayMiniQueryResultRequest;

	@Override
	public MiniOrderQueryHandleParam handleRequest(MiniOrderQueryHandleParam requestParam) throws Exception {
		ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
		if (handlerState == null || ResponsibilityChainHandlerStateEnum.EXCEPTION.equals(handlerState)) {
			return requestParam;
		}

		String payChannelCode = requestParam.getPayChannelCode();
		//银联
		if (PayChannelCode.UNIONPAY.getValue().equals(payChannelCode)) {
			// 查询请求参数
			JSONObject json = new JSONObject();
			// 消息类型
			json.put("msgType", "query");

			json.put("msgSrc", UnionpayMiniConstant.MSGSRC);
			// 商户号
			json.put("mid", requestParam.getPayChannelAccount());
			// 终端号
			json.put("tid", requestParam.getPayChannelKey());
			// 机构商户号
			json.put("instMid", UnionpayMiniConstant.MINI_INST_MID);
			// 商户订单号
			json.put("merOrderId", requestParam.getPayOrderNo());
			//是否要在商户系统下单，看商户需求  createBill()
			json.put("requestTimestamp", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			// 获取签名
			Map<String, String> paramsMap = UnionpayMiniSignUtils.jsonToMap(json);
			paramsMap.put("sign", UnionpayMiniSignUtils.makeSign(UnionpayMiniConstant.MD5KEY, paramsMap));
			LOGGER.info("paramsMap:{}", paramsMap);

			String obj = JSON.toJSONString(paramsMap);
			LOGGER.info("obj:{}", obj);

			UnionpayMiniQueryResult requestResult = unionpayMiniQueryResultRequest.miniQueryResult(obj);
			MiniOrderQueryResult queryResult = new MiniOrderQueryResult();
			PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
			if (requestResult != null) {
				if (StringUtils.equals(UnionpayMiniStateEnum.TRADE_SUCCESS.getState(), requestResult.getStatus())) {
					// 下单成功
					queryResult.setPayOrderNo(requestParam.getPayOrderNo());
					queryResult.setTradeOrderNo(requestResult.getTargetOrderId());
					queryResult.setResultState(OrderPayState.SUCCESS.getValue());
					queryResult.setResultMessage(OrderPayState.SUCCESS.getDescription());

					payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
					payOrderRequestRecord.setTradeOrderNo(requestResult.getTargetOrderId());

					payOrderRequestRecord.setErrCode(requestResult.getErrCode());
					payOrderRequestRecord.setErrMsg(requestResult.getErrMsg());

					PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
						PaymentOrderResponseRecord.class);

					payOrderResponseRecord.setPayChannelAccount(requestParam.getPayChannelAccount());
					bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
				} else if (StringUtils.equals(UnionpayMiniStateEnum.TIMEOUT.getState(), requestResult.getErrCode())) {
					// 处理超时
					queryResult.setResultState(Integer.valueOf(UnionpayStateEnum.QUERYTIMEOUT.getValue()));
					queryResult.setResultMessage(requestResult.getErrMsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
				} else {
					// 查询失败
					LOGGER.info("errCode:{},errMsg:{}", requestResult.getErrCode(), requestResult.getErrMsg());
					queryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
					queryResult.setResultMessage(requestResult.getErrMsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
				}
			}

			requestParam.setMiniOrderQueryResult(queryResult);

		} else if (StringUtils.equals(PayChannelCode.CITICBANK.getValue(), payChannelCode)) {
			//中信银行接口
			CiticBankMiniQueryParam param = new CiticBankMiniQueryParam();
			param.setMchId(requestParam.getPayChannelAccount());
			param.setKey(requestParam.getPayChannelKey());
			param.setOutTradeNo(requestParam.getPayOrderNo());

			CiticBankMiniQueryRequest rq = new CiticBankMiniQueryRequest(param);
			CiticBankMiniQueryResult requestResult = rq.doPostForObject();
			MiniOrderQueryResult queryResult = new MiniOrderQueryResult();
			if (requestResult == null) {
				queryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
				queryResult.setResultMessage(OrderQueryResultCodeEnum.ERROR.getDescription());
				requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
				return requestParam;
			}

			if (StringUtils.equals(CiticBankResultStatusEnum.SUCCESS.getValue(), requestResult.getStatus())) {
				PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
				// 查询成功
				if (StringUtils.equals(CiticBankResultCodeEnum.SUCCESS.getValue(), requestResult.getResultCode())) {
					if (StringUtils.equals(CiticBankTradeStateEnum.SUCCESS.getValue(),
						requestResult.getTradeState())) {
						// 支付成功
						String timeEnd = requestResult.getTimeEnd();
						payOrderRequestRecord.setState(OrderPayState.SUCCESS.getValue());
						payOrderRequestRecord.setPaySuccessTime(LocalDateTime.parse(requestResult.getTimeEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
						payOrderRequestRecord.setTradeOrderNo(requestResult.getTransactionId());

						PaymentOrderResponseRecord payOrderResponseRecord = BeanMapper.map(payOrderRequestRecord,
							PaymentOrderResponseRecord.class);
						payOrderResponseRecord.setBankType(requestResult.getBankType());
						payOrderResponseRecord.setFeeType(requestResult.getFeeType());

						bankTradeService.barcodePaySuccessResult(payOrderRequestRecord, payOrderResponseRecord);
						queryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
						queryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
						queryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
						queryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());

						requestParam.setNotifyFlag(true);

					} else if (StringUtils.equals(CiticBankTradeStateEnum.NOTPAY.getValue(),
						requestResult.getTradeState())) {
						// 未支付
						queryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
						queryResult.setResultMessage(OrderQueryResultCodeEnum.UNPAY.getDescription());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
					} else if (StringUtils.equals(CiticBankTradeStateEnum.CLOSED.getValue(),
						requestResult.getTradeState())) {
						// 已关闭的
						queryResult.setResultState(OrderQueryResultCodeEnum.CLOSE.getValue());
						queryResult.setResultMessage(OrderQueryResultCodeEnum.CLOSE.getDescription());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);

					} else if (StringUtils.equals(CiticBankTradeStateEnum.REFUND.getValue(),
						requestResult.getTradeState())) {
						// 已完全退款，暂不处理
						queryResult.setResultState(OrderQueryResultCodeEnum.REFUND.getValue());
						queryResult.setResultMessage(OrderQueryResultCodeEnum.REFUND.getDescription());
						requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
					}
				} else {
					// 调用接口发生错误
					queryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
					queryResult.setResultMessage(requestResult.getErrMsg());
					requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
				}

			} else {
				// 调用接口发生错误
				queryResult.setResultState(OrderQueryResultCodeEnum.ERROR.getValue());
				queryResult.setResultMessage(requestResult.getMessage());
				requestParam.setHandlerState(ResponsibilityChainHandlerStateEnum.COMPLETE);
			}

			requestParam.setMiniOrderQueryResult(queryResult);
		}


		return requestParam;
	}

}
