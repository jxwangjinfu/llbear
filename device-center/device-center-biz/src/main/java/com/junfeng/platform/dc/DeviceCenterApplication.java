package com.junfeng.platform.dc;


import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 设备中心
 * @projectName:device-center-biz
 * @author:fuh
 * @date:2019年8月27日 下午2:02:09
 * @version 1.0
 */
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class DeviceCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeviceCenterApplication.class, args);
	}

}
