package com.junfeng.platform.payment.service.responsibilitychain.barcodepay;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.config.mq.NotifyMessage;
import com.junfeng.platform.payment.config.mq.RabbitConst;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model.BarcodePayHandleParams;
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
 * 条码支付成功 回调业务系统
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月23日 上午11:28:05
 * @version 1.0
 */
@Service
public class BarcodePayNotifyHandler extends AbstractHandler<BarcodePayHandleParams> {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public BarcodePayHandleParams handleRequest(BarcodePayHandleParams requestParam) throws Exception {

        ResponsibilityChainHandlerStateEnum handlerState = requestParam.getHandlerState();
        if (handlerState == null || !ResponsibilityChainHandlerStateEnum.SUCCESS.equals(handlerState)) {
            return requestParam;
        }
        if (handlerState.equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {

            PaymentOrderRequestRecord payOrderRequestRecord = requestParam.getPayOrderRequestRecord();
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
            LOGGER.info("notifyMessage={}",notifyMessage.toString());
            MessageProperties messageProperties = new MessageProperties();
            String json = JSON.toJSONString(notifyMessage);
            LOGGER.info("notifyMessage json={}",json);
            messageProperties.setContentType(RabbitConst.MESSAGE_PROPERTIES_CONTENTTYPE);
            Message newMessage = new Message(json.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(RabbitConst.NOTIFY_EXCHANGE, RabbitConst.ROUTING_KEY_NOTIFY,
                    newMessage,correlationData);

        }
        return requestParam;
    }

}
