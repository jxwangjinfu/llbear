package com.junfeng.platform.errorwarner.config;

import com.junfeng.platform.errorwarner.dingtalk.DingTalkService;
import com.junfeng.platform.errorwarner.listener.ErrorListeners;
import com.junfeng.platform.errorwarner.mail.MailService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Rabbit相关配置
 *
 * @author fuh
 * @version 1.0
 * @createDate 2019/10/14 15:47
 */
@Configuration
@EnableRabbit
@Slf4j
public class ErrorWarnerConfig {

	private AtomicInteger count = new AtomicInteger(0);

	@Bean
	public Queue queue() {
		return QueueBuilder.durable("platform.errors")
				.build();
	}

	@Bean
	@ConditionalOnMissingBean(ErrorListeners.class)
	public ErrorListeners errorListeners(MailService mailService,DingTalkService dingTalkService){
		return new ErrorListeners(mailService,dingTalkService);
	}

	@Bean
	public MailService mailService(){
		return new MailService();
	}

	@Bean
	public DingTalkService dingTalkService(){return new DingTalkService();}

	/**
	 * 接收json转换支持
	 * @author: fuh
	 * @createTime: 2019/10/14 16:56
	 */
	@Bean
	@ConditionalOnMissingBean(SimpleRabbitListenerContainerFactory.class)
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		factory.setPrefetchCount(1);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		return factory;
	}

	/**
	 * 发送json转换支持
	 * @author: fuh
	 * @createTime: 2019/10/14 16:56
	 */
	@Bean
	@ConditionalOnMissingBean(RabbitTemplate.class)
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}


	/**
	 * 异常处理器
	 * @author: fuh
	 * @createTime: 2019/10/15 10:41
	 */
	@Bean
	@ConditionalOnMissingBean(RabbitListenerErrorHandler.class)
	public RabbitListenerErrorHandler errorHandler() {

		return (amqpMessage, message,exception)->{
			if(count.incrementAndGet()<3) {
				message.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class)
						.basicReject(message.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class),
								true);
				return null;
			}else{
				count.set(0);
				message.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class)
						.basicReject(message.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class),
								false);
				return null;
			}
		};
	}


}
