package com;

import com.junfeng.platform.dc.DeviceCenterApplication;
import com.junfeng.platform.dc.entity.Command;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceCenterApplication.class)
@Slf4j
public class TestMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {

		Command command = new Command();
		command.setType("1");
		command.setName("aaa");
        rabbitTemplate.convertAndSend("fuh",
                "fuh.*", command);
        log.info("消息发送完成{}", command);

    }

	@Test
	public void testError() {

		Command command = new Command();
		command.setType("1");
		command.setName("aaa");
		rabbitTemplate.convertAndSend(
			"test_error", command);
		log.info("消息发送完成{}", command);

	}

	@Test
	public void testTopic() {

		Command command = new Command();
		command.setType("1");
		command.setName("a1");
		rabbitTemplate.convertAndSend("junfeng",
			"junfeng.jskfb.wangjf", command);

		Command command2 = new Command();
		command2.setType("1");
		command2.setName("a2");
		rabbitTemplate.convertAndSend("junfeng",
			"junfeng.jskfb.fuh", command2);

	}
}
