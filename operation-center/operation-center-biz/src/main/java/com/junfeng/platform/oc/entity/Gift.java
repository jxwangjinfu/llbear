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
 * 赠品表
 *
 * @author wangjf
 * @date 2019-10-12 14:09:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_gift")
public class Gift extends Model<Gift> {
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
   * 赠品活动名称
   */
    private String giftName;
    /**
   * 赠品状态,0代表未开始、1代表进行中、-1代表结束
   */
    private Integer state;
    /**
   * 赠品发行数量,默认1张
   */
    private Integer publishNumber;
    /**
   * 有效期类型，0-非长期，1-长期
   */
    private Integer useType;
    /**
   * 关联SKU主键
   */
    private Long pcSkuId;
    /**
   * 用户接收有效期
   */
    private LocalDateTime receiveDate;
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
