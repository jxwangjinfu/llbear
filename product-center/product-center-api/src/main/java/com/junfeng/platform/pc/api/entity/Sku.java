package com.junfeng.platform.pc.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * sku信息
 *
 * @author lw
 * @date 2019-10-12 18:25:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_sku")
public class Sku extends Model<Sku> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;

    @TableField(exist = false)
	Integer stock;

    /**
   * spuId
   */
    private Long spuCode;
    /**
   *
   */
    private String images;
    /**
   * 价格
   */
    private Long price;
    /**
   * 特有规格赋值
   */
    private String specialIndexs;
    /**
   * 状态 0不可售 1 可售
   */
    private String state;
    /**
   * sku编号
   */
    private String skuCode;
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
