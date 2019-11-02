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
 * 买赠会员表
 *
 * @author wangjf
 * @date 2019-10-14 15:10:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_buy_gift_member")
public class BuyGiftMember extends Model<BuyGiftMember> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 买赠主键
   */
    private Long ocBuyGiftId;
    /**
   * 会员id
   */
    private Long mcMemberId;
    /**
   * 已购物订单编号
   */
    private String orderNo;
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
