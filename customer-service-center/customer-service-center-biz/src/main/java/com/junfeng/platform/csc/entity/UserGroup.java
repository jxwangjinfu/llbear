package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 客服组信息
 *
 * @author lw
 * @date 2019-10-12 11:44:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_user_group")
public class UserGroup extends Model<UserGroup> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 客服组名称
   */
    private String groupName;
    /**
   * 0-正常，1-删除
   */
    @TableLogic
	@TableField(fill= FieldFill.INSERT)
	private String delFlag;
    /**
   *
   */
    private LocalDateTime createTime;
    /**
   *
   */
	@TableField(fill= FieldFill.INSERT)
    private String createBy;
    /**
   *
   */
    private LocalDateTime updateTime;
    /**
   *
   */
	@TableField(fill= FieldFill.UPDATE)
    private String updateBy;

}
