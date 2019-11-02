package com.junfeng.platform.oc.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_buy_gift_level_goods")
public class BuyGiftLevelGoods extends Model<BuyGiftLevelGoods> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 买赠等级表主键
   */
    private Long ocBuyGiftLevelId;
    /**
   * 1代表优惠券，2代表红包，3代表赠品
   */
    private Integer type;
    /**
   * 对应于type表的主键
   */
    private Long typeId;
    /**
   * 数量
   */
    private Integer giftNumber;
    /**
   * 0-正常，1-删除
   */
    private String delFlag;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 创建人
   */
	@TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
   * 最后变更时间
   */
    private LocalDateTime updateTime;
    /**
   * 最后更新人员
   */
	@TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
