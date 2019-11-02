package com.junfeng.platform.qc.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * quartz日志表
 *
 * @author wangjf
 * @date 2019-09-18 09:09:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("quartz_log")
public class QuartzLog extends Model<QuartzLog> {
private static final long serialVersionUID = 1L;

    /**
   * 编号
   */
    @TableId
    private Long id;
    /**
   * 日志类型
   */
    private String type;
    /**
   * 任务名称
   */
    private String jobName;
    /**
   * 任务组名称
   */
    private String jobGroup;
    /**
   * 执行类名
   */
    private String className;
    /**
   * cron表达式
   */
    private String cronExpression;
    /**
   * 执行日志
   */
    private String executeLog;
    /**
   * 执行状态
   */
    private String executeStatus;
    /**
   * 创建者
   */
    private String createBy;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;
    /**
   * 执行参数
   */
    private String params;
    /**
   * 执行开始时间
   */
    private LocalDateTime startTime;
    /**
   * 执行耗时
   */
    private Integer executeTime;
    /**
   * 删除标记
   */
    private String delFlag;
    /**
   * 异常日志
   */
    private String exceptionLog;
  
}
