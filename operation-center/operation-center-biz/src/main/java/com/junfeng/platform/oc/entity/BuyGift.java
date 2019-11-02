package com.junfeng.platform.oc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 买赠表
 *
 * @author wangjf
 * @date 2019-10-14 15:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_buy_gift")
public class BuyGift extends Model<BuyGift> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 系统来源
   */
    private String clientId;
    /**
   * 买赠名称
   */
    private String giftName;
    /**
   * 优惠券状态,0代表未开始、1代表进行中、-1代表结束
   */
    private Integer state;
    /**
   * 适用商品,0全部商品、1指定商品、-1指定商品不可用;关联子表
   */
    private Integer usableGoods;
    /**
   * 开始时间
   */
    private LocalDateTime useStartTime;
    /**
   * 结束时间
   */
    private LocalDateTime useEndTime;
    /**
   * 优惠等级类型。1代表阶梯满减，2代表循环满减
   */
    private Integer levelType;
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
