package com.junfeng.device.manager;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/24 10:54
 * @projectName pig
 */
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class DeviceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceManagerApplication.class, args);
	}

}
