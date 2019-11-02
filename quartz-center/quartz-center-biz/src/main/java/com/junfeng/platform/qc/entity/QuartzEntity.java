/**
 * 
 */
package com.junfeng.platform.qc.entity;

import lombok.Data;

/**
 * @projectName:quartz-center-api
 * @author:Wangjf
 * @date:2019年9月16日 上午11:24:43
 * @version 1.0
 */
@Data
public class QuartzEntity {
    // 任务名称
    private String jobName;
    // 任务分组
    private String jobGroup;
    // 任务描述
    private String description;
    // 执行类
    private String jobClassName;
    // 执行时间表达式
    private String cronExpression;
    // 执行时间
    private String triggerName;
    // 任务状态
    private String triggerState;
    // 任务参数
    private String param;
    // 任务名称 用于修改
    private String oldJobName;
    // 任务分组 用于修改
    private String oldJobGroup;
}
