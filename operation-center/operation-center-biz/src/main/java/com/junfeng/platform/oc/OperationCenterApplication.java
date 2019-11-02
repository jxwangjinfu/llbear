
package com.junfeng.platform.oc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import lombok.extern.slf4j.Slf4j;

/**
 * 运营中心
 *
 * @projectName:operation-center-biz
 * @author:Wangjf
 * @date:2019年9月23日 上午10:22:47
 * @version 1.0
 */
@Slf4j
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class OperationCenterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OperationCenterApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("Spring boot 使用profile为:{}", profile);
        }
    }

}
