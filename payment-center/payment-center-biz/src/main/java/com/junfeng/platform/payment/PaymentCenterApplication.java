package com.junfeng.platform.payment;

import com.pig4cloud.pig.common.core.util.SnowFlake;
import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * 2fx0one
 * 2019-09-18 15:27
 **/
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class PaymentCenterApplication {

	@Bean
	public SnowFlake snowFlake(){
		return new SnowFlake(1, 1);
	}


	public static void main(String[] args) {
		SpringApplication.run(PaymentCenterApplication.class, args);
	}
}
