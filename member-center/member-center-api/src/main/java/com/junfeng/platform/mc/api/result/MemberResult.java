package com.junfeng.platform.mc.api.result;

import lombok.Data;

/**
 * 会员信息结果
 *
 * @author daiysh
 * @projectName member-center-api
 * @date 2019-09-19 17:24
 **/
@Data
public class MemberResult {

	private Long id;
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
	 * 电子邮件
	 */
	private String email;
	/**
	 * 关联用户Id
	 */
	private Integer userId;
	/**
	 * 身份Id
	 */
	private String identityId;
	/**
	 * 微信账号
	 */
	private String openId;
	/**
	 * 范围
	 */
	private String scope;
	/**
	 * 会员级别值
	 */
	private Integer levelValue;
	/**
	 * 会员积分
	 */
	private Integer points;
	/**
	 * 余额
	 */
	private Integer balance;
	/**
	 * 性别  1：男  2：女
	 */
	private Integer gender;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 生日
	 */
	private String birth;
	/**
	 * 备注
	 */
	private String remark;
}
