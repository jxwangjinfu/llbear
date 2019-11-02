package com.junfeng.platform.errorwarner.sender;

import com.junfeng.platform.errorwarner.ErrorVo;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.Cacheable;

/**
 * 预警发送到
 * @author fuh
 * @version 1.0 2019/10/22 15:17
 */
@AllArgsConstructor
public class RabbitErrorWarnerSender implements ErrorWarnerSender {

	public static final String PLATFORM_ERRORS = "platform.errors";

	private RabbitTemplate rabbitTemplate;

	@Override
	@Cacheable(cacheNames = "send_errors",key = "#errorVo.code +'@'+ #errorVo.serviceName")
	public void send(ErrorVo errorVo){

		rabbitTemplate.convertAndSend(PLATFORM_ERRORS,errorVo);

	}
}
