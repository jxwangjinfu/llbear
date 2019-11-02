package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 工单处理记录
 *
 * @author lw
 * @date 2019-10-12 14:07:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_work_order_record")
public class WorkOrderRecord extends Model<WorkOrderRecord> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 工单编号
   */
    private Long workOrderCode;
    /**
   * 组编号
   */
    private Long groupCode;
    /**
   * 小组名称
   */
    private String groupName;
    /**
   * 处理人编号
   */
    private Long userCode;
    /**
   * 处理人姓名
   */
    private String userName;
    /**
   * 处理结果类型编码
   */
    private String reasonTypeCode;
    /**
   * 详细描述
   */
    private String orderDetail;
    /**
   * 当前处理状态 0：未分配 1：处理中 2：处理完成
   */
    private Integer state;
    /**
   * 处理顺序号
   */
    private Integer orderSort;
    /**
   * 0-正常，1-删除
   */
    @TableLogic
	@TableField(fill = FieldFill.INSERT)
    private String delFlag;
    /**
   *
   */
    private LocalDateTime createTime;
    /**
   *
   */
	@TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
   *
   */
    private LocalDateTime updateTime;
    /**
   *
   */
	@TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
