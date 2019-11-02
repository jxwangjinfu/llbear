package com.junfeng.platform.csc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junfeng.platform.csc.entity.RoleType;
import com.junfeng.platform.csc.entity.User;
import com.junfeng.platform.csc.service.UserService;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author lw
 * @version 1.0
 * @createDate 2019/10/25 10:09
 * @projectName pig
 */
@Slf4j
@Component("cscpms")
@AllArgsConstructor
public class CustomerPermissionService {

	private final UserService userService;
	/**
	 * 判断接口是否有xxx:xxx权限
	 *
	 * @param roleTypeString 权限
	 * @return {boolean}
	 */
	public boolean hasPermission(String roleTypeString) {
		RoleType roleType  = RoleType.valueOf(roleTypeString);
		if(roleType==null){
			return false;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		PigUser pigUser =SecurityUtils.getUser(authentication);
		if (pigUser == null) {
			return false;
		}
		User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserCode, pigUser.getId()));
		if(user ==null||StrUtil.isBlank(user.getRoles())) return false;
		JSONArray jsonArray = JSON.parseArray(user.getRoles());
		if(jsonArray.contains(roleType.getRoleId())) return true;
		else return false;
	}

/*	public boolean hasPermissionOnline(Principal principal) {
		PigUser pigUser = getPigUser(principal);
		User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserCode, pigUser.getId()).last(" limit 1"));
		if (user == null || StrUtil.isBlank(user.getRoles())) return false;
		JSONArray jsonArray = JSON.parseArray(user.getRoles());
		if (jsonArray.contains(RoleType.Online.getRoleId())) return true;
		else return false;
	}

	private PigUser getPigUser(java.security.Principal principal){
		if (principal instanceof PigUser) {
			return (PigUser)principal;
		}
		return null;
	}*/
}
