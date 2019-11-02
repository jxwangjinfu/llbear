package com.junfeng.platform.tc.config.rabbit;

import com.junfeng.platform.tc.api.message.GroupOrderMessage;
import com.junfeng.platform.tc.service.GroupOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-28 11:33
 * @projectName pig
 * 团购订单，团长开团之后，在一定的时间取消。每条消息自己独立设置
 */
@Component
@Slf4j
public class GroupOrderDelayProcess {

	@Autowired
	private GroupOrderService groupOrderService;

	// 队列统一过期时间。
	// 第一步. 每条消息有自己的TTL，消息最初产生, 是投递在这个队列中的。只投递，不消费该消息。
	// 这个队列使用默认的交换机绑定即可
	public static final  String GROUP_ORDER_DELAY_QUEUE_PRE_MESSAGE = "trade.group.order.delay.queue";

	// 第二步. 消息一旦到期，转发到该DLX，由routing_key 派发到指定的处理队列 等待消费！
	public static final  String GROUP_ORDER_DEAD_LETTER_PROCESS_EXCHANGE = "trade.group.order.process.exchange";
	public static final  String GROUP_ORDER_DEAD_LETTER_PROCESS_ROUTING_KEY = "trade.group.order.process.routing.key";
	public static final  String GROUP_ORDER_DEAD_LETTER_PROCESS_QUEUE = "trade.group.order.process.queue";

	@Bean
	public org.springframework.amqp.core.Queue groupOrderDelayQueuePerMessageTTL() {
		//该队列绑定在默认交换机上。投递上来的消息。会带一个过期参数。过期后，会投递到对应的交换机上。
		return QueueBuilder.durable(GROUP_ORDER_DELAY_QUEUE_PRE_MESSAGE)
			// DLX，dead letter发送到的exchange
			.withArgument("x-dead-letter-exchange", GROUP_ORDER_DEAD_LETTER_PROCESS_EXCHANGE)
			// dead letter携带的routing key
			.withArgument("x-dead-letter-routing-key", GROUP_ORDER_DEAD_LETTER_PROCESS_ROUTING_KEY)
			.build();
	}


	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(GROUP_ORDER_DEAD_LETTER_PROCESS_QUEUE),
		//消息到期后, 投递到该交换机。并路由到指定队列
		exchange = @Exchange(GROUP_ORDER_DEAD_LETTER_PROCESS_EXCHANGE),
		key = GROUP_ORDER_DEAD_LETTER_PROCESS_ROUTING_KEY
	))
	public void grouOrderDelayProcess(GroupOrderMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
		log.info("===== 团订单 延迟队列 " + message.toString());

		if (message.getGroupOrderNo()!=null) {
			groupOrderService.checkLeaderGroupOrderTimeout(message.getGroupOrderNo());
		}

		channel.basicAck(deliveryTag, false);
	}
}
