package com.junfeng.platform.payment.controller.pay;

import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.payment.PaymentCenterApplication;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-12 10:34
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentCenterApplication.class)
public class PaymentControllerTest {

	@Autowired
	RemoteTradeOrderService remoteTradeOrderService;

	@Test
	public void test() {
		R<Order> order = remoteTradeOrderService.remoteGetOrderInfo("36510233661440", SecurityConstants.FROM_IN);
		System.out.println("order = " + order);

	}
}
