package com.junfeng.platform.mc.api.vo;

import lombok.Data;

/**
 * 会员信息
 *
 * @author daiysh
 * @projectName member-center-api
 * @date 2019-09-19 16:32
 **/
@Data
public class MemberVo {
	//手机注册(手机号)
	private String mobile;

	//会员名手机注册(账号名)
	private String userName;

	//微信授权注册(openId)
	private String openId;

	//邮箱注册(邮箱地址)
	private String email;

	//实名注册(身份证ID)
	private String identityId;

	//应用Id
	private String clientId;

	//昵称
	private String nickName;

	//密码
	private String password;
}
