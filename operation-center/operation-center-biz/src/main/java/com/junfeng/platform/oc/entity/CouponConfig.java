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
 * 优惠券使用配置表
 *
 * @author wangjf
 * @date 2019-10-09 10:53:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_coupon_config")
public class CouponConfig extends Model<CouponConfig> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 配置状态,0-启用、-1-结束
   */
    private Integer state;
    /**
   * 优惠券使用规则,0-可全部叠加、1-一次使用一张
   */
    private Integer couponUseRule;
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
