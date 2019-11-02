package com.junfeng.platform.pc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 品牌类目关联表
 *
 * @author lw
 * @date 2019-10-14 15:19:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_category_brand")
public class CategoryBrand extends Model<CategoryBrand> {
private static final long serialVersionUID = 1L;

    /**
   * 商品类目id
   */
    @TableId
    private Long categoryId;
    /**
   * 品牌id
   */
    private Long brandId;
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
