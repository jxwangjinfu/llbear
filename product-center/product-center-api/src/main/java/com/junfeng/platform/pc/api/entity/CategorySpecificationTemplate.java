package com.junfeng.platform.pc.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类目关联模板
 *
 * @author lw
 * @date 2019-10-14 15:19:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_category_specification_template")
public class CategorySpecificationTemplate extends Model<CategorySpecificationTemplate> {
private static final long serialVersionUID = 1L;

    /**
   * 规格模板所属商品分类id
   */
    @TableId(type = IdType.INPUT)
    private Long categoryId;
    /**
   * 规格参数模板，json格式
   */
    private String spuTemplate;
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
