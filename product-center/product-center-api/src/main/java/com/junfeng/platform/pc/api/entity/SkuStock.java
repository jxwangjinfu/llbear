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
 * sku库存表
 *
 * @author lw
 * @date 2019-10-14 15:41:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_sku_stock")
public class SkuStock extends Model<SkuStock> {
private static final long serialVersionUID = 1L;

    /**
   * skuId
   */
    @TableId(type = IdType.INPUT)
    private Long skuCode;
    /**
   * 库存数量
   */
    private Integer stock;
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
