package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.UserGroup;
import com.junfeng.platform.csc.service.UserGroupService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 客服组信息
 *
 * @author lw
 * @date 2019-10-12 11:44:30
 */
@Api(tags = {"客服组信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/usergroup")
public class UserGroupController {

	private final UserGroupService userGroupService;

	/**
	 * 简单分页查询
	 *
	 * @param page      分页对象
	 * @param userGroup 客服组信息
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 客服组信息")
	@GetMapping("/page")
	public R<IPage<UserGroup>> getUserGroupPage(Page<UserGroup> page, UserGroup userGroup) {
		return R.ok(userGroupService.getUserGroupPage(page, userGroup));
	}

	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<UserGroup> getById(@PathVariable("id") Long id) {
		return R.ok(userGroupService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param userGroup
	 * @return R
	 */
	@ApiOperation(value = "新增客服组信息", notes = "参数： userGroup")
	@SysLog("新增客服组信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('csc_usergroup_add')")
	public R save(@RequestBody UserGroup userGroup) {
		if(userGroupService.save(userGroup)){
			return R.ok();
		}else{
			return R.failed("保存失败");
		}
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除客服组信息", notes = "参数： id")
	@SysLog("删除客服组信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('csc_usergroup_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(userGroupService.removeById(id));
	}
}
