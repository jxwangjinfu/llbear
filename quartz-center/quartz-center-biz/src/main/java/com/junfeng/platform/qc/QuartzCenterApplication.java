/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.junfeng.platform.qc;

import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @EnablePigResourceServer
 * @EnablePigFeignClients
 * @SpringCloudApplication 定时任务管理系统
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 上午11:08:51
 * @version 1.0
 */
@Slf4j
@EnablePigResourceServer
@SpringCloudApplication
public class QuartzCenterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(QuartzCenterApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("Spring Boot 使用profile为:{}" , profile);
        }

    }
}
