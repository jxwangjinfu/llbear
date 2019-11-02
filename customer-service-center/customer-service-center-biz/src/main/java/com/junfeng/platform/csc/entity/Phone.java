package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 话机信息表
 *
 * @author lw
 * @date 2019-09-29 10:37:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_phone")
public class Phone extends Model<Phone> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 姓名
   */
    private String name;
    /**
   * 电话号码
   */
    private String phoneNumber;
    /**
   * 在线状态
   */
    private String onlineFlag;
    /**
   * 第三方主键
   */
    private String thirdCode;
    /**
   * 话务员编号
   */
    private Long workCode;
    /**
   *
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
