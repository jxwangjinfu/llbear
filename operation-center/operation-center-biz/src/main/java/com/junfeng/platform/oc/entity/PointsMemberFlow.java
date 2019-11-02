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
 * 会员获取积分流水
 *
 * @author wangjf
 * @date 2019-10-21 14:55:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_points_member_flow")
public class PointsMemberFlow extends Model<PointsMemberFlow> {
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
   * 会员id
   */
    private Long mcMemberId;
    /**
   * oc_points_config外键
   */
    private Long ocPointsConfigId;
    /**
   * 积分值
   */
    private Integer points;
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
