package com.junfeng.platform.manager.service;

import com.junfeng.platform.mc.api.vo.MemberVo;
import com.pig4cloud.pig.common.core.util.R;

/**
 * 用户&会员 业务处理层
 *
 * @author daiysh
 * @date 2019-09-23 09:22:11
 */
public interface UserService {

	/**
	 * 处理注册流程
	 *
	 * @param member
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-09-23  10:49
	 **/
	R<Boolean> handleRegister(MemberVo member);

	/**
	 * 登陆
	 *
	 * @param username
	 * @param password
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Object>
	 * @author: daiysh
	 * @createTime: 2019-10-16  11:01
	 **/
	R<Object> login(String username,String password);

	/**
	 * 绑定手机号
	 *
	 * @param userId
	 * @param mobile
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-17  10:02
	 **/
	R<Boolean> bindMobile(int userId,String mobile);

	/**
	 * 绑定实名身份
	 *
	 * @param userId
	 * @param identityId
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-21  15:20
	 **/
	R<Boolean> bindIdentity(int userId,String identityId);
}
