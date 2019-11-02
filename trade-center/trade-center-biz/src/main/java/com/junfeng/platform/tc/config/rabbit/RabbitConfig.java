package com.junfeng.platform.tc.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-23 15:22
 * @projectName pig
 */
@Configuration
@EnableRabbit
@Slf4j
public class RabbitConfig {

	//只需要配置消息转换器即可
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}



}
