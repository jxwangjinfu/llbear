package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.entity.Role;
import com.junfeng.platform.csc.service.RoleService;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客服权限
 *
 * @author lw
 * @date 2019-10-24 15:11:57
 */
/*@Api(tags = {"客服权限"},hidden = true)*/
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

	private final RoleService roleService;

	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param role 客服权限
	 * @return
	 */
/*	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 客服权限")*/
	@GetMapping("/page")
	public R<IPage<Role>> getRolePage(Page<Role> page, Role role) {
		return R.ok(roleService.getRolePage(page, role));
	}
}
