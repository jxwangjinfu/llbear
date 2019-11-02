package com;

import com.pig4cloud.pig.admin.PigAdminApplication;
import com.pig4cloud.pig.admin.rabbit.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PigAdminApplication.class)
@Slf4j
public class PigAdminApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        Message message = new Message(11,"15179011111");
		message.setUserId(10);
		message.setMobile("00000000000");
        rabbitTemplate.convertAndSend("pig",
                "phone.*", message);
        log.info("消息发送完成{}", message);

    }
}
