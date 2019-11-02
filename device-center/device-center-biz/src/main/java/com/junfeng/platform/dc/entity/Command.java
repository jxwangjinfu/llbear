package com.junfeng.platform.dc.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 指令
 *
 * @author fuh
 * @date 2019-09-18 16:09:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_command")
public class Command extends Model<Command> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Long id;
    /**
   * 指令代码
   */
    private String type;
    /**
   * 指令名称
   */
    private String name;
    /**
   * 指令状态，0 无效 1生效中，2全部完成，3已撤销
   */
    private String status;
    /**
   * 商家范围数
   */
    private Integer totalNum;
    /**
   * 已完成商家数
   */
    private Integer doneNum;
    /**
   * 未完成商家数
   */
    private Integer undoneNum;
    /**
   * 撤销店铺数
   */
    private Integer cancelNum;
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
    private String createBy;
    /**
   * 修改人
   */
    private String updateBy;
  
}
