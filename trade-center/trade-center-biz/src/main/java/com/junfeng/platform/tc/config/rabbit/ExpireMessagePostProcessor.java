package com.junfeng.platform.tc.config.rabbit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-28 13:47
 * @projectName pig
 */
public class ExpireMessagePostProcessor implements MessagePostProcessor {

	// 毫秒
	private final Long ttl;

	public ExpireMessagePostProcessor(Long ttl) {
		this.ttl = ttl;
	}

	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		message.getMessageProperties()
			// 设置per-message的失效时间
			.setExpiration(ttl.toString());
		return message;
	}
}
