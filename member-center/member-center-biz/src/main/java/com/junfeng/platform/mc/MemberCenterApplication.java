package com.junfeng.platform.mc;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 会员中心
 *
 * @author daiysh
 * @projectName pig
 * @date 2019/9/3 4:47 PM
 **/
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class MemberCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(MemberCenterApplication.class, args);
	}

}
