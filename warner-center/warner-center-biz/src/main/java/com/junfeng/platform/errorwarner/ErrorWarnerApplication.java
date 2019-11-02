package com.junfeng.platform.errorwarner;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author fuh
 * @version 1.0 2019/10/23 13:46
 */
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class ErrorWarnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorWarnerApplication.class);
	}
}
