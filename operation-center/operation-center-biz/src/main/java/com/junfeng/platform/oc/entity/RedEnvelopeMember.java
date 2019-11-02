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
 * 红包会员表
 *
 * @author wangjf
 * @date 2019-10-09 16:37:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("oc_red_envelope_member")
public class RedEnvelopeMember extends Model<RedEnvelopeMember> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 关联红包主键
   */
    private Long ocRedEnvelopeId;
    /**
   * 会员id
   */
    private Long mcMemberId;
    /**
   * 红包金额
   */
    private Integer money;
    /**
   * 红包码
   */
    private String redEnvelopeCode;
	/**
	 * 订单编号
	 */
	private String orderNo;
    /**
   * 任务标识,-1-活动结束,0-未开始,1-未领取，2-已领取未使用，3-已使用，4-已过期
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
