package com.junfeng.platform.dc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.util.CheckUtils;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.vo.DictVo;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.junfeng.platform.dc.entity.DeviceArea;
import com.junfeng.platform.dc.service.DeviceAreaService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 设备区域表
 *
 * @author hanyx
 * @date 2019-10-31 13:59:24
 */
@Api(tags = {"设备区域表"})
@RestController
@AllArgsConstructor
@RequestMapping("/devicearea")
public class DeviceAreaController {

	private final DeviceAreaService deviceAreaService;

	/**
	 * 简单分页查询
	 *
	 * @param page       分页对象
	 * @param deviceArea 设备区域表
	 * @return 分页列表
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 设备区域表")
	@GetMapping("/page")
	public R<IPage<DeviceArea>> getDeviceAreaPage(Page<DeviceArea> page, DeviceArea deviceArea) {
		return R.ok(deviceAreaService.getDeviceAreaPage(page, deviceArea));
	}


	/**
	 * 功能描述: 查找区域类型列表
	 *
	 * @param type 区域类型
	 * @return java.util.List<com.pig4cloud.pig.common.core.vo.DictVo>
	 * @author: hanyx
	 * @createTime: 2019/11/1 15:25
	 */
	@ApiOperation(value = "查找区域类型列表", notes = "参数： type 区域类型")
	@GetMapping("/dict/{type}")
	public R<List<DictVo>> getDictList(@PathVariable Integer type) {
		if (CheckUtils.isEmpty(type)) {
			return R.failed("类型为空");
		}

		int areaType = type;
		if ((type > DeviceCenterConstant.AREA_TYPE_DISTRICT) || (type < DeviceCenterConstant.AREA_TYPE_COUNTRY)) {
			areaType = DeviceCenterConstant.AREA_TYPE_DISTRICT;
		}

		return R.ok(deviceAreaService.getDictList(areaType));
	}

	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceArea> getById(@PathVariable("id") String id) {
		return R.ok(deviceAreaService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceArea 设备部署区域
	 * @return R
	 */
	@ApiOperation(value = "新增设备区域表", notes = "参数： deviceArea")
	@SysLog("新增设备区域表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_devicearea_add')")
	public R save(@RequestBody @Validated DeviceArea deviceArea) {
		DeviceArea parentArea = deviceAreaService.getById(deviceArea.getParentId());
		if (CheckUtils.isEmpty(parentArea)) {
			return R.failed("此父id不存在");
		}

		if (deviceAreaService.isCodeExist(deviceArea.getCode())) {
			return R.failed("此行政编码的区域已存在");
		}
		deviceArea.setId(UUID.randomUUID().toString());
		deviceArea.setParentIds(parentArea.getParentIds() + "," + deviceArea.getParentId());
		deviceArea.setSort(30);

		return R.ok(deviceAreaService.save(deviceArea));
	}

	/**
	 * 修改记录
	 *
	 * @param deviceArea 设备部署区域
	 * @return R
	 */
	@ApiOperation(value = "修改设备区域表", notes = "参数： deviceArea")
	@SysLog("修改设备区域表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_devicearea_edit')")
	public R update(@RequestBody @Validated DeviceArea deviceArea) {
		if (CheckUtils.isEmpty(deviceArea.getId())) {
			return R.failed("id为空");
		}

		DeviceArea area = deviceAreaService.getById(deviceArea.getId());
		if (CheckUtils.isEmpty(area)) {
			return R.failed("此id不存在");
		}

		deviceArea.setUpdateTime(LocalDateTime.now());
		return R.ok(deviceAreaService.updateById(deviceArea));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除设备区域表", notes = "参数： id")
	@SysLog("删除设备区域表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_devicearea_del')")
	public R removeById(@PathVariable String id) {
		return R.ok(deviceAreaService.removeById(id));
	}

}
