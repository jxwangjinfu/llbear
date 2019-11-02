package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.service.SysUserRoleService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色
 *
 * @author daiysh
 * @date 2019-10-21 11:24
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/userrole")
public class UserRoleController {
	private final SysUserRoleService sysUserRoleService;

	/**
	 * 新增用户角色信息
	 *
	 * @param userId 用户Id
	 * @return R
	 */
	@Inner
	@GetMapping("/add/{userId}")
	public R<Boolean> addUserRole(@PathVariable Integer userId) {
		// 校验
		if (userId <= 0) {
			return R.failed("参数格式不正确");
		}
		SysUserRole dbSysUserRole = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
		if (dbSysUserRole != null) {
			return R.failed("该用户存在默认角色，不能新增记录");
		}

		// 会员组角色ID
		int ROLE_MEMBER_ID = 5;
		SysUserRole sysUserRole = new SysUserRole();

		sysUserRole.setUserId(userId);
		sysUserRole.setRoleId(ROLE_MEMBER_ID);

		return R.ok(sysUserRoleService.save(sysUserRole));
	}


}
