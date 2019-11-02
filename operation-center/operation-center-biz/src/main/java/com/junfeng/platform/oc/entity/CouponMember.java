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
 * 优惠券会员表
 *
 * @author wangjf
 * @date 2019-09-29 16:51:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_coupon_member")
public class CouponMember extends Model<CouponMember> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 关联优惠券主键
     */
    private Long ocCouponId;
    /**
     * 会员id
     */
    private Long mcMemberId;
    /**
     * 券码
     */
    private String couponCode;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 任务标识,-1-活动结束,0-未开始,1-未领取，2-已领取未使用，3-已使用，4-已过期
     */
    private Integer state;
    /**
     * 抵扣金额
     */
    private Integer money;

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
