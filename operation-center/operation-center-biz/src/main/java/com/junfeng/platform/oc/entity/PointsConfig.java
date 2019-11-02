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
 * 运营规则表
 *
 * @author wangjf
 * @date 2019-10-17 17:14:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_points_config")
public class PointsConfig extends Model<PointsConfig> {
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
   * 操作类型,0-消耗,1-获取
   */
    private Integer operationType;
    /**
   * 规则类型,1-登录,2-购物,3...
   */
    private Integer pointsType;
    /**
   * 规则名称
   */
    private String pointsName;
    /**
   * 配置状态,0-启用、-1-结束
   */
    private Integer state;
    /**
   * 积分值
   */
    private Integer points;
    /**
   * 积分规则
   */
    private String pointsRule;
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
