package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.oc.api.vo.CouponGenerateMessageVO;
import com.junfeng.platform.oc.api.vo.RedEnvelopeGenerateMessageVO;
import com.junfeng.platform.oc.config.mq.RabbitConst;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
@Slf4j
public class TestMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 功能描述:
     *
     * @author: wangjf
     * @createTime: 2019/9/30 10:58
     * @param
     * @return java.lang.String
     */
    @Test
    public void test() {

        CouponGenerateMessageVO vo = new CouponGenerateMessageVO();
        vo.setCreateBy("wangjf");
        vo.setOcCouponId(2L);
        vo.setStartNo(1);
        vo.setEndNo(1000);
        rabbitTemplate.convertAndSend(RabbitConst.COUPON_GENERATE_NOTIFY_EXCHANGE,
                RabbitConst.COUPON_GENERATE_ROUTING_KEY_NOTIFY, vo);
        log.info("消息发送完成{}", vo);

    }

    @Test
    public void test1() {

        RedEnvelopeGenerateMessageVO vo = new RedEnvelopeGenerateMessageVO();
        vo.setCreateBy("wangjf");
        vo.setMoney(100);
        vo.setOcRedEnvelopeId(1L);
        vo.setStartNo(1);
        vo.setEndNo(1000);
        rabbitTemplate.convertAndSend(RabbitConst.COUPON_GENERATE_NOTIFY_EXCHANGE,
                RabbitConst.COUPON_GENERATE_ROUTING_KEY_NOTIFY, vo);
        log.info("消息发送完成{}", vo);

    }
}
