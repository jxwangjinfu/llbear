package com.junfeng.platform.tc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.payment.api.feign.RemotePaymentService;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.junfeng.platform.tc.service.OrderService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;


/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-10 16:45
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeCenterApplication.class)
public class TradeCenterApplicationTest {

	@Autowired
	RemoteTradeOrderService remoteTradeOrderService;

	@Autowired
	private OrderService orderService;

	@Autowired
	RemotePaymentService remotePaymentService;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Test
	public void run()  {
//		R<List<Order>> r = remoteTradeOrderService.remoteMemberOrderList("1234", "1", SecurityConstants.FROM_IN);
//		R<Order> r = remoteTradeOrderService.remoteMemberOrderCreate(new OrderRequest().setUserId(1234L).setSellerId(1L), SecurityConstants.FROM_IN);
		R<Order> r = remoteTradeOrderService.remoteOrderCancel("1234", "432", SecurityConstants.FROM_IN);
		System.out.println("r.getData() = " + r.getData());
//		remoteTradeOrderService.remoteOrderCancel("1234", "3959918839336960", SecurityConstants.FROM_IN);
//		Order order = orderService.getOne(Wrappers.<Order> lambdaQuery().eq(Order::getOrderNo, "36510233661440"));

//		System.out.println("order = " + order);
//		PaymentOrderVo paymentOrderVo = new PaymentOrderVo()
//			.setMchOrderNo(order.getOrderNo());
//		R<PaymentOrderRequestRecord> r = remotePaymentService.create(paymentOrderVo, SecurityConstants.FROM_IN);
//		Assert.notNull(r, "支付发起失败！");
	}

	@Test
	public void test2() {
//		rabbitTemplate.convertAndSend(RabbitConfig.ORDER_DELAY_EXCHANGE, "abc");
	}

}
