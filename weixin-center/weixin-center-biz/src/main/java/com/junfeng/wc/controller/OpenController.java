package com.junfeng.wc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.wc.entity.Open;
import com.junfeng.wc.service.OpenService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 开放平台
 *
 * @author daiysh
 * @date 2019-09-25 10:55:42
 */
@Api(tags = {"开放平台"})
@RestController
@AllArgsConstructor
@RequestMapping("/open")
public class OpenController {

	private final OpenService openService;

	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param open 开放平台
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 开放平台")
	@GetMapping("/page")
	public R<IPage<Open>> getOpenPage(Page<Open> page, Open open) {
		return R.ok(openService.getOpenPage(page, open));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<Open> getById(@PathVariable("id") Long id) {
		return R.ok(openService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param open
	 * @return R
	 */
	@ApiOperation(value = "新增开放平台", notes = "参数： open")
	@SysLog("新增开放平台")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('wc_open_add')")
	public R<Boolean> save(@RequestBody Open open) {
		return R.ok(openService.save(open));
	}

	/**
	 * 修改记录
	 *
	 * @param open
	 * @return R
	 */
	@ApiOperation(value = "修改开放平台", notes = "参数： open")
	@SysLog("修改开放平台")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('wc_open_edit')")
	public R<Boolean> update(@RequestBody Open open) {
		return R.ok(openService.updateById(open));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除开放平台", notes = "参数： id")
	@SysLog("删除开放平台")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('wc_open_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(openService.removeById(id));
	}

}
