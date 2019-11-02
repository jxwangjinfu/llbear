package com.junfeng.platform.tc;

import com.pig4cloud.pig.common.core.util.SnowFlake;
import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * 交易中心
 *
 * @projectName:trade-center-biz
 * @author:fuh
 * @date:2019年9月17日 下午2:09:31
 * @version 1.0
 */
@EnablePigResourceServer
@SpringCloudApplication
@EnablePigFeignClients
public class TradeCenterApplication {
	@Bean
	public SnowFlake snowFlake(){
		return new SnowFlake(1, 1);
	}

	public static void main(String[] args) {
        SpringApplication.run(TradeCenterApplication.class, args);
    }
}
