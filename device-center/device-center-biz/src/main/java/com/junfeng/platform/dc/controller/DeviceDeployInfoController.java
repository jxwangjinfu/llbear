package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.entity.DeviceArea;
import com.junfeng.platform.dc.service.DeviceAreaService;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.junfeng.platform.dc.util.ParseUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceDeployInfo;
import com.junfeng.platform.dc.service.DeviceDeployInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

/**
 * 设备部署信息
 *
 * @author hanyx
 * @date 2019-10-25 11:07:42
 */
@Api(tags = {"设备部署信息"})
@RestController
@AllArgsConstructor
@RequestMapping("/devicedeployinfo")
public class DeviceDeployInfoController {

	private final DeviceDeployInfoService deviceDeployInfoService;
	private final DeviceService deviceService;
	private final DeviceAreaService deviceAreaService;

	/**
	 * 简单分页查询
	 *
	 * @param page             分页对象
	 * @param deviceDeployInfo 设备部署信息
	 * @return 分页列表
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 设备部署信息")
	@GetMapping("/page")
	public R<IPage<DeviceDeployInfo>> getDeviceDeployInfoPage(Page<DeviceDeployInfo> page, DeviceDeployInfo deviceDeployInfo) {
		return R.ok(deviceDeployInfoService.getDeviceDeployInfoPage(page, deviceDeployInfo));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceDeployInfo> getById(@PathVariable("id") Long id) {
		return R.ok(deviceDeployInfoService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceDeployInfo 设备部署信息
	 * @return R
	 */
	@ApiOperation(value = "新增设备部署信息", notes = "参数： deviceDeployInfo")
	@SysLog("新增设备部署信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_devicedeployinfo_add')")
	public R save(@RequestBody @Validated DeviceDeployInfo deviceDeployInfo) {
		Device device = deviceService.getById(deviceDeployInfo.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此id的设备");
		}

		// 一个设备只有一条（未被逻辑删除）记录
		if (deviceDeployInfoService.isExist(deviceDeployInfo.getDeviceId())) {
			return R.failed("该设备已有部署信息，不能新增，请修改");
		}

		// 判断该部署区域是否存在，不存在，则不允许增加
		DeviceArea deviceArea = deviceAreaService.getDeviceAreaByCode(ParseUtils.parseInt(deviceDeployInfo.getDeployAreaCode()));
		if (CheckUtils.isEmpty(deviceArea)) {
			return R.failed("不存在此区域");
		}

		deviceDeployInfo.setDeployArea(deviceArea.getName());
		deviceDeployInfo.setCreateTime(LocalDateTime.now());
		deviceDeployInfo.setUpdateTime(LocalDateTime.now());
		deviceDeployInfo.setCreateBy(ContextHolderUtil.getUsername());
		deviceDeployInfo.setUpdateBy(ContextHolderUtil.getUsername());
		deviceDeployInfo.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);
		return R.ok(deviceDeployInfoService.save(deviceDeployInfo));
	}

	/**
	 * 修改记录
	 *
	 * @param deviceDeployInfo 设备部署信息
	 * @return R
	 */
	@ApiOperation(value = "修改设备部署信息", notes = "参数： deviceDeployInfo")
	@SysLog("修改设备部署信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_devicedeployinfo_edit')")
	public R update(@RequestBody @Validated DeviceDeployInfo deviceDeployInfo) {
		Device device = deviceService.getById(deviceDeployInfo.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此id的设备");
		}

		deviceDeployInfo.setUpdateTime(LocalDateTime.now());
		deviceDeployInfo.setUpdateBy(ContextHolderUtil.getUsername());
		return R.ok(deviceDeployInfoService.updateById(deviceDeployInfo));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除设备部署信息", notes = "参数： id")
	@SysLog("删除设备部署信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_devicedeployinfo_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(deviceDeployInfoService.removeById(id));
	}

}
