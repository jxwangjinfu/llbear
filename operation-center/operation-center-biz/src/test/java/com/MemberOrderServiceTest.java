package com;

import com.junfeng.platform.oc.api.result.OrderResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.oc.api.vo.OrderVO;
import com.junfeng.platform.oc.service.MemberOrderService;

/**
 * 订单优惠测试
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/17 10:23
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class MemberOrderServiceTest {

	@Autowired
	private MemberOrderService memberOrderService;
	@Test
	public void test1(){

		OrderVO orderVO = new OrderVO();
		orderVO.setTotalPrice(130L);
		orderVO.setClientId("pig");
		orderVO.setMemberId(1L);
		System.out.println();
		OrderResult orderResult = memberOrderService.orderPreview(orderVO);
		System.out.println(orderResult);

	}

	@Test
	public void test2(){
		OrderVO orderVO = new OrderVO();
		orderVO.setTotalPrice(130L);
		orderVO.setClientId("pig");
		orderVO.setMemberId(1L);
		orderVO.setOrderNo("2019101711111111111");
		OrderResult orderResult = memberOrderService.orderCalculate(orderVO);
		System.out.println(orderResult);

	}

}
