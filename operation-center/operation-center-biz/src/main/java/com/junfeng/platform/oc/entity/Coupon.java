package com.junfeng.platform.oc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券
 *
 * @author wangjf
 * @date 2019-09-23 15:22:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_coupon")
public class Coupon extends Model<Coupon> {
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
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券状态,0代表未开始、1代表进行中、-1代表结束
     */
    private Integer state;
    /**
     * 优惠券类型,1、代表满减券、2代表折扣券、3代表随机券
     */
    private Integer typeCode;
    /**
     * 优惠券发行数量,默认1张
     */
    private Integer publishNumber;
    /**
     * 已使用数量
     */
    private Integer useNumber;
    /**
     * 已领取数量
     */
    private Integer recipientsNumber;
    /**
     * 未使用数量
     */
    private Integer unUseNumber;
    /**
     * 订单支付金额
     */
    private BigDecimal orderPayMoneyTotal;
    /**
     * 适用商品,0全部商品、1指定商品、-1指定商品不可用;关联子表
     */
    private Integer usableGoods;
    /**
     * 订单使用门槛,0代表无门槛,具体数字代表订单满该数字才可使用
     */
    private Integer orderUsePre;
    /**
     * 优惠内容，json格式
     */
    private String discountContext;
    /**
     * 开始时间
     */
    private LocalDateTime useStartTime;
    /**
     * 结束时间
     */
    private LocalDateTime useEndTime;
    /**
     * 领取人限制,0代表未开始、1代表指定人可用，关联子表
     */
    private Integer recipientsLimit;
    /**
     * 每人领取次数限制,0代表无限制、具体数值代表可领用的数量
     */
    private Integer recipientsNumberLimit;
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
