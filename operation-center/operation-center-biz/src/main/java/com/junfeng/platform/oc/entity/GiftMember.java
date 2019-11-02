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
 * 赠品会员表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_gift_member")
public class GiftMember extends Model<GiftMember> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 关联赠品主键
   */
    private Long ocGiftId;
    /**
   * 会员id
   */
    private Long mcMemberId;
    /**
   * 订单编号
   */
    private String orderNo;
    /**
   * 标识,-1-活动结束,0-未开始,1-未领取，2-已领取，3-已使用，4-已过期，5-已发放
   */
    private Integer state;
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
