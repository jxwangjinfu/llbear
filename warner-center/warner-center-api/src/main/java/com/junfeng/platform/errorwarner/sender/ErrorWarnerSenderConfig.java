package com.junfeng.platform.errorwarner.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.Collections;

/**
 * Rabbit相关配置
 *
 * @author fuh
 * @version 1.0
 * @createDate 2019/10/14 15:47
 */
@Configuration
@EnableRabbit
@EnableCaching
@Slf4j
public class ErrorWarnerSenderConfig {

	@Bean
	public Queue queue() {
		return QueueBuilder.durable(RabbitErrorWarnerSender.PLATFORM_ERRORS)
				.build();
	}

	@Bean
	@ConditionalOnMissingBean(ErrorWarnerSender.class)
	public ErrorWarnerSender errorWarner(RabbitTemplate rabbitTemplate){
		return new RabbitErrorWarnerSender(rabbitTemplate);
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

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheManager cm = RedisCacheManager.builder(connectionFactory)
			.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(86400)).disableCachingNullValues())
			.withInitialCacheConfigurations(Collections.singletonMap("send_errors", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(300))))
			.transactionAware()
			.build();
		return cm;
	}

}
