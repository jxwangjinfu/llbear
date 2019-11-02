package com.junfeng.platform.manager;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * uniapp后台管理系统
 *
 * @author daiysh
 * @date 2019/9/3 4:47 PM
 */
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class UniappManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(UniappManagerApplication.class, args);
	}
}
