package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.csc.entity.User;
import com.junfeng.platform.csc.service.UserService;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客服人员信息
 *
 * @author lw
 * @date 2019-10-24 15:25:15
 */
@Api(tags = {"客服人员信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final RemoteUserService remoteUserService;


	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param user 客服人员信息
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 客服人员信息")
	@GetMapping("/page")
	public R<IPage<User>> getUserPage(Page<User> page, User user) {
		return R.ok(userService.getUserPage(page, user));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<User> getById(@PathVariable("id") Long id) {
		return R.ok(userService.getById(id));
	}


	@ApiOperation(value = "通过账号查询登录信息", notes = "参数:username 账号")
	@GetMapping("/userinfo")
	public R<UserInfo> getUserInfoByUserName(@RequestParam String username) {
		return remoteUserService.info(username, SecurityConstants.FROM_IN);
	}

	/**
	 * 新增记录
	 *
	 * @param user
	 * @return R
	 */
	@ApiOperation(value = "新增客服人员信息", notes = "参数： user")
	@SysLog("新增客服人员信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('csc_user_add')")
	public R save(@RequestBody User user) {
		return R.ok(userService.saveWithCheck(user));
	}

	/**
	 * 修改记录
	 *
	 * @param user
	 * @return R
	 */
	@ApiOperation(value = "修改客服人员信息", notes = "参数： user")
	@SysLog("修改客服人员信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('csc_user_edit')")
	public R update(@RequestBody User user) {
		user.setUpdateBy(SecurityUtils.getUser().getUsername());
		return R.ok(userService.updateById(user));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除客服人员信息", notes = "参数： id")
	@SysLog("删除客服人员信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('csc_user_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(userService.removeById(id));
	}
}
