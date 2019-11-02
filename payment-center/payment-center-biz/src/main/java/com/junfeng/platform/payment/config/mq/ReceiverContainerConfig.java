package com.junfeng.platform.payment.config.mq;

import com.junfeng.platform.payment.service.notify.NotifyHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <pre>
 * 消息队列消费监听配置类
 * 注：
 * 为了防止循环调用，把该配置与RabbitConfig配置类分开
 * </pre>
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月13日 下午5:18:27
 * @version 1.0
 */
@Configuration
public class ReceiverContainerConfig {
//    @Autowired
//    private CachingConnectionFactory connectionFactory;
	@Autowired
	private Queue notifyQueue;
	@Autowired
	private MessageConverter messageConverter;
	@Autowired
	private NotifyHandler notifyHandler;
	/**
	 * 创建监听容器存放消费者
	 *
	 * @return
	 */
	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setConnectionFactory(connectionFactory);
//		container.setConcurrentConsumers(3);
//		container.setMaxConcurrentConsumers(3);
		// 手动确认消费
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

		// 监听队列
		container.setQueues(notifyQueue);
		// 队列消费者
		container.setMessageListener(notifyHandler);
		container.setMessageConverter(messageConverter);
		// 在spring容器初始化时不启动，放在应用启动类中去启动
		container.setAutoStartup(false);
		return container;
	}
}
