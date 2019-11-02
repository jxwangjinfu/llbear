package com.junfeng.platform.dc.controller;

import com.junfeng.platform.dc.api.result.DevicePageResult;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.*;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.*;
import com.junfeng.platform.dc.service.*;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import com.junfeng.platform.dc.util.DeviceUtils;
import com.junfeng.platform.dc.util.MappingUtils;
import com.pig4cloud.pig.common.security.annotation.Inner;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 设备表
 *
 * @author fuh
 * @date 2019-09-16 16:17:21
 */
@Api(tags = {"设备"})
@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {

	private final DeviceService deviceService;

	private final DeviceTypeService deviceTypeService;

	private final DeviceIssueService deviceIssueService;

	private final DeviceConfigService deviceConfigService;

	private final DeviceAppVersionService deviceAppVersionService;

	private final DeviceAdvertisementService deviceAdvertisementService;

	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param device 设备表
	 * @return 设备分页列表
	 */
	@GetMapping("/page")
	public R<IPage<Device>> getDevicePage(Page<Device> page, Device device) {
		return R.ok(deviceService.getDevicePage(page, device));
	}

	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.dc.api.result.DevicePageResult>
	 * @author: hanyx
	 * @createTime: 2019/10/28 14:03
	 */
	@Inner
	@PostMapping("/page/deploy")
	public R<DevicePageResult> getDevicePageByDeployInfo(@RequestBody DeviceDeployVo deviceDeployVo) {
		IPage<DeviceResult> page = deviceService.getDevicePageByDeployInfo(deviceDeployVo);

		DevicePageResult devicePageResult = new DevicePageResult();
		devicePageResult.setRecords(page.getRecords());
		devicePageResult.setTotal(page.getTotal());
		devicePageResult.setCurrent(page.getCurrent());
		return R.ok(devicePageResult);
	}

	/**
	 * 功能描述: 按设备及其部署信息查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/29 9:34
	 */
	@Inner
	@PostMapping("/list/deploy")
	public R<List<DeviceResult>> getDeviceListByDeployInfo(@RequestBody DeviceDeployVo deviceDeployVo) {
		List<Device> deviceList = deviceService.getDeviceListByDeployInfo(deviceDeployVo);
		if (CheckUtils.isEmpty(deviceList)) {
			return R.failed("查询结果为空");
		}

		List<DeviceResult> deviceResultList = deviceList.stream().map(device -> {
			DeviceResult deviceResult = new DeviceResult();
			BeanUtils.copyProperties(device, deviceResult);
			return deviceResult;
		}).collect(Collectors.toList());
		return R.ok(deviceResultList);
	}

	/**
	 * 查询某设备类型分页列表
	 *
	 * @param page      分页对象
	 * @param devTypeVo 设备类型vo
	 * @return 该设备分页列表
	 */
	@ApiOperation(value = "按设备类型分页查询", notes = "参数： page 分页对象 和 devTypeVo")
	@GetMapping("/page/device_type")
	public R<IPage<Device>> getDevicePageByDevTypeCode(Page<Device> page, @Validated DeviceTypeVo devTypeVo) {
		return R.ok(deviceService.getDevicePageByDevTypeCode(page, devTypeVo));
	}

	/**
	 * 查询某设备平台分页列表
	 *
	 * @param page        分页对象
	 * @param devPlatform 设备平台
	 * @return 该设备分页列表
	 */
	@ApiOperation(value = "按设备平台分页查询", notes = "参数： page 分页对象 和 devPlatform")
	@GetMapping("/page/device_platform/{devPlatform}")
	public R<IPage<Device>> getDevicePageByDevPlatform(Page<Device> page, @PathVariable String devPlatform) {
		return R.ok(deviceService.getDevicePageByDevPlatform(page, devPlatform));
	}

	/**
	 * 通过id查询单条记录
	 *
	 * @param id 设备id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询单条记录", notes = "参数： id")
	@GetMapping("/{id}")
	public R<Device> getById(@PathVariable("id") Long id) {
		return R.ok(deviceService.getById(id));
	}

	/**
	 * 通过设备编码查询单条记录
	 *
	 * @param devCode 设备号
	 * @return R
	 */
	@ApiOperation(value = "通过设备编码查询单条记录", notes = "参数： devCode")
	@GetMapping("/device_code/{devCode}")
	public R<Device> getDeviceByDeviceCode(@PathVariable String devCode) {
		return R.ok(deviceService.getDeviceByDeviceCode(devCode));
	}

	/**
	 * 查询版本号为version的机型数量
	 *
	 * @param versionCode 版本号
	 * @return 设备数量
	 */
	@ApiOperation(value = "查询某版本的设备数量", notes = "参数： versionCode")
	@GetMapping("/device_version/{versionCode}")
	public R<Integer> getDeviceCountByVersion(@PathVariable int versionCode) {
		return R.ok(deviceService.getDeviceCountByVersion(versionCode));
	}

	/**
	 * 查找设备数量
	 *
	 * @param deviceVo 待查找设备vo
	 * @return 设备数量
	 */
	@ApiOperation(value = "根据条件查询设备数量", notes = "参数： deviceVo")
	@GetMapping("/device_count")
	public R<Integer> getDeviceCount(DeviceCountVo deviceVo) {
		return R.ok(deviceService.getDeviceCount(deviceVo));
	}

	/**
	 * 功能描述: 查询该型设备的最新版本信息
	 *
	 * @param id 设备id
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.dc.entity.DeviceAppVersion>
	 * @author: hanyx
	 * @createTime: 2019/10/9 15:48
	 */
	@ApiOperation(value = "获取设备App最新版本信息", notes = "参数： id")
	@GetMapping("/{id}/version")
	public R<DeviceAppVersion> getLatestVersion(@PathVariable Long id) {
		Device device = deviceService.getById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		return R.ok(deviceAppVersionService.getLatestApp(device.getDeviceTypeCode(), DeviceCenterConstant.STATE_PUBLISHED));
	}

	/**
	 * 功能描述: 查询该设备的配置信息
	 *
	 * @param id 设备id
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.dc.entity.DeviceConfig>
	 * @author: hanyx
	 * @createTime: 2019/10/10 17:57
	 */
	@ApiOperation(value = "获取设备配置", notes = "参数： id")
	@GetMapping("/{id}/config")
	public R<DeviceConfig> getDeviceConfig(@PathVariable Long id) {
		Device device = deviceService.getById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		return R.ok(deviceConfigService.getDeviceConfigByDeviceId(device.getId()));
	}

	/**
	 * 功能描述: 查询该设备的广告信息
	 *
	 * @param id 设备id
	 * @return com.pig4cloud.pig.common.core.util.R<com.junfeng.platform.dc.entity.DeviceAdvertisement>
	 * @author: hanyx
	 * @createTime: 2019/10/11 17:27
	 */
	@ApiOperation(value = "获取设备广告", notes = "参数： id")
	@GetMapping("/{id}/advertisement")
	public R<DeviceAdvertisement> getDeviceAdvertisement(@PathVariable Long id) {
		Device device = deviceService.getById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		return R.ok(deviceAdvertisementService.getAdvertisementByDeviceId(device.getId()));
	}

	/**
	 * 功能描述: 获取设备故障
	 *
	 * @param id 设备id
	 * @return com.pig4cloud.pig.common.core.util.R<java.util.List < com.junfeng.platform.dc.entity.DeviceIssue>>
	 * @author: hanyx
	 * @createTime: 2019/10/17 14:58
	 */
	@ApiOperation(value = "查看设备故障", notes = "参数： id")
	@GetMapping("/{id}/issue")
	public R<List<DeviceIssue>> getDeviceIssue(@PathVariable Long id) {
		Device device = deviceService.getById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		return R.ok(deviceIssueService.getDeviceIssueListByDeviceId(id));
	}

	/**
	 * 新增记录
	 *
	 * @param device 设备
	 * @return R
	 */
	@ApiOperation(value = "新增设备", notes = "参数： device")
	@SysLog("新增设备")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('dc_device_add')")
	public R<Boolean> save(@RequestBody @Validated Device device) {
		// 检查设备类型是否存在
		if (!deviceTypeService.isExist(device.getDeviceTypeCode())) {
			return R.failed("该设备类型不存在，请先创建设备类型");
		}

		if (deviceService.isExist(device.getDevCode())) {
			return R.failed("该设备编号已存在");
		}

		device.setCreateTime(LocalDateTime.now());
		device.setUpdateTime(LocalDateTime.now());
		device.setCreateBy(ContextHolderUtil.getUsername());
		device.setUpdateBy(ContextHolderUtil.getUsername());
		device.setIsOnline(DeviceCenterConstant.STATE_OFFLINE);
		device.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return R.ok(deviceService.save(device));
	}

	/**
	 * 功能描述: 上报设备故障
	 *
	 * @param id      设备id
	 * @param issueVo 故障信息
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/17 10:35
	 */
	@ApiOperation(value = "提交设备故障", notes = "参数： id 和 issueVo")
	@SysLog("提交设备故障")
	@PostMapping("/{id}/issue")
	public R saveDeviceIssue(@PathVariable Long id, @Validated DeviceIssueVo issueVo) {
		// 数据验证
		if (id.longValue() != issueVo.getDeviceId().longValue()) {
			return R.failed("设备id不一致");
		}
		DeviceIssue deviceIssue = MappingUtils.mapping(issueVo, DeviceIssue.class);
		R r = DeviceUtils.validateDeviceIssue(deviceIssue);
		if (r.getCode() != R.ok().getCode()) {
			return r;
		}

		Device device = deviceService.getDeviceByIssue(deviceIssue);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		if (deviceIssueService.isExist(deviceIssue)) {
			return R.failed("该记录已提交过");
		}

		return R.ok(deviceIssueService.saveDeviceIssue(deviceIssue));
	}

	/**
	 * 修改记录
	 *
	 * @param device 设备
	 * @return R
	 */
	@ApiOperation(value = "修改设备", notes = "参数： device")
	@SysLog("修改设备")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('dc_device_edit')")
	public R<Boolean> update(@RequestBody Device device) {
		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		device.setUpdateTime(LocalDateTime.now());
		device.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceService.updateById(device));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id 设备id
	 * @return R
	 */
	@ApiOperation(value = "删除设备", notes = "参数： id")
	@SysLog("删除设备")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('dc_device_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		// 逻辑删除
		Device device = deviceService.getById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		device.setDelFlag(DeviceCenterConstant.DEL_FLAG_TRUE);
		device.setUpdateTime(LocalDateTime.now());
		device.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceService.updateById(device));
	}

	/**
	 * 功能描述: 心跳接口
	 *
	 * @param id 设备id
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/31 11:27
	 */
	@ApiOperation(value = "心跳接口", notes = "参数： id")
	@PutMapping("/{id}/heartbeat")
	public R heartbeat(@PathVariable Long id) {
		// TODO 心跳逻辑
		Device device = deviceService.getDeviceById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		return R.ok(deviceService.updateDeviceOnlineState(device));
	}

	/**
	 * 功能描述: 上报设备版本信息
	 *
	 * @param id 设备id
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/8 16:31
	 */
	@ApiOperation(value = "上报设备版本", notes = "参数： id 和 versionVo")
	@SysLog("上报设备版本")
	@PutMapping("/{id}/version")
	public R<Boolean> updateDeviceVersion(@PathVariable Long id, @Validated DeviceVersionVo vo) {
		// 不能新增小于等于库中已有的版本
		DeviceAppVersion appVersion = deviceAppVersionService.getLatestApp(vo.getDeviceTypeCode(), DeviceCenterConstant.STATE_PUBLISHED);
		if (CheckUtils.isEmpty(appVersion)) {
			return R.failed("该型设备尚未发布版本");
		}

		if (appVersion.getVersionCode() < vo.getCurrentVersionCode()) {
			return R.failed("上传版本号不能大于当前已发布的版本号");
		}

		Device device = deviceService.getDeviceById(id);
		boolean isExist = device.getDevCode().equals(vo.getDevCode()) && device.getDeviceTypeCode().equals(vo.getDeviceTypeCode());
		if (CheckUtils.isEmpty(device) || !isExist) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		device.setCurrentVersionCode(vo.getCurrentVersionCode());
		if (!CheckUtils.isEmpty(vo.getCurrentVersionName())) {
			device.setCurrentVersionName(vo.getCurrentVersionName());
		}
		device.setUpdateTime(LocalDateTime.now());
		device.setUpdateBy(ContextHolderUtil.getUsername());

		return R.ok(deviceService.updateById(device));
	}

	/**
	 * 功能描述:上报设备配置
	 *
	 * @param id       设备id
	 * @param configVo 配置
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/11 9:51
	 */
	@ApiOperation(value = "上报设备配置", notes = "参数： id 和 configVo")
	@SysLog("上报设备配置")
	@PutMapping("/{id}/config")
	public R<Boolean> updateDeviceConfig(@PathVariable Long id, @Validated DeviceConfigVo configVo) {
		Device device = deviceService.getById(id);
		if (CheckUtils.isEmpty(device)) {
			return R.failed("不存在该设备");
		}

		// 已逻辑删除的不处理
		if (CheckUtils.isLogicDelete(device.getDelFlag())) {
			return R.failed("该记录已被删除");
		}

		DeviceConfig deviceConfig = deviceConfigService.getDeviceConfigByDeviceId(id);
		if (deviceConfig != null) {
			deviceConfig.setConfig(configVo.getConfig());
			deviceConfig.setUpdateTime(LocalDateTime.now());
			deviceConfig.setUpdateBy(ContextHolderUtil.getUsername());
			return R.ok(deviceConfigService.updateById(deviceConfig));
		} else {
			return R.ok(deviceConfigService.save(DeviceUtils.createDeviceConfig(id, configVo.getConfig())));
		}
	}

	/**
	 * 功能描述: 定时更新设备状态任务的回调
	 *
	 * @return com.pig4cloud.pig.common.core.util.R<java.lang.Boolean>
	 * @author: hanyx
	 * @createTime: 2019/10/14 15:34
	 */
	@Inner
	@PostMapping("/callback/state")
	public R<Boolean> deviceCallback() {
		return R.ok(deviceService.updateDeviceOnlineState());
	}

}
