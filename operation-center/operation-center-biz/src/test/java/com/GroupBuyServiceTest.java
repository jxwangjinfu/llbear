package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.oc.service.GroupBuyService;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/22 14:44
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class GroupBuyServiceTest {

	@Autowired
	private GroupBuyService groupBuyService;

	@Test
	public void test(){

		Boolean aBoolean = groupBuyService.updateState(1L, 1);
		System.out.println(aBoolean);

	}

}
