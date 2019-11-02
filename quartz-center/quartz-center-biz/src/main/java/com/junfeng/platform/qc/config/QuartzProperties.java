package com.junfeng.platform.qc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Quartz 配置文件
 * @projectName:quartz-center-biz
 * @author:Wangjf
 * @date:2019年9月18日 下午2:04:51
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "org.quartz")
@Data
public class QuartzProperties {
    private String schedulerInstanceName;
    private String schedulerInstanceId;
    private String threadPoolClass;
    private String threadPoolThreadCount;
    private String threadPoolThreadPriority;
    private String jobStoreClass;
    private String jobStoreIsClustered;
    private String jobStoreClusterCheckinInterval;
    private String jobStoreMaxMisfiresToHandleAtATime;
    private String jobStoreTxIsolationLevelSerializable;
    private String jobStoreMisfireThreshold;
    private String jobStoreTablePrefix;
    

}
