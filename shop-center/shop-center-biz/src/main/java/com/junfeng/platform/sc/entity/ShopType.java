package com.junfeng.platform.sc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 店铺类型
 *
 * @author lw
 * @date 2019-10-23 14:57:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sc_shop_type")
public class ShopType extends Model<ShopType> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 名称
   */
    private String name;
    /**
   * '0-正常，1-删除'
   */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String delFlag;
    /**
   * 创建时间
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
