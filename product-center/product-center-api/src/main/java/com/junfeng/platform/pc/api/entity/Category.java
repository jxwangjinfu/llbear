package com.junfeng.platform.pc.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类目表
 *
 * @author lw
 * @date 2019-10-14 15:19:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_category")
public class Category extends Model<Category> {
private static final long serialVersionUID = 1L;

    /**
   * 类目id
   */
    @TableId
    private Long id;
    /**
   * 类目名称
   */
    private String name;
    /**
   * 父类目id,顶级类目填0
   */
    private Long parentId;
    /**
   * 是否为父节点，0为否，1为是
   */
    private Integer isParent;
    /**
   * 排序指数，越小越靠前
   */
    private Integer sort;
    /**
   * '0-正常，1-删除'
   */
	@TableLogic
    private String delFlag;
    /**
   *
   */
    private LocalDateTime createTime;
    /**
   *
   */
    private String createBy;
    /**
   *
   */
    private LocalDateTime updateTime;
    /**
   *
   */
    private String updateBy;

}
