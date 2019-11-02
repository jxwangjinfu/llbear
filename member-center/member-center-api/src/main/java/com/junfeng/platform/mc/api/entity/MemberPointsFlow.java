package com.junfeng.platform.mc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 会员积分流水表
 *
 * @author daiysh
 * @date 2019-10-08 15:03:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mc_member_points_flow")
public class MemberPointsFlow extends Model<MemberPointsFlow> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 会员ID
	 */
	private Long memberid;
	/**
	 * 操作类型 1 获取、0 消耗
	 */
	private String operationType;
	/**
	 * 积分类型，1 注册、2 登陆
	 */
	private String pointsType;
	/**
	 * 积分值
	 */
	private Integer points;
	/**
	 * 之前积分值
	 */
	private Integer beforepoints;
	/**
	 * 之后积分值
	 */
	private Integer afterpoints;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 删除标记
	 */
	private String delFlag;

}
