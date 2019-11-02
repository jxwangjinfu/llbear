package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.junfeng.platform.dc.api.vo.DeviceAdvertisementBatchVo;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceAdvertisement;
import com.junfeng.platform.dc.service.DeviceAdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备广告表
 *
 * @author hanyx
 * @date 2019-10-11 15:00:24
 */
@Api(tags = {"设备广告表"})
@RestController
@AllArgsConstructor
@RequestMapping("/deviceadvertisement")
public class DeviceAdvertisementController {

	private final DeviceAdvertisementService deviceAdvertisementService;

	private final DeviceService deviceService;

	/**
	 * 简单分页查询
	 *
	 * @param page                分页对象
	 * @param deviceAdvertisement 设备广告
	 * @return 分页设备广告
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 设备广告表")
	@GetMapping("/page")
	public R<IPage<DeviceAdvertisement>> getDeviceAdvertisementPage(Page<DeviceAdvertisement> page, DeviceAdvertisement deviceAdvertisement) {
		return R.ok(deviceAdvertisementService.getDeviceAdvertisementPage(page, deviceAdvertisement));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceAdvertisement> getById(@PathVariable("id") Long id) {
		return R.ok(deviceAdvertisementService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceAdvertisement 设备广告
	 * @return R
	 */
	@ApiOperation(value = "新增设备广告", notes = "参数： deviceAdvertisement")
	@SysLog("新增设备广告")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceadvertisement_add')")
	public R save(@RequestBody @Validated DeviceAdvertisement deviceAdvertisement) {
		Device device = deviceService.getById(deviceAdvertisement.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此id的设备");
		}

		// 一个设备只有一条（未被逻辑删除）记录
		if (deviceAdvertisementService.isExist(deviceAdvertisement.getDeviceId())) {
			return R.failed("该设备已有广告，不能新增，请修改广告");
		}

		deviceAdvertisement.setCreateTime(LocalDateTime.now());
		deviceAdvertisement.setUpdateTime(LocalDateTime.now());
		deviceAdvertisement.setCreateBy(ContextHolderUtil.getUsername());
		deviceAdvertisement.setUpdateBy(ContextHolderUtil.getUsername());
		deviceAdvertisement.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return R.ok(deviceAdvertisementService.save(deviceAdvertisement));
	}

	/**
	 * 功能描述: 按设备类型批量新增设备广告
	 *
	 * @param advertisementBatchVo 广告配置
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/11 16:52
	 */
	@ApiOperation(value = "按设备类型批量新增设备广告", notes = "参数： advertisementBatchVo")
	@SysLog("按设备类型批量新增设备广告")
	@PostMapping("/batch")
	@PreAuthorize("@pms.hasPermission('dc_deviceadvertisement_add')")
	public R saveBatchByDevType(@RequestBody @Validated DeviceAdvertisementBatchVo advertisementBatchVo) {
		// 根据设备类型查找其中没有广告的设备id列表
		List<Long> deviceIdList = deviceService.getNonAdIdListByDeviceType(advertisementBatchVo.getDevTypeCode());

		if (CheckUtils.isEmpty(deviceIdList)) {
			return R.failed("没有该型设备或者该型设备都已有广告");
		}

		List<DeviceAdvertisement> advertisementList = Lists.newArrayList();
		deviceIdList.forEach(item -> {
			DeviceAdvertisement advertisement = new DeviceAdvertisement();
			advertisement.setDeviceId(item);
			advertisement.setAdvertisementUrl(advertisementBatchVo.getAdvertisementUrl());
			advertisement.setCreateTime(LocalDateTime.now());
			advertisement.setUpdateTime(LocalDateTime.now());
			advertisement.setCreateBy(ContextHolderUtil.getUsername());
			advertisement.setUpdateBy(ContextHolderUtil.getUsername());
			advertisement.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

			advertisementList.add(advertisement);
		});

		return R.ok(deviceAdvertisementService.saveBatch(advertisementList));
	}

	/**
	 * 修改记录
	 *
	 * @param deviceAdvertisement 设备广告
	 * @return R
	 */
	@ApiOperation(value = "修改设备广告", notes = "参数： deviceAdvertisement")
	@SysLog("修改设备广告")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_deviceadvertisement_edit')")
	public R update(@RequestBody @Validated DeviceAdvertisement deviceAdvertisement) {
		Device device = deviceService.getById(deviceAdvertisement.getDeviceId());
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在此id的设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(deviceAdvertisement.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		deviceAdvertisement.setUpdateTime(LocalDateTime.now());
		deviceAdvertisement.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceAdvertisementService.updateById(deviceAdvertisement));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除设备广告", notes = "参数： id")
	@SysLog("删除设备广告")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_deviceadvertisement_del')")
	public R removeById(@PathVariable Long id) {
		// 逻辑删除
		DeviceAdvertisement advertisement = deviceAdvertisementService.getById(id);
		if (CheckUtils.isEmpty(advertisement)) {
			return R.failed("不存在该ID的记录");
		}

		if (CheckUtils.isLogicDelete(advertisement.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		advertisement.setDelFlag(DeviceCenterConstant.DEL_FLAG_TRUE);
		advertisement.setUpdateTime(LocalDateTime.now());
		advertisement.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceAdvertisementService.updateById(advertisement));
	}

}
