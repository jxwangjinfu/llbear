package com.junfeng.platform.qc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 外部定时任务
 *
 * @author wangjf
 * @date 2019-09-25 10:49:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("qc_out_quart")
public class OutQuart extends Model<OutQuart> implements Serializable{
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
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
    *回调次数
     */
    private Integer callbackCount;
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

}
