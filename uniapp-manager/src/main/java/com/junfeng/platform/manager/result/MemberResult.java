package com.junfeng.platform.manager.result;

import lombok.Data;

/**
 * 会员信息结果集
 *
 * @author daiysh
 * @date 2019-10-16 13:57
 **/
@Data
public class MemberResult extends BaseResult {
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 会员登录名
	 */
	private String name;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 积分值
	 */
	private Integer points;
}
