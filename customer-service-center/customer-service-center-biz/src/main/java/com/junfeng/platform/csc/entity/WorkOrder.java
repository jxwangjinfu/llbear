package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 工单信息
 *
 * @author lw
 * @date 2019-10-12 13:57:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_work_order")
public class WorkOrder extends Model<WorkOrder> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 工单标题
   */
    private String title;
    /**
   * 工单内容
   */
    private String content;
    /**
   * 状态 0：待处理 1：处理中 2：完结
   */
    private String state;
    /**
   * 0-正常，1-删除
   */
    @TableLogic
	@TableField(fill = FieldFill.INSERT)
    private String delFlag;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 创建人
   */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
   * 变更时间
   */
    private LocalDateTime updateTime;
    /**
   * 变更人
   */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
