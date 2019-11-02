package com.junfeng.platform.oc.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq消息队列配置类
 *
 * @author:Wangjf
 * @date:2019年09月29日 下午3:49:10
 * @version 1.0
 */
@EnableRabbit
@Configuration
public class RabbitConfig {

    // 优惠券 start
    @Bean
    public Queue couponGenerateNotifyQueue() {
        return QueueBuilder.durable(RabbitConst.COUPON_GENERATE_NOTIFY_QUEUE).build();
    }

    @Bean
    public DirectExchange couponGenerateNotifyExchange() {
        return new DirectExchange(RabbitConst.COUPON_GENERATE_NOTIFY_EXCHANGE);
    }

    @Bean
    public Binding couponNotifyBinding(Queue couponGenerateNotifyQueue, DirectExchange couponGenerateNotifyExchange) {
        return BindingBuilder.bind(couponGenerateNotifyQueue).to(couponGenerateNotifyExchange)
                .with(RabbitConst.COUPON_GENERATE_ROUTING_KEY_NOTIFY);
    }
    // 优惠券 end

    // 红包 start
    @Bean
    public Queue redEnvelopeGenerateNotifyQueue() {
        return QueueBuilder.durable(RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_QUEUE).build();
    }

    @Bean
    public DirectExchange redEnvelopeGenerateNotifyExchange() {
        return new DirectExchange(RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_EXCHANGE);
    }

    @Bean
    public Binding redEnvelopeNotifyBinding(Queue redEnvelopeGenerateNotifyQueue,
            DirectExchange redEnvelopeGenerateNotifyExchange) {
        return BindingBuilder.bind(redEnvelopeGenerateNotifyQueue).to(redEnvelopeGenerateNotifyExchange)
                .with(RabbitConst.RED_ENVELOPE_GENERATE_ROUTING_KEY_NOTIFY);
    }
    // 红包 end

	// 礼品 start
	@Bean
	public Queue giftGenerateNotifyQueue() {
		return QueueBuilder.durable(RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_QUEUE).build();
	}

	@Bean
	public DirectExchange giftGenerateNotifyExchange() {
		return new DirectExchange(RabbitConst.RED_ENVELOPE_GENERATE_NOTIFY_EXCHANGE);
	}

	@Bean
	public Binding giftNotifyBinding(Queue redEnvelopeGenerateNotifyQueue,
											DirectExchange redEnvelopeGenerateNotifyExchange) {
		return BindingBuilder.bind(redEnvelopeGenerateNotifyQueue).to(redEnvelopeGenerateNotifyExchange)
			.with(RabbitConst.RED_ENVELOPE_GENERATE_ROUTING_KEY_NOTIFY);
	}
	// 礼品 end

    /**
     * 接收消息转换
     *
     * @author: wangjf
     * @createTime: 2019/10/23 14:33
     * @param connectionFactory
     * @return org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setPrefetchCount(1);
        factory.setAutoStartup(true);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    /**
     * 发送消息转换
     * @author: wangjf
     * @createTime: 2019/10/23 14:34
     * @param connectionFactory
     * @return org.springframework.amqp.rabbit.core.RabbitTemplate
     */
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}
}
