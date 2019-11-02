/**
 * 
 */
package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junfeng.platform.qc.QuartzCenterApplication;
import com.junfeng.platform.qc.entity.QuartzEntity;

/**
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 下午1:42:53
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzCenterApplication.class)
public class QuartzTest2 {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Test
    public void test() throws Exception {

        QuartzEntity quartz = new QuartzEntity();

        quartz.setCronExpression("0/5 * * * * ? ");
        quartz.setDescription("测试2");
        quartz.setJobClassName("com.junfeng.platform.qc.job.HiJob");
        quartz.setJobGroup("testGroup02");
        quartz.setJobName("testJob02");
        quartz.setTriggerName("testTriggerName02");
        quartz.setTriggerState("testTriggerState02");

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("param", "test.....param");

        Class cls = Class.forName(quartz.getJobClassName());
        cls.newInstance();
        // 构建job信息
        JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(), quartz.getJobGroup())
                .setJobData(jobDataMap).withDescription(quartz.getDescription()).build();
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger" + quartz.getTriggerName(), quartz.getJobGroup()).startNow()
                .withSchedule(cronScheduleBuilder).build();
        // 交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);

    }

}
