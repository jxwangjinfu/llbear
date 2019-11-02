package com.junfeng.wc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.wc.entity.Mp;
import com.junfeng.wc.service.MpService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 公众号
 *
 * @author daiysh
 * @date 2019-09-25 10:53:00
 */
@Api(tags = {"公众号"})
@RestController
@AllArgsConstructor
@RequestMapping("/mp")
public class MpController {

	private final MpService mpService;

	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param mp   公众号
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 公众号")
	@GetMapping("/page")
	public R<IPage<Mp>> getMpPage(Page<Mp> page, Mp mp) {
		return R.ok(mpService.getMpPage(page, mp));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<Mp> getById(@PathVariable("id") Long id) {
		return R.ok(mpService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param mp
	 * @return R
	 */
	@ApiOperation(value = "新增公众号", notes = "参数： mp")
	@SysLog("新增公众号")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('wc_mp_add')")
	public R<Boolean> save(@RequestBody Mp mp) {
		return R.ok(mpService.save(mp));
	}

	/**
	 * 修改记录
	 *
	 * @param mp
	 * @return R
	 */
	@ApiOperation(value = "修改公众号", notes = "参数： mp")
	@SysLog("修改公众号")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('wc_mp_edit')")
	public R<Boolean> update(@RequestBody Mp mp) {
		return R.ok(mpService.updateById(mp));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除公众号", notes = "参数： id")
	@SysLog("删除公众号")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('wc_mp_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(mpService.removeById(id));
	}

}
