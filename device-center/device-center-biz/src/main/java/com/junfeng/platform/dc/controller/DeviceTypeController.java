package com.junfeng.platform.dc.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceType;
import com.junfeng.platform.dc.service.DeviceTypeService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.vo.DictVo;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 设备类型
 *
 * @author fuh
 * @date 2019-08-27 15:03:12
 */
@Api(tags = {"设备类型"})
@RestController
@AllArgsConstructor
@RequestMapping("/devicetype")
public class DeviceTypeController {

	private final DeviceTypeService deviceTypeService;

	/**
	 * 简单分页查询
	 *
	 * @param page       分页对象
	 * @param deviceType 设备类型
	 * @return 分页设备类型
	 */
	@ApiOperation(value = "简单分页查询", notes = "参数： page 分页对象 和 deviceType")
	@GetMapping("/page")
	public R<IPage<DeviceType>> getDeviceTypePage(Page<DeviceType> page, DeviceType deviceType) {
		return R.ok(deviceTypeService.getDeviceTypePage(page, deviceType));
	}

	@GetMapping("/dict")
	public R<List<DictVo>> dict() {
		return R.ok(deviceTypeService.getDictList());
	}

	/**
	 * 总数
	 *
	 * @return
	 * @author:fuh
	 * @createTime:2019年9月23日 上午11:02:29
	 */
	@ApiOperation(value = "获取设备类型总数", notes = "")
	@GetMapping("/total")
	public R<Integer> total() {
		return R.ok(deviceTypeService.count());
	}

	/**
	 * 通过id查询单条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<DeviceType> getById(@PathVariable("id") Long id) {
		return R.ok(deviceTypeService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param deviceType 设备类型
	 * @return R
	 */
	@ApiOperation(value = "新增设备类型", notes = "参数： deviceType")
	@SysLog("新增设备类型")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_devicetype_add')")
	public R<Boolean> save(@RequestBody @Valid DeviceType deviceType) {
		if (deviceTypeService.isExist(deviceType.getCode())) {
			return R.failed("该设备编码已存在");
		}

		deviceType.setCreateTime(LocalDateTime.now());
		deviceType.setUpdateTime(LocalDateTime.now());
		deviceType.setCreateBy(ContextHolderUtil.getUsername());
		deviceType.setUpdateBy(ContextHolderUtil.getUsername());
		deviceType.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return R.ok(deviceTypeService.save(deviceType));
	}

	/**
	 * 修改记录
	 *
	 * @param deviceType 设备类型
	 * @return R
	 */
	@ApiOperation(value = "修改设备类型", notes = "参数： deviceType")
	@SysLog("修改设备类型")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_devicetype_edit')")
	public R<Boolean> update(@RequestBody DeviceType deviceType) {
		// 已逻辑删除的不处理
		if (deviceType.getDelFlag().equals(DeviceCenterConstant.DEL_FLAG_TRUE)) {
			return R.failed("该记录已被删除");
		}

		deviceType.setUpdateTime(LocalDateTime.now());
		deviceType.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceTypeService.updateById(deviceType));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "删除设备类型", notes = "参数： id")
	@SysLog("删除设备类型")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_devicetype_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		// 逻辑删除
		DeviceType deviceType = deviceTypeService.getById(id);
		if (deviceType.getDelFlag().equals(DeviceCenterConstant.DEL_FLAG_TRUE)) {
			return R.failed("该记录已被删除");
		}

		deviceType.setDelFlag(DeviceCenterConstant.DEL_FLAG_TRUE);
		deviceType.setUpdateTime(LocalDateTime.now());
		deviceType.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceTypeService.updateById(deviceType));
	}

}
