package com.junfeng.platform.manager.service.imp;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.manager.result.MemberResult;
import com.junfeng.platform.manager.result.TokenResult;
import com.junfeng.platform.manager.service.UserService;
import com.junfeng.platform.mc.api.entity.Member;
import com.junfeng.platform.mc.api.feign.RemoteMemberService;
import com.junfeng.platform.mc.api.vo.MemberVo;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 用户业务实现类
 *
 * @author daiysh
 * @date 2019-10-11 14:36
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RemoteUserService remoteUserService;

	@Autowired
	private RemoteMemberService remoteMemberService;

	@Value("${auth.serverApi}")
	private String serverApi;

	@Value("${auth.authorization}")
	private String authorization;
	/**
	 * 处理注册流程
	 *
	 * @param memberVo
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: daiysh
	 * @createTime: 2019-10-30  11:40
	 **/
	@Override
	public R<Boolean> handleRegister(MemberVo memberVo) {
		String clientId = "uniapp-manager";
		memberVo.setClientId(clientId);
		return remoteMemberService.register(memberVo,SecurityConstants.FROM_IN);
	}

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return: com.pig4cloud.pig.common.core.util.R<java.lang.Object>
	 * @author: daiysh
	 * @createTime: 2019-10-30  11:41
	 **/
	@Override
	public R<Object> login(String username, String password) {
		//1 请求认证中心
		R<TokenResult> tokenResultR = getAuthorization(authorization,username,password);
		Assert.isTrue(CommonConstants.SUCCESS.equals(tokenResultR.getCode()), tokenResultR.getMsg());

		//2 获取会员基本信息
		TokenResult tokenResult = tokenResultR.getData();
		R<Member> memberR = remoteMemberService.getMember(Integer.parseInt(tokenResult.getUser_id()), SecurityConstants.FROM_IN);
		if (memberR.getCode() != 0) {
			return R.failed(memberR.getMsg());
		}

		//3 组装数据返回给前端
		return R.ok(getMemberResult(username, tokenResult, memberR));
	}

	/**
	 * 组装返回给会员的结果数据
	 *
	 * @param username
	 * @param tokenResult
	 * @param memberR
	 * @return: com.junfeng.platform.manager.result.MemberResult
	 * @author: daiysh
	 * @createTime: 2019-10-30  11:42
	 **/
	private MemberResult getMemberResult(String username, TokenResult tokenResult, R<Member> memberR) {
		Member member = memberR.getData();

		MemberResult memberResult = new MemberResult();
		memberResult.setAccessToken(tokenResult.getAccess_token());
		memberResult.setUserId(member.getUserId());
		memberResult.setName(username);
		memberResult.setMobile(member.getMobile());
		memberResult.setNickName(member.getNickName());
		memberResult.setPoints(member.getPoints());
		return memberResult;
	}

	/**
	 * 认证账号
	 *
	 * @param authorization
	 * @param username
	 * @param password
	 * @return: com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.manager.result.TokenResult>
	 * @author: daiysh
	 * @createTime: 2019-10-30  11:42
	 **/
	private R<TokenResult> getAuthorization(String authorization,String username,String password) {
		RestTemplate restTemplate = new RestTemplate();
		// 1.1 增加头部参数
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + authorization);
		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

		StringBuffer urlBuffer = joinUrl(username, password);
		ResponseEntity<String> resEntity = null;
		TokenResult tokenResult = null;
		try {
			resEntity = restTemplate.exchange(urlBuffer.toString(), HttpMethod.GET, requestEntity, String.class);
			JSONObject jsonObject = JSONObject.parseObject(resEntity.getBody());

			String code = jsonObject.getString("code");
			if (code != null) {
				return R.failed(jsonObject.getString("msg"));
			}

			tokenResult = jsonObject.toJavaObject(TokenResult.class);
		} catch (RestClientException e) {
			log.error(e.getMessage());
			return R.failed("用户名不存在或者密码错误");
		}

		return R.ok(tokenResult);
	}

	/**
	 * 拼接Url
	 *
	 * @param username
	 * @param password
	 * @return: java.lang.StringBuffer
	 * @author: daiysh
	 * @createTime: 2019-10-30  11:44
	 **/
	private StringBuffer joinUrl(String username, String password) {
		StringBuffer urlBuffer = new StringBuffer();

		urlBuffer.append(serverApi)
			.append("/auth/oauth/token?")
			.append("username=")
			.append(username)
			.append("&password=")
			.append(password)
			.append("&grant_type=password&scope=server");
		return urlBuffer;
	}

	@Override
	public R<Boolean> bindMobile(int userId, String mobile) {

		//1 作为账号绑定
		SysUser sysUser = new SysUser();
		sysUser.setUserId(userId);
		sysUser.setPhone(mobile);
		R<Boolean> returnR = remoteUserService.bindUser(sysUser, SecurityConstants.FROM_IN);
		if (returnR.getCode() != 0) {
			return R.failed(returnR.getMsg());
		}

		//2 作为会员信息绑定
		R<Member> memberR = remoteMemberService.getMember(userId, SecurityConstants.FROM_IN);
		if (memberR.getCode() != 0) {
			return R.failed(memberR.getMsg());
		}
		Member member = memberR.getData();
		member.setMobile(mobile);

		remoteMemberService.updateMember(member, SecurityConstants.FROM_IN);

		return R.ok();
	}

	@Override
	public R<Boolean> bindIdentity(int userId, String identityId) {
		R<Member> memberR = remoteMemberService.getMember(userId, SecurityConstants.FROM_IN);
		if (memberR.getCode() != 0) {
			return R.failed(memberR.getMsg());
		}
		Member member = memberR.getData();
		member.setIdentityId(identityId);

		remoteMemberService.updateMember(member, SecurityConstants.FROM_IN);

		return R.ok();
	}

}
