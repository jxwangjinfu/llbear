package com.junfeng.platform.pc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 库存变动流水
 *
 * @author lw
 * @date 2019-10-15 16:44:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pc_sku_stock_record")
public class SkuStockRecord extends Model<SkuStockRecord> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * sku对应编号
   */
    private Long skuCode;
    /**
   * 库存类型，0 初始化 1 扣减 2 退回
   */
    private String stockType;
    /**
   * 数量
   */
    private Integer count;
    /**
   *
   */
    private Integer countBefore;
    /**
   *
   */
    private Integer countAfter;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   *
   */
    private String createBy;

}
