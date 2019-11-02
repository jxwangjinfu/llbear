package com.junfeng.platform.mc.api.vo;

import lombok.Data;

/**
 * 会员积分流水
 *
 * @author daiysh
 * @date 2019-10-08 15:15
 **/
@Data
public class MemberPointsFlowVo {

	/**
	 * 会员ID
	 */
	private long memberId;

	/**
	 * 操作类型
	 */
	private String operationType;

	/**
	 * 积分类型
	 */
	private String pointsType;

	/**
	 * 分值
	 */
	private int points;

	/**
	 * 回调地址
	 */
	private String callbackUrl;

	/**
	 * 备注
	 */
	private String remark;
}
