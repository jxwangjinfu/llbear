package com.junfeng.platform.qc.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 对外暴露VO
 *
 * @projectName:quartz-center-api
 * @author:Wangjf
 * @date:2019年9月25日 上午11:11:11
 * @version 1.0
 */
@Data
public class OutQuartVO implements Serializable {
    private static final long serialVersionUID = 8426005966532138286L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 外部系统名称
     */
    private String outSysName;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组
     */
    private String jobGroup;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 执行类名称
     */
    private String jobClassName;
    /**
     * 触发规则
     */
    private String cronExpression;
    /**
     * 触发参数
     */
    private String param;
    /**
     * 回调地址
     */
    private String callbackUrl;
    /**
     * 0-未触发，1-已触发
     */
    private String state;
    /**
     * 0-正常，1-删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 最后变更时间
     */
    private LocalDateTime updateTime;
    /**
     * 最后更新人员
     */
    private String updateBy;
	private String oldJobName;
	private String oldJobGroup;
}
