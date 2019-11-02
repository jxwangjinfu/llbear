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
 * 买赠等级表
 *
 * @author wangjf
 * @date 2019-10-15 14:32:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_buy_gift_level")
public class BuyGiftLevel extends Model<BuyGiftLevel> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 买赠表主键
   */
    private Long ocBuyGiftId;
    /**
   * 订单使用门槛,0代表无门槛,具体数字代表订单满该数字才可使用
   */
    private Integer orderUsePre;
    /**
   * 是否免邮,0-非免邮,1-免邮
   */
    private Integer isPost;
    /**
   * 获得积分值
   */
    private Integer points;
    /**
   * 是否可用优惠券,0-不可以,1-可以
   */
    private Integer isCoupon;
    /**
   * 是否可用红包,0-不可以,1-可以
   */
    private Integer isRedEnvelope;
    /**
   * 是否可用赠品,0-不可以,1-可以
   */
    private Integer isGift;
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
