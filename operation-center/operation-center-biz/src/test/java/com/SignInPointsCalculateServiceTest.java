package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.oc.OperationCenterApplication;
import com.junfeng.platform.oc.api.vo.SignInVO;
import com.junfeng.platform.oc.service.points.SignInPointsCalculateService;

/**
 * 描述
 *
 * @author wangjf
 * @version 1.0
 * @createDate 2019/10/21 15:17
 * @projectName pig
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OperationCenterApplication.class)
public class SignInPointsCalculateServiceTest {

    @Autowired
    private SignInPointsCalculateService signInPointsCalculateService;

    @Test
    public void test() {

        SignInVO signInVO = new SignInVO();
        signInVO.setCount(1);
        signInVO.setClientId("pig");
        signInVO.setConfigId(1L);
        signInVO.setMemberId(1L);
		Integer integer = signInPointsCalculateService.calculatePoint(signInVO);
		System.out.println("获取积分为："+integer);
		System.out.println("结束");
    }

}
