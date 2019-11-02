package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.junfeng.platform.dc.api.vo.DeviceConfigBatchVo;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.junfeng.platform.dc.util.DeviceUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceConfig;
import com.junfeng.platform.dc.service.DeviceConfigService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备配置表
 *
 * @author hanyx
 * @date 2019-10-10 16:37:14
 */
@Api(tags = {"设备配置表"})
@RestController
@AllArgsConstructor
@RequestMapping("/deviceconfig")
public class DeviceConfigController {

	private final DeviceConfigService deviceConfigService;

	private final DeviceService deviceService;

	/**
	 * 简单分页查询
	 *
	 * @param page         分页对象
	 * @param deviceConfig 设备配置
	 * @return 分页设备配置
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 设备配置表")
	@GetMapping("/page")
	public R<IPage<DeviceConfig>> getDeviceConfigPage(Page<DeviceConfig> page, DeviceConfig deviceConfig) {
		return R.ok(deviceConfigService.getDeviceConfigPage(page, deviceConfig));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id 设备id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceConfig> getById(@PathVariable("id") Long id) {
		return R.ok(deviceConfigService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceConfig 设备配置
	 * @return R
	 */
	@ApiOperation(value = "新增设备配置", notes = "参数： deviceConfig")
	@SysLog("新增设备配置")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceconfig_add')")
	public R save(@RequestBody @Validated DeviceConfig deviceConfig) {
		Device device = deviceService.getById(deviceConfig.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 一个设备只有一条（未被逻辑删除）记录
		if (deviceConfigService.isExist(deviceConfig.getDeviceId())) {
			return R.failed("该设备已有配置，不能新增，请修改配置");
		}

		deviceConfig.setCreateTime(LocalDateTime.now());
		deviceConfig.setUpdateTime(LocalDateTime.now());
		deviceConfig.setCreateBy(ContextHolderUtil.getUsername());
		deviceConfig.setUpdateBy(ContextHolderUtil.getUsername());
		deviceConfig.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return R.ok(deviceConfigService.save(deviceConfig));
	}

	/**
	 * 功能描述: 按设备类型批量新增记录
	 *
	 * @param configVo 设备配置
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/10 17:18
	 */
	@ApiOperation(value = "按设备类型批量新增设备配置", notes = "参数：configVo")
	@SysLog("批量新增设备配置")
	@PostMapping("/batch")
	@PreAuthorize("@pms.hasPermission('dc_deviceconfig_add')")
	public R<Boolean> saveBatchByDevType(@RequestBody @Validated DeviceConfigBatchVo configVo) {
		// 根据设备类型查找其中没有配置的设备id列表
		List<Long> deviceIdList = deviceService.getNonConfigIdListByDeviceType(configVo.getDevTypeCode());

		if (CheckUtils.isEmpty(deviceIdList)) {
			return R.failed("没有该型设备");
		}

		List<DeviceConfig> deviceConfigList = Lists.newArrayList();
		deviceIdList.forEach(item -> deviceConfigList.add(DeviceUtils.createDeviceConfig(item, configVo.getConfig())));

		return R.ok(deviceConfigService.saveBatch(deviceConfigList));
	}

	/**
	 * 修改记录
	 *
	 * @param deviceConfig 设备配置
	 * @return R
	 */
	@ApiOperation(value = "修改设备配置", notes = "参数： deviceConfig")
	@SysLog("修改设备配置")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceconfig_edit')")
	public R update(@RequestBody @Validated DeviceConfig deviceConfig) {
		Device device = deviceService.getById(deviceConfig.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备或者该型设备都已有配置");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(deviceConfig.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		deviceConfig.setUpdateTime(LocalDateTime.now());
		deviceConfig.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceConfigService.updateById(deviceConfig));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id 设备id
	 * @return R
	 */
	@ApiOperation(value = "删除设备配置", notes = "参数： id")
	@SysLog("删除设备配置")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_deviceconfig_del')")
	public R removeById(@PathVariable Long id) {
		// 逻辑删除
		DeviceConfig deviceConfig = deviceConfigService.getById(id);
		if (CheckUtils.isEmpty(deviceConfig)) {
			return R.failed("不存在该记录");
		}

		if (CheckUtils.isLogicDelete(deviceConfig.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		deviceConfig.setDelFlag(DeviceCenterConstant.DEL_FLAG_TRUE);
		deviceConfig.setUpdateTime(LocalDateTime.now());
		deviceConfig.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceConfigService.updateById(deviceConfig));
	}

}
