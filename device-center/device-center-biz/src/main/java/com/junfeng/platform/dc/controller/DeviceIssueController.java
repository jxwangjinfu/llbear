package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.junfeng.platform.dc.util.DeviceUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceIssue;
import com.junfeng.platform.dc.service.DeviceIssueService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

/**
 * 问题表
 *
 * @author hanyx
 * @date 2019-10-08 14:28:13
 */
@Api(tags = {"问题表"})
@RestController
@AllArgsConstructor
@RequestMapping("/deviceissue")
public class DeviceIssueController {

	private final DeviceIssueService deviceIssueService;

	private final DeviceService deviceService;

	/**
	 * 简单分页查询
	 *
	 * @param page        分页对象
	 * @param deviceIssue 问题表
	 * @return 问题列表
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 问题表")
	@GetMapping("/page")
	public R<IPage<DeviceIssue>> getDeviceIssuePage(Page<DeviceIssue> page, DeviceIssue deviceIssue) {
		return R.ok(deviceIssueService.getDeviceIssuePage(page, deviceIssue));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceIssue> getById(@PathVariable("id") Long id) {
		return R.ok(deviceIssueService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceIssue 设备问题
	 * @return R
	 */
	@ApiOperation(value = "新增问题", notes = "参数： deviceIssue")
	@SysLog("新增问题")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceissue_add')")
	public R save(@RequestBody @Validated DeviceIssue deviceIssue) {
		R r = validate(deviceIssue);
		if (r.getCode() != R.ok().getCode()) {
			return r;
		}

		deviceIssue.setCreateTime(LocalDateTime.now());
		deviceIssue.setUpdateTime(LocalDateTime.now());
		deviceIssue.setCreateBy(ContextHolderUtil.getUsername());
		deviceIssue.setUpdateBy(ContextHolderUtil.getUsername());
		deviceIssue.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return R.ok(deviceIssueService.save(deviceIssue));
	}

	/**
	 * 功能描述: 验证合法性
	 *
	 * @param deviceIssue 设备问题
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/16 15:12
	 */
	private R validate(DeviceIssue deviceIssue) {
		R r = DeviceUtils.validateDeviceIssue(deviceIssue);
		if (r.getCode() != R.ok().getCode()) {
			return r;
		}

		Device device = deviceService.getDeviceByIssue(deviceIssue);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(deviceIssue.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		return R.ok();
	}

	/**
	 * 修改记录
	 *
	 * @param deviceIssue 设备问题
	 * @return R
	 */
	@ApiOperation(value = "修改问题", notes = "参数： deviceIssue")
	@SysLog("修改问题")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceissue_edit')")
	public R update(@RequestBody @Validated DeviceIssue deviceIssue) {
		R r = validate(deviceIssue);
		if (r.getCode() != R.ok().getCode()) {
			return r;
		}

		deviceIssue.setUpdateTime(LocalDateTime.now());
		deviceIssue.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceIssueService.updateById(deviceIssue));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除问题", notes = "参数： id")
	@SysLog("删除问题")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_deviceissue_del')")
	public R removeById(@PathVariable Long id) {
		// 逻辑删除
		DeviceIssue deviceIssue = deviceIssueService.getById(id);
		if (CheckUtils.isEmpty(deviceIssue)) {
			return R.failed("不存在该的记录");
		}

		if (deviceIssue.getDelFlag().equals(DeviceCenterConstant.DEL_FLAG_TRUE)) {
			return R.failed("该记录已被删除");
		}

		deviceIssue.setDelFlag(DeviceCenterConstant.DEL_FLAG_TRUE);
		deviceIssue.setUpdateTime(LocalDateTime.now());
		deviceIssue.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceIssueService.updateById(deviceIssue));
	}

}
