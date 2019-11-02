package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceRunTime;
import com.junfeng.platform.dc.service.DeviceRunTimeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

/**
 * 设备运行时间
 *
 * @author hanyx
 * @date 2019-10-25 15:29:06
 */
@Api(tags = {"设备运行时间"})
@RestController
@AllArgsConstructor
@RequestMapping("/deviceruntime")
public class DeviceRunTimeController {

	private final DeviceRunTimeService deviceRunTimeService;
	private final DeviceService deviceService;

	/**
	 * 简单分页查询
	 *
	 * @param page          分页对象
	 * @param deviceRunTime 设备运行时间
	 * @return 分页列表
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 设备运行时间")
	@GetMapping("/page")
	public R<IPage<DeviceRunTime>> getDeviceRunTimePage(Page<DeviceRunTime> page, DeviceRunTime deviceRunTime) {
		return R.ok(deviceRunTimeService.getDeviceRunTimePage(page, deviceRunTime));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceRunTime> getById(@PathVariable("id") Long id) {
		return R.ok(deviceRunTimeService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceRunTime 设备运行时间
	 * @return R
	 */
	@ApiOperation(value = "新增设备运行时间", notes = "参数： deviceRunTime")
	@SysLog("新增设备运行时间")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceruntime_add')")
	public R save(@RequestBody @Validated DeviceRunTime deviceRunTime) {
		Device device = deviceService.getById(deviceRunTime.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此id的设备");
		}

		// 一个设备一天只有一条（未被逻辑删除）记录
		if (deviceRunTimeService.isExist(deviceRunTime)) {
			return R.failed("该设备当天已有运行信息，不能新增，请修改");
		}

		deviceRunTime.setCreateTime(LocalDateTime.now());
		deviceRunTime.setUpdateTime(LocalDateTime.now());
		deviceRunTime.setCreateBy(ContextHolderUtil.getUsername());
		deviceRunTime.setUpdateBy(ContextHolderUtil.getUsername());
		deviceRunTime.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);
		return R.ok(deviceRunTimeService.save(deviceRunTime));
	}

	/**
	 * 修改记录
	 *
	 * @param deviceRunTime 设备运行时间
	 * @return R
	 */
	@ApiOperation(value = "修改设备运行时间", notes = "参数： deviceRunTime")
	@SysLog("修改设备运行时间")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceruntime_edit')")
	public R update(@RequestBody @Validated DeviceRunTime deviceRunTime) {
		Device device = deviceService.getById(deviceRunTime.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此id的设备");
		}

		deviceRunTime.setUpdateTime(LocalDateTime.now());
		deviceRunTime.setUpdateBy(ContextHolderUtil.getUsername());
		return R.ok(deviceRunTimeService.updateById(deviceRunTime));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除设备运行时间", notes = "参数： id")
	@SysLog("删除设备运行时间")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_deviceruntime_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(deviceRunTimeService.removeById(id));
	}

}
