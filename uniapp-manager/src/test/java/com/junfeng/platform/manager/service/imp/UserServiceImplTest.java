package com.junfeng.platform.manager.service.imp;

import com.junfeng.platform.manager.UniappManagerApplication;
import com.junfeng.platform.manager.service.UserService;
import com.junfeng.platform.mc.api.vo.MemberVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniappManagerApplication.class)
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Test
	public void handleRegister() {
		MemberVo memberVo = new MemberVo();
		memberVo.setUserName("test");
		memberVo.setNickName("daiTest");
		memberVo.setPassword("123456");

		userService.handleRegister(memberVo);
	}

	@Test
	public void login() {
	}

	@Test
	public void bindMobile() {
	}
}
