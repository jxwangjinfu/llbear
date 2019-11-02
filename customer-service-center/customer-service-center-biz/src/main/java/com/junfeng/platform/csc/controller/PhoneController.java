package com.junfeng.platform.csc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.csc.controller.model.PhoneVo;
import com.junfeng.platform.csc.entity.Phone;
import com.junfeng.platform.csc.service.PhoneService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 话机信息表
 *
 * @author lw
 * @date 2019-09-29 10:37:52
 */
@Api(tags = {"话机信息表"})
@RestController
@AllArgsConstructor
@RequestMapping("/phone")
public class PhoneController {

	private final PhoneService phoneService;

	/**
	 * 简单分页查询
	 *
	 * @param page  分页对象
	 * @param phone 话机信息表
	 * @return
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 话机信息表")
	@GetMapping("/page")
	public R<IPage<Phone>> getPhonePage(Page<Phone> page, Phone phone) {
		return R.ok(phoneService.getPhonePage(page, phone));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<Phone> getById(@PathVariable("id") Long id) {
		return R.ok(phoneService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param phone
	 * @return R
	 */
	@ApiOperation(value = "新增话机信息表", notes = "参数： phone")
	@SysLog("新增话机信息表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('csc_phone_add')")
	public R save(@RequestBody Phone phone) {
		return R.ok(phoneService.saveWithCheck(phone));
	}

	/**
	 * 修改记录
	 *
	 * @param phone
	 * @return R
	 */
	@ApiOperation(value = "修改话机信息表", notes = "参数： phone")
	@SysLog("修改话机信息表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('csc_phone_edit')")
	public R update(@RequestBody Phone phone) {
		return R.ok(phoneService.updateByIdWithCheck(phone));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@ApiOperation(value = "删除话机信息表", notes = "参数： id")
	@SysLog("删除话机信息表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('csc_phone_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(phoneService.removeById(id));
	}


	@ApiOperation(value = "话机占线", notes = "参数： 话机id")
	@SysLog("占线通知")
	@PostMapping("/onbusy/{id}")
	public R onBusy(@PathVariable Long id) {
		return R.ok(phoneService.onBusy(id));
	}


	@ApiOperation(value = "话机释放", notes = "参数： 话机id")
	@SysLog("释放话机")
	@PostMapping("/onfree/{id}")
	public R onFree(@PathVariable Long id) {
		return R.ok(phoneService.onFree(id));
	}


	@ApiOperation(value = "话机上线", notes = "参数： phoneVo 话机Id和人员Id")
	@SysLog("话机上线")
	@PostMapping("/online")
	@PreAuthorize("@cscpms.hasPermission('Phone')")
	public  R onLine(@RequestBody PhoneVo phoneVo){
		return R.ok(phoneService.onLine(phoneVo.getPhoneId(),phoneVo.getUserId()));
	}

	@ApiOperation(value = "话机下线", notes = "参数： phoneVo 话机Id和人员Id")
	@SysLog("话机下线")
	@PostMapping("/offline")
	@PreAuthorize("@cscpms.hasPermission('Phone')")
	public R offLine(@RequestBody  PhoneVo phoneVo){
		return R.ok(phoneService.offLine(phoneVo.getPhoneId(),phoneVo.getUserId()));
	}
}
