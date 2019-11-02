package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 客服人员信息
 *
 * @author lw
 * @date 2019-10-24 15:25:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_user")
public class User extends Model<User> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 关联账号
   */
    private Integer userCode;
    /**
   * 姓名
   */
    private String userName;
    /**
   * 分组编号
   */
    private Long groupCode;
    /**
   * 权限信息
   */
    private String roles;
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
