package com.junfeng.wc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.wc.entity.Miniapp;
import com.junfeng.wc.service.MiniappService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 小程序
 *
 * @author daiysh
 * @date 2019-09-25 10:49:30
 */
@Api(tags = {"小程序"})
@RestController
@AllArgsConstructor
@RequestMapping("/miniapp")
public class MiniappController {

	private final MiniappService miniappService;

	/**
	 * 简单分页查询
	 *
	 * @param page    分页对象
	 * @param miniapp 小程序
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 小程序")
	@GetMapping("/page")
	public R<IPage<Miniapp>> getMiniappPage(Page<Miniapp> page, Miniapp miniapp) {
		return R.ok(miniappService.getMiniappPage(page, miniapp));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<Miniapp> getById(@PathVariable("id") Long id) {
		return R.ok(miniappService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param miniapp
	 * @return R
	 */
	@ApiOperation(value = "新增小程序", notes = "参数： miniapp")
	@SysLog("新增小程序")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('wc_miniapp_add')")
	public R<Boolean> save(@RequestBody Miniapp miniapp) {
		return R.ok(miniappService.save(miniapp));
	}

	/**
	 * 修改记录
	 *
	 * @param miniapp
	 * @return R
	 */
	@ApiOperation(value = "修改小程序", notes = "参数： miniapp")
	@SysLog("修改小程序")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('wc_miniapp_edit')")
	public R<Boolean> update(@RequestBody Miniapp miniapp) {
		return R.ok(miniappService.updateById(miniapp));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除小程序", notes = "参数： id")
	@SysLog("删除小程序")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('wc_miniapp_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(miniappService.removeById(id));
	}

}
