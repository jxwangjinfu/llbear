package com.junfeng.wc;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 微信服务中心
 *
 * @author daiysh
 * @date 2019/9/3 4:47 PM
 **/
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class WeixinCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeixinCenterApplication.class, args);
	}

}
