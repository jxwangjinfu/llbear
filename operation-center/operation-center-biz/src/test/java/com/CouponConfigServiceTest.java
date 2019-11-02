package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.oc.entity.CouponConfig;
import com.junfeng.platform.oc.service.CouponConfigService;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/29 15:31
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class CouponConfigServiceTest {

	@Autowired
	private CouponConfigService couponConfigService;

	@Test
	public void test(){

		CouponConfig couponConfig = new  CouponConfig();
		couponConfig.setCouponUseRule(1);
		couponConfigService.save(couponConfig);

	}
}
