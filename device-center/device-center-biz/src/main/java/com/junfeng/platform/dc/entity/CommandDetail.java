package com.junfeng.platform.dc.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 指令明细
 *
 * @author fuh
 * @date 2019-09-18 17:30:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_command_detail")
public class CommandDetail extends Model<CommandDetail> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Long id;
    /**
   * 指令ID
   */
    private Long commandId;
    /**
   * 设备ID
   */
    private Long deviceId;
    /**
   * 指令状态，0 未完成，2已完成，3已撤销
   */
    private String status;
    /**
   * 撤销时间
   */
    private LocalDateTime cancelTime;
    /**
   * 备注
   */
    private String remark;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;
    /**
   * 创建人
   */
    private Long createBy;
    /**
   * 修改人
   */
    private Long updateBy;
  
}
