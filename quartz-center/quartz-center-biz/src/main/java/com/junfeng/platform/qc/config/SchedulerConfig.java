package com.junfeng.platform.qc.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz配置
 * 
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月16日 上午10:34:24
 * @version 1.0
 */
@Configuration
@Order(100)
public class SchedulerConfig {

    @Autowired
    private SpringJobFactory springJobFactory;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private QuartzProperties quartzProperties;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setDataSource(dataSource);
        // quartz参数
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", quartzProperties.getSchedulerInstanceName());
        prop.put("org.quartz.scheduler.instanceId", quartzProperties.getSchedulerInstanceId());
        // 线程池配置
        prop.put("org.quartz.threadPool.class", quartzProperties.getThreadPoolClass());
        prop.put("org.quartz.threadPool.threadCount", quartzProperties.getThreadPoolThreadCount());
        prop.put("org.quartz.threadPool.threadPriority", quartzProperties.getThreadPoolThreadPriority());
        // JobStore配置
        prop.put("org.quartz.jobStore.class", quartzProperties.getJobStoreClass());
        // 集群配置
        prop.put("org.quartz.jobStore.isClustered", quartzProperties.getJobStoreIsClustered());
        prop.put("org.quartz.jobStore.clusterCheckinInterval", quartzProperties.getJobStoreClusterCheckinInterval());
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime",
                quartzProperties.getJobStoreMaxMisfiresToHandleAtATime());
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable",
                quartzProperties.getJobStoreTxIsolationLevelSerializable());

        prop.put("org.quartz.jobStore.misfireThreshold", quartzProperties.getJobStoreMisfireThreshold());
        prop.put("org.quartz.jobStore.tablePrefix", quartzProperties.getJobStoreTablePrefix());

        factory.setQuartzProperties(prop);
        factory.setSchedulerName("quartzCenterScheduler");
        factory.setJobFactory(springJobFactory);
        // 延时启动
        factory.setStartupDelay(10);
        factory.setOverwriteExistingJobs(true);
        return factory;
    }

    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean(applicationContext.getBean(DataSource.class)).getScheduler();
    }

}
