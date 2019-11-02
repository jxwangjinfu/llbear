package com.junfeng.platform.payment.service.responsibilitychain.queryorder;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.config.mq.NotifyMessage;
import com.junfeng.platform.payment.config.mq.RabbitConst;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.responsibilitychain.queryorder.model.QueryOrderHandleParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * 银行查询订单,成功回调
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月21日 上午9:47:57
 */
@Service
public class QueryOrderNotifyHandler extends AbstractHandler<QueryOrderHandleParam> {
	private static final Logger LOGGER = LogManager.getLogger();
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public QueryOrderHandleParam handleRequest(QueryOrderHandleParam requestParams) {

		ResponsibilityChainHandlerStateEnum handlerState = requestParams.getHandlerState();
		if (handlerState == null || handlerState.equals(ResponsibilityChainHandlerStateEnum.EXCEPTION)) {
			return requestParams;
		}
		if (handlerState.equals(ResponsibilityChainHandlerStateEnum.SUCCESS) && requestParams.getNotifyFlag()) {

			PaymentOrderRequestRecord payOrderRequestRecord = requestParams.getPayOrderRequestRecord();
			NotifyMessage notifyMessage = new NotifyMessage();
			notifyMessage.setAmount(payOrderRequestRecord.getAmount());
			notifyMessage.setFailCount(0);
			notifyMessage.setMchOrderNo(payOrderRequestRecord.getMchOrderNo());
			notifyMessage.setPayMchId(payOrderRequestRecord.getPayMchId());
			notifyMessage.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			notifyMessage.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
			notifyMessage.setPayChannelCode(payOrderRequestRecord.getPayChannelCode());
			notifyMessage.setPaymentModeCode(payOrderRequestRecord.getPaymentModeCode());
			notifyMessage.setState(payOrderRequestRecord.getState());
			if (payOrderRequestRecord.getPaySuccessTime() != null) {
				notifyMessage.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
				ZonedDateTime zdt = payOrderRequestRecord.getPaySuccessTime().atZone(ZoneId.systemDefault());
				notifyMessage.setPaySuccessTimestamp(Date.from(zdt.toInstant()).getTime());
			}
			notifyMessage.setNotifyUrl(payOrderRequestRecord.getNotifyUrl());
			notifyMessage.setMsgId(UUID.randomUUID().toString());
			CorrelationData correlationData = new CorrelationData(notifyMessage.getMsgId());
			LOGGER.info("notifyMessage={}", notifyMessage.toString());
			MessageProperties messageProperties = new MessageProperties();
			String json = JSON.toJSONString(notifyMessage);
			messageProperties.setContentType(RabbitConst.MESSAGE_PROPERTIES_CONTENTTYPE);
			Message newMessage = new Message(json.getBytes(), messageProperties);
			rabbitTemplate.convertAndSend(RabbitConst.NOTIFY_EXCHANGE, RabbitConst.ROUTING_KEY_NOTIFY,
				newMessage, correlationData);

		}
		return requestParams;
	}

}
