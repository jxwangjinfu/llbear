package com.junfeng.platform.errorwarner.listener;

import com.junfeng.platform.errorwarner.ErrorVo;
import com.junfeng.platform.errorwarner.NotifyType;
import com.junfeng.platform.errorwarner.dingtalk.DingTalkService;
import com.junfeng.platform.errorwarner.mail.MailService;
import com.junfeng.platform.errorwarner.mail.MailVo;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

/**
 * 描述
 *
 * @author fuh
 * @version 1.0
 * @createDate 2019/10/15 10:40
 * @projectName demo
 */
@Slf4j
@AllArgsConstructor
public class ErrorListeners {

	private MailService mailService;
	private DingTalkService dingTalkService;


    @RabbitListener(queues = "platform.errors")
    public void test(ErrorVo errorVo, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {

    	if(errorVo.getNotifyType() == null || errorVo.getNotifyType().equals(NotifyType.DINGTALK)) {
			try {
				dingTalkService.sendMessage(errorVo);
			} catch (Exception e) {
				log.error("钉钉机器人调用失败",e);
			}
		}else if(errorVo.getNotifyType().equals(NotifyType.MAIL)){
			MailVo mailVo = new MailVo();
			mailVo.setSubject(errorVo.getCode());
			mailVo.setText(errorVo.getMessage());
			mailVo.setTo(errorVo.getSendMailTo());
			mailService.sendMail(mailVo);
		}

        channel.basicAck(deliveryTag,true);
    }



}
