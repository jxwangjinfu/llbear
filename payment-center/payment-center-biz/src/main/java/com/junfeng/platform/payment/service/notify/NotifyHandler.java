package com.junfeng.platform.payment.service.notify;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.common.type.BooleanToIntEnum;
import com.junfeng.platform.payment.config.mq.NotifyFailTTLEnum;
import com.junfeng.platform.payment.config.mq.NotifyMessage;
import com.junfeng.platform.payment.config.mq.RabbitConst;
import com.junfeng.platform.payment.api.entity.PaymentNotifyRecord;
import com.junfeng.platform.payment.service.PaymentNotifyRecordService;
import com.junfeng.platform.payment.uitls.notify.model.PaymentCenterNotifyPaySuccess;
import com.junfeng.platform.payment.uitls.notify.request.PaymentCenterNotifyPaySuccessRequest;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 回调通知业务系统处理
 *
 * @version 1.0
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月13日 下午5:30:30
 */
@Component
public class NotifyHandler implements ChannelAwareMessageListener {
	private final static Logger LOGGER = LogManager.getLogger();

	/**
	 * 回调业务系统成功返回定义
	 */
	private final static String NOTIFY_SUCCESS = "SUCCESS";
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private PaymentNotifyRecordService payNotifyRecordService;

	RedissonClient client;

	/**
	 * 收到队列推送消息处理
	 *
	 * @param message
	 * @param channel
	 * @throws Exception
	 * @author:chenyx
	 * @createTime:2018年8月13日 下午6:25:25
	 */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		LOGGER.info("message={}", message);
		String realMessage = new String(message.getBody());
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		String correlationId = message.getMessageProperties().getCorrelationId();
		LOGGER.info("message correlationId={},deliveryTag={},body={}", correlationId, deliveryTag, realMessage);
		try {

			// 转回对象

			NotifyMessage notifyMessage = JSON.parseObject(realMessage, NotifyMessage.class);
			// 只有支付成功才回调
			if (BooleanToIntEnum.TRUE.getValue().equals(notifyMessage.getState())) {
				boolean sendSuccess = false;

				PaymentCenterNotifyPaySuccess paySuccessMessage =
					new PaymentCenterNotifyPaySuccess(
						notifyMessage.getPaySuccessTimestamp(),
						"",
						notifyMessage.getPayMchId().toString(),
						notifyMessage.getMchOrderNo(),
						notifyMessage.getTradeOrderNo(),
						notifyMessage.getPayOrderNo(),
						notifyMessage.getPaySuccessTimestamp(),
						"",
						notifyMessage.getState().toString()
					);
				// 回调业务系统
				PaymentCenterNotifyPaySuccessRequest rq = new PaymentCenterNotifyPaySuccessRequest(
					notifyMessage.getNotifyUrl(), paySuccessMessage);
				String notifyResult = rq.doRequest();
				if (StringUtils.equals(notifyResult, NOTIFY_SUCCESS)) {
					sendSuccess = true;
				}

				int count = payNotifyRecordService.count(Wrappers.<PaymentNotifyRecord>lambdaQuery()
					.eq(PaymentNotifyRecord::getPayOrderNo, notifyMessage.getPayOrderNo()));
				if (count == 0) {
					// 判断通知记录是否存在，不存在则新增一条
					PaymentNotifyRecord payNotifyRecord = new PaymentNotifyRecord();
					payNotifyRecord.setAmount(notifyMessage.getAmount());
					payNotifyRecord.setMchOrderNo(notifyMessage.getMchOrderNo());
					payNotifyRecord.setNotifyUrl(notifyMessage.getNotifyUrl());
					payNotifyRecord.setPayChannelCode(notifyMessage.getPayChannelCode());
					payNotifyRecord.setPayMchId(notifyMessage.getPayMchId());
					payNotifyRecord.setPaymentModeCode(notifyMessage.getPaymentModeCode());
					payNotifyRecord.setPayOrderNo(notifyMessage.getPayOrderNo());
					payNotifyRecord.setTradeOrderNo(notifyMessage.getTradeOrderNo());
					payNotifyRecordService.save(payNotifyRecord);
				}
				PaymentNotifyRecord record = payNotifyRecordService.getOne(Wrappers.<PaymentNotifyRecord>lambdaQuery()
					.eq(PaymentNotifyRecord::getPayOrderNo, notifyMessage.getPayOrderNo()));
				if (sendSuccess) {
					// 成功后，通知队列处理成功,改数据库处理状态
					payNotifyRecordService.update(Wrappers.<PaymentNotifyRecord>lambdaUpdate()
						.set(PaymentNotifyRecord::getState,  BooleanToIntEnum.TRUE.getValue())
						.set(PaymentNotifyRecord::getNotifySuccessTime, LocalDateTime.now())
						.set(PaymentNotifyRecord::getLastNotifyTime, record.getNotifySuccessTime())
						.eq(PaymentNotifyRecord::getId, record.getId())
					);
				} else {
					// 失败后
					// 改数据库处理失败次数
					// 判断失败次数，如果小于设定限制，修改消息延迟时间，写入延迟队列

					notifyMessage.setFailCount(notifyMessage.getFailCount() + 1);
					if (notifyMessage.getFailCount() < NotifyFailTTLEnum.FAIL_COUNT_LIMIT.getFailCount()) {
						// 失败未超过次数限制
						MessageProperties messageProperties = new MessageProperties();
						//String ttl = getTTLTime(notifyMessage.getFailCount());
						String ttl = NotifyFailTTLEnum.FAIL_NOTIFY_TTL.getTtl();
						String json = JSON.toJSONString(notifyMessage);
						messageProperties.setExpiration(ttl);
						messageProperties.setContentType(RabbitConst.MESSAGE_PROPERTIES_CONTENTTYPE);
						Message newMessage = new Message(json.getBytes(), messageProperties);
						rabbitTemplate.convertAndSend(RabbitConst.DELAY_NOTIFY_EXCHANGE,
							RabbitConst.ROUTING_KEY_DELAY_NOTIFY, newMessage);
					}
					payNotifyRecordService.update(Wrappers.<PaymentNotifyRecord>lambdaUpdate()
						.eq(PaymentNotifyRecord::getId, record.getId())
						.set(PaymentNotifyRecord::getNotifyCount, notifyMessage.getFailCount())
						.set(PaymentNotifyRecord::getState, BooleanToIntEnum.FALSE.getValue())
						.set(PaymentNotifyRecord::getLastNotifyTime, LocalDateTime.now())
					);
				}

			}
		} finally {
			// 防止异常导致的阻塞
			channel.basicAck(deliveryTag, false);
		}
	}

	/**
	 * 根据失败次数获取延迟时间
	 *
	 * @param failCount
	 * @return
	 * @author:chenyx
	 * @createTime:2018年8月14日 下午2:33:59
	 */
	private String getTTLTime(Integer failCount) {
		for (NotifyFailTTLEnum value : NotifyFailTTLEnum.values()) {
			if (failCount.equals(value.getFailCount())) {
				return value.getTtl();
			}
		}

		return NotifyFailTTLEnum.FAIL_COUNT_LIMIT.getTtl();
	}
}
