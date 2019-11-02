package com.junfeng.platform.pc.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌表
 *
 * @author lw
 * @date 2019-10-14 15:18:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_brand")
public class Brand extends Model<Brand> {
private static final long serialVersionUID = 1L;

    /**
   * 品牌id
   */
    @TableId
    private Long id;
    /**
   * 品牌名称
   */
    private String name;
    /**
   * 品牌图片地址
   */
    private String image;
    /**
   * 品牌的首字母
   */
    private String letter;
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
