package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.DeviceCountVo;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import com.junfeng.platform.dc.api.vo.DeviceQueryVo;
import com.junfeng.platform.dc.api.vo.DeviceTypeVo;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.entity.DeviceIssue;
import com.junfeng.platform.dc.service.DeviceService;
import com.junfeng.platform.dc.mapper.DeviceMapper;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备表
 *
 * @author fuh
 * @date 2019-09-16 16:17:21
 */
@Service("deviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

	/**
	 * 设备表简单分页查询
	 *
	 * @param device 设备表
	 * @return 设备分页列表
	 */
	@Override
	public IPage<Device> getDevicePage(Page<Device> page, Device device) {
		return baseMapper.getDevicePage(page, device);
	}

	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.dc.entity.Device>
	 * @author: hanyx
	 * @createTime: 2019/10/28 14:03
	 */
	@Override
	public IPage<DeviceResult> getDevicePageByDeployInfo(DeviceDeployVo deviceDeployVo) {
		if (CheckUtils.isEmpty(deviceDeployVo)) {
			return null;
		}

		Page page = new Page();
		if (!CheckUtils.isEmpty(deviceDeployVo.getPageSize())) {
			page.setSize(deviceDeployVo.getPageSize());
		}
		if (!CheckUtils.isEmpty(deviceDeployVo.getCurrentPage())) {
			page.setCurrent(deviceDeployVo.getCurrentPage());
		}

		return baseMapper.getDevicePageByDeployInfo(page, deviceDeployVo);
	}

	/**
	 * 查询某设备类型列表
	 *
	 * @param page      分页对象
	 * @param devTypeVo 设备类型dto
	 * @return 该设备分页列表
	 */
	@Override
	public IPage<Device> getDevicePageByDevTypeCode(Page<Device> page, DeviceTypeVo devTypeVo) {
		if (CheckUtils.isEmpty(devTypeVo)) {
			return null;
		}

		return page(page, Wrappers.<Device>lambdaQuery()
			.eq(Device::getDeviceTypeCode, devTypeVo.getDevTypeCode())
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(StringUtils.isNotEmpty(devTypeVo.getIsOnline()), Device::getIsOnline, devTypeVo.getIsOnline()));
	}

	/**
	 * 查询某设备平台分页列表
	 *
	 * @param page        分页对象
	 * @param devPlatform 设备平台
	 * @return 该设备分页列表
	 */
	@Override
	public IPage<Device> getDevicePageByDevPlatform(Page<Device> page, String devPlatform) {
		return page(page, Wrappers.<Device>lambdaQuery()
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(Device::getPlatform, devPlatform));
	}

	/**
	 * 根据在线状态查询设备列表
	 *
	 * @param onlineState 设备在线状态（在线、离线）
	 * @return 设备列表
	 */
	@Override
	public List<Device> getDeviceListByState(String onlineState) {
		return list(Wrappers.<Device>lambdaQuery()
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(Device::getIsOnline, onlineState));
	}

	/**
	 * 功能描述: 按设备及其部署信息查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return java.util.List<com.junfeng.platform.dc.entity.Device>
	 * @author: hanyx
	 * @createTime: 2019/10/28 16:38
	 */
	@Override
	public List<Device> getDeviceListByDeployInfo(DeviceDeployVo deviceDeployVo) {
		if (CheckUtils.isEmpty(deviceDeployVo)) {
			return null;
		}

		return baseMapper.getDeviceListByDeployInfo(deviceDeployVo);
	}

	/**
	 * 根据设备id查找设备
	 *
	 * @param id 设备id
	 * @return 设备
	 */
	@Override
	public Device getDeviceById(Long id) {
		return getById(id);
	}

	/**
	 * 根据设备号查找设备
	 *
	 * @param devCode 设备号
	 * @return 设备
	 */
	@Override
	public Device getDeviceByDeviceCode(String devCode) {
		return getOne(Wrappers.<Device>lambdaQuery()
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(Device::getDevCode, devCode));
	}

	/**
	 * 功能描述: 根据ID和设备号查找设备
	 *
	 * @param id      设备id
	 * @param devCode 设备编码
	 * @return com.junfeng.platform.dc.entity.Device
	 * @author: hanyx
	 * @createTime: 2019/10/16 14:36
	 */
	@Override
	public Device getDeviceByIdAndDeviceCode(Long id, String devCode) {
		return getOne(Wrappers.<Device>lambdaQuery()
			.eq(Device::getId, id)
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(Device::getDevCode, devCode));
	}

	/**
	 * 功能描述: 根据条件查询设备
	 *
	 * @param deviceVo 设备查询vo
	 * @return com.junfeng.platform.dc.entity.Device
	 * @author: hanyx
	 * @createTime: 2019/10/8 17:05
	 */
	@Override
	public List<Device> getDevice(DeviceQueryVo deviceVo) {
		if (CheckUtils.isEmpty(deviceVo)) {
			return null;
		}

		return list(Wrappers.<Device>lambdaQuery()
			.eq(StringUtils.isNotEmpty(deviceVo.getDevCode()), Device::getDevCode, deviceVo.getDevCode())
			.eq(StringUtils.isNotEmpty(deviceVo.getDeviceTypeCode()), Device::getDeviceTypeCode, deviceVo.getDeviceTypeCode())
			.eq(StringUtils.isNotEmpty(deviceVo.getPlatform()), Device::getPlatform, deviceVo.getPlatform())
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq((deviceVo.getId() != null), Device::getId, deviceVo.getId()));
	}

	/**
	 * 功能描述: 根据设备类型查找其中没有广告的设备id列表
	 *
	 * @param devTypeCode 设备类型编码
	 * @return java.util.List<java.lang.Long>
	 * @author: hanyx
	 * @createTime: 2019/10/12 11:19
	 */
	@Override
	public List<Long> getNonAdIdListByDeviceType(String devTypeCode) {
		return baseMapper.getNonAdIdListByDeviceType(devTypeCode);
	}

	/**
	 * 功能描述: 根据设备类型查找其中没有配置的设备id列表
	 *
	 * @param devTypeCode 设备类型编码
	 * @return java.util.List<java.lang.Long>
	 * @author: hanyx
	 * @createTime: 2019/10/12 11:19
	 */
	@Override
	public List<Long> getNonConfigIdListByDeviceType(String devTypeCode) {
		return baseMapper.getNonConfigIdListByDeviceType(devTypeCode);
	}

	/**
	 * 查询版本号为version的机型数量
	 *
	 * @param versionCode 版本号
	 * @return 设备数量
	 */
	@Override
	public Integer getDeviceCountByVersion(int versionCode) {
		return count(Wrappers.<Device>lambdaQuery()
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(Device::getCurrentVersionCode, versionCode));
	}

	/**
	 * 查找设备数量
	 *
	 * @param deviceVo 待查找设备vo
	 * @return 设备数量
	 */
	@Override
	public Integer getDeviceCount(DeviceCountVo deviceVo) {
		if (CheckUtils.isEmpty(deviceVo)) {
			return DeviceCenterConstant.DEFAULT_LONG;
		}

		return count(Wrappers.<Device>lambdaQuery()
			.eq(StringUtils.isNotEmpty(deviceVo.getDevCode()), Device::getDevCode, deviceVo.getDevCode())
			.eq(StringUtils.isNotEmpty(deviceVo.getDeviceTypeCode()), Device::getDeviceTypeCode, deviceVo.getDeviceTypeCode())
			.eq(StringUtils.isNotEmpty(deviceVo.getDevName()), Device::getDevName, deviceVo.getDevName())
			.eq(StringUtils.isNotEmpty(deviceVo.getRemark()), Device::getRemark, deviceVo.getRemark())
			.eq(StringUtils.isNotEmpty(deviceVo.getCreateBy()), Device::getCreateBy, deviceVo.getCreateBy())
			.eq(StringUtils.isNotEmpty(deviceVo.getUpdateBy()), Device::getUpdateBy, deviceVo.getUpdateBy())
			.eq(StringUtils.isNotEmpty(deviceVo.getState()), Device::getState, deviceVo.getState())
			.eq(StringUtils.isNotEmpty(deviceVo.getMac()), Device::getMac, deviceVo.getMac())
			.eq(StringUtils.isNotEmpty(deviceVo.getManufacturer()), Device::getManufacturer, deviceVo.getManufacturer())
			.eq(StringUtils.isNotEmpty(deviceVo.getModel()), Device::getModel, deviceVo.getModel())
			.eq(StringUtils.isNotEmpty(deviceVo.getLanIp()), Device::getLanIp, deviceVo.getLanIp())
			.eq(StringUtils.isNotEmpty(deviceVo.getIsOnline()), Device::getIsOnline, deviceVo.getIsOnline())
			.eq(StringUtils.isNotEmpty(deviceVo.getCurrentVersionName()), Device::getCurrentVersionName, deviceVo.getCurrentVersionName())
			.eq(StringUtils.isNotEmpty(deviceVo.getPlatform()), Device::getPlatform, deviceVo.getPlatform())
			.eq((deviceVo.getId() != null), Device::getId, deviceVo.getId())
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq((deviceVo.getCurrentVersionCode() != null), Device::getCurrentVersionCode, deviceVo.getCurrentVersionCode()));
	}

	/**
	 * 功能描述: 更新设备的在线状态
	 *
	 * @param device 设备
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/14 16:57
	 */
	@Override
	public boolean updateDeviceOnlineState(Device device) {
		if (CheckUtils.isEmpty(device)) {
			return false;
		}

		device.setIsOnline(DeviceCenterConstant.STATE_ONLINE);
		device.setLastOnlineTime(LocalDateTime.now());
		device.setUpdateTime(LocalDateTime.now());
		device.setUpdateBy(ContextHolderUtil.getUsername());

		return updateById(device);
	}

	/**
	 * 功能描述: 更新设备在线状态
	 *
	 * @return java.lang.Boolean
	 * @author: hanyx
	 * @createTime: 2019/10/14 14:23
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean updateDeviceOnlineState() {
		// 查找在线的设备，若设备lastOnlineTime与当前时间相差大于PERIOD_TIME，则将设备离线
		List<Device> deviceList = getDeviceListByState(DeviceCenterConstant.STATE_ONLINE);
		deviceList.forEach(item -> {
			if (System.currentTimeMillis() - Timestamp.valueOf(item.getLastOnlineTime()).getTime() > DeviceCenterConstant.PERIOD_TIME) {
				item.setIsOnline(DeviceCenterConstant.STATE_OFFLINE);
				item.setLastOnlineTime(LocalDateTime.now());
			}
		});

		deviceList.forEach(this::updateById);

		return Boolean.TRUE;
	}

	/**
	 * 功能描述: 查询该设备编码的设备是否存在
	 *
	 * @param devCode 设备编码
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/14 14:25
	 */
	@Override
	public boolean isExist(String devCode) {
		return getOne(Wrappers.<Device>lambdaQuery()
			.eq(Device::getDevCode, devCode)
			.eq(Device::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)) != null;
	}

	/**
	 * 功能描述: 根据设备故障查询设备
	 *
	 * @param deviceIssue 设备故障信息
	 * @return com.junfeng.platform.dc.entity.Device
	 * @author: hanyx
	 * @createTime: 2019/10/17 14:52
	 */
	@Override
	public Device getDeviceByIssue(@Validated DeviceIssue deviceIssue) {
		Device device;
		// 只有设备Id
		if (CheckUtils.isEmpty(deviceIssue.getDeviceCode())) {
			device = getDeviceById(deviceIssue.getDeviceId());
		} else {// 既有设备编码又有设备id
			device = getDeviceByIdAndDeviceCode(deviceIssue.getDeviceId(), deviceIssue.getDeviceCode());
		}

		return device;
	}

}
