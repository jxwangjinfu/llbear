package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.api.vo.DeviceQueryVo;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceAppVersion;
import com.junfeng.platform.dc.service.DeviceAppVersionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备APP版本管理
 *
 * @author hanyx
 * @date 2019-10-09 10:53:18
 */
@Api(tags = {"设备APP版本管理"})
@RestController
@AllArgsConstructor
@RequestMapping("/deviceappversion")
public class DeviceAppVersionController {

	private final DeviceAppVersionService deviceAppVersionService;

	private final DeviceService deviceService;

	/**
	 * 简单分页查询
	 *
	 * @param page             分页对象
	 * @param deviceAppVersion 设备APP版本
	 * @return 设备APP版本列表
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 设备APP版本")
	@GetMapping("/page")
	public R<IPage<DeviceAppVersion>> getDeviceAppVersionPage(Page<DeviceAppVersion> page, DeviceAppVersion deviceAppVersion) {
		return R.ok(deviceAppVersionService.getDeviceAppVersionPage(page, deviceAppVersion));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceAppVersion> getById(@PathVariable("id") Long id) {
		return R.ok(deviceAppVersionService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceAppVersion 设备APP版本
	 * @return R
	 */
	@ApiOperation(value = "新增设备APP版本", notes = "参数： deviceAppVersion")
	@SysLog("新增设备APP版本")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceappversion_add')")
	public R save(@RequestBody @Validated DeviceAppVersion deviceAppVersion) {
		R r = validate(deviceAppVersion);
		if (r.getCode() != R.ok().getCode()) {
			return r;
		}

		// 不能新增小于等于库中已有的版本
		DeviceAppVersion appVersion = deviceAppVersionService.getLatestApp(deviceAppVersion.getDeviceTypeCode(), DeviceCenterConstant.DEFAULT_LONG);
		if (!CheckUtils.isEmpty(appVersion) && (appVersion.getVersionCode() >= deviceAppVersion.getVersionCode())) {
			return R.failed("不能新增小于等于已有版本号的版本");
		}

		deviceAppVersion.setMd5(DigestUtils.md5DigestAsHex(deviceAppVersion.getFileName().getBytes()));
		deviceAppVersion.setCreateTime(LocalDateTime.now());
		deviceAppVersion.setUpdateTime(LocalDateTime.now());
		deviceAppVersion.setCreateBy(ContextHolderUtil.getUsername());
		deviceAppVersion.setUpdateBy(ContextHolderUtil.getUsername());
		deviceAppVersion.setState(DeviceCenterConstant.STATE_UNPUBLISHED);
		deviceAppVersion.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return R.ok(deviceAppVersionService.save(deviceAppVersion));
	}

	/**
	 * 功能描述: 验证合法性
	 *
	 * @param deviceAppVersion 设备App版本信息
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/16 16:59
	 */
	private R validate(DeviceAppVersion deviceAppVersion) {
		if (!CheckUtils.isCode(deviceAppVersion.getDeviceTypeCode())) {
			return R.failed("设备类型编码不合法");
		}

		DeviceQueryVo queryVo = new DeviceQueryVo();
		queryVo.setDeviceTypeCode(deviceAppVersion.getDeviceTypeCode());
		List<Device> deviceList = deviceService.getDevice(queryVo);
		if (CheckUtils.isEmpty(deviceList) || (deviceList.size() <= 0)) {
			return R.failed("不存在此设备类型");
		}

		return R.ok();
	}

	/**
	 * 修改记录
	 *
	 * @param deviceAppVersion 设备APP版本
	 * @return R
	 */
	@ApiOperation(value = "修改设备APP版本", notes = "参数： deviceAppVersion")
	@SysLog("修改设备APP版本")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceappversion_edit')")
	public R update(@RequestBody DeviceAppVersion deviceAppVersion) {
		R r = validate(deviceAppVersion);
		if (r.getCode() != R.ok().getCode()) {
			return r;
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(deviceAppVersion.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		deviceAppVersion.setUpdateTime(LocalDateTime.now());
		deviceAppVersion.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceAppVersionService.updateById(deviceAppVersion));
	}

	/**
	 * 功能描述: 发布设备App版本
	 *
	 * @param id 设备版本id
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/9 14:48
	 */
	@ApiOperation(value = "发布设备APP版本", notes = "参数： id")
	@SysLog("发布设备APP版本")
	@PutMapping("/{id}/publish")
	public R<Boolean> publishDeviceApp(@PathVariable Long id) {
		DeviceAppVersion appVersion = deviceAppVersionService.getById(id);
		if (CheckUtils.isEmpty(appVersion)) {
			return R.failed("不存在此版本id");
		}

		// 已逻辑删除的不能发布
		if (CheckUtils.isLogicDelete(appVersion.getDelFlag())) {
			return R.failed("此记录已删除");
		}

		// 已发布的不能发布
		if (appVersion.getState() == DeviceCenterConstant.STATE_PUBLISHED) {
			return R.failed("此版本已发布");
		}

		DeviceAppVersion publishedAppVersion = deviceAppVersionService.getLatestApp(appVersion.getDeviceTypeCode(), DeviceCenterConstant.STATE_PUBLISHED);
		if (!CheckUtils.isEmpty(publishedAppVersion) && (publishedAppVersion.getVersionCode() >= appVersion.getVersionCode())) {
			return R.failed("不能发布小于等于已发布版本号的版本");
		}

		return R.ok(deviceAppVersionService.publishDeviceApp(appVersion));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除设备APP版本", notes = "参数： id")
	@SysLog("删除设备APP版本")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_deviceappversion_del')")
	public R removeById(@PathVariable Long id) {
		// 逻辑删除
		DeviceAppVersion deviceAppVersion = deviceAppVersionService.getById(id);
		if (CheckUtils.isLogicDelete(deviceAppVersion.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		deviceAppVersion.setDelFlag(DeviceCenterConstant.DEL_FLAG_TRUE);
		deviceAppVersion.setUpdateTime(LocalDateTime.now());
		deviceAppVersion.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceAppVersionService.updateById(deviceAppVersion));
	}

}
