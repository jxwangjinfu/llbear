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
 * 赠品定时任务触发表
 *
 * @author wangjf
 * @date 2019-10-12 14:55:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_gift_quart")
public class GiftQuart extends Model<GiftQuart> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 关联赠品主键
   */
    private Long giftId;
    /**
   * 赠品状态,1代表进行中、-1代表结束
   */
    private Integer giftState;
    /**
   * 任务执行时间
   */
    private String jobTime;
    /**
   * 任务名称
   */
    private String jobName;
    /**
   * 任务组
   */
    private String jobGroup;
    /**
   * 执行内容或者参数
   */
    private String context;
    /**
   * 任务标识,0-开始，1-结束
   */
    private String state;
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
