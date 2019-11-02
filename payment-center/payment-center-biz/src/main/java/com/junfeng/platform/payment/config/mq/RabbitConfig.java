package com.junfeng.platform.payment.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置类
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月13日 下午5:17:52
 * @version 1.0
 */
@Configuration
public class RabbitConfig {

//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//
//    /**
//     * 重新声明模板
//     *
//     * @return
//     * @author:chenyx
//     * @createTime:2018年8月13日 下午5:07:12
//     */
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        // rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }

    /**
     * 通知回调到应用处理队列
     *
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午4:57:32
     */
    @Bean
    public Queue notifyQueue() {
        return QueueBuilder.durable(RabbitConst.NOTIFY_QUEUE).build();
    }

    /**
     * 延迟通知重试处理队列 按每个消息做迟延处理, 延迟消息超时后，自动转发到通知回调处理交换机，routingKey为ROUTING_KEY_NOTIFY
     *
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午4:57:50
     */
    @Bean
    public Queue delayNotifyQueue() {
        return QueueBuilder.durable(RabbitConst.DELAY_NOTIFY_QUEUE)
                .withArgument("x-dead-letter-exchange", RabbitConst.NOTIFY_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", RabbitConst.ROUTING_KEY_NOTIFY).build();
    }

    /**
     * 通知应用回调的交换机
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午5:12:50
     */
    @Bean
    public DirectExchange notifyExchange() {
        return new DirectExchange(RabbitConst.NOTIFY_EXCHANGE);
    }

    /**
     * 延迟重试通知应用回调的交换机
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午5:12:41
     */
    @Bean
    public DirectExchange delayNotifyExchange() {
        return new DirectExchange(RabbitConst.DELAY_NOTIFY_EXCHANGE);
    }

    /**
     * 绑定通知队列到通知交换机，关键字为ROUTING_KEY_NOTIFY
     * @param notifyQueue
     * @param notifyExchange
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午5:12:17
     */
    @Bean
    public Binding notifyBinding(Queue notifyQueue, DirectExchange notifyExchange) {
        return BindingBuilder.bind(notifyQueue).to(notifyExchange).with(RabbitConst.ROUTING_KEY_NOTIFY);
    }

    /**
     * 绑定延迟重试队列到延迟交换机,路由关键字是ROUTING_KEY_DELAY_NOTIFY
     * @param delayNotifyQueue
     * @param delayNotifyExchange
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午5:12:08
     */
    @Bean
    public Binding delayNotifyBinding(Queue delayNotifyQueue, DirectExchange delayNotifyExchange) {
        return BindingBuilder.bind(delayNotifyQueue).to(delayNotifyExchange).with(RabbitConst.ROUTING_KEY_DELAY_NOTIFY);
    }


    /**
     * 设置消息转换格式为json
     * @return
     * @author:chenyx
     * @createTime:2018年8月13日 下午5:13:06
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
