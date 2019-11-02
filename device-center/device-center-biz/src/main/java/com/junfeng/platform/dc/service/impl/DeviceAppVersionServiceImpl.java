package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.DeviceAppVersion;
import com.junfeng.platform.dc.service.DeviceAppVersionService;
import com.junfeng.platform.dc.mapper.DeviceAppVersionMapper;
import com.junfeng.platform.dc.util.CheckUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备APP版本管理
 *
 * @author hanyx
 * @date 2019-10-09 10:53:18
 */
@Service("deviceAppVersionService")
public class DeviceAppVersionServiceImpl extends ServiceImpl<DeviceAppVersionMapper, DeviceAppVersion> implements DeviceAppVersionService {

	/**
	 * 设备APP版本简单分页查询
	 *
	 * @param deviceAppVersion 设备APP版本
	 * @return 设备APP版本列表
	 */
	@Override
	public IPage<DeviceAppVersion> getDeviceAppVersionPage(Page<DeviceAppVersion> page, DeviceAppVersion deviceAppVersion) {
		return baseMapper.getDeviceAppVersionPage(page, deviceAppVersion);
	}

	/**
	 * 功能描述: 查询某型设备的最新App
	 *
	 * @param devTypeCode  设备型号编码
	 * @param publishState 发布状态
	 * @return com.junfeng.platform.dc.entity.DeviceAppVersion
	 * @author: hanyx
	 * @createTime: 2019/10/9 15:31
	 */
	@Override
	public DeviceAppVersion getLatestApp(String devTypeCode, int publishState) {
		List<DeviceAppVersion> appVersionList = list(Wrappers.<DeviceAppVersion>lambdaQuery()
			.eq(DeviceAppVersion::getDeviceTypeCode, devTypeCode)
			.eq(((publishState == DeviceCenterConstant.STATE_UNPUBLISHED) || (publishState == DeviceCenterConstant.STATE_PUBLISHED)), DeviceAppVersion::getState, publishState)
			.eq(DeviceAppVersion::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.orderByDesc(DeviceAppVersion::getPublishTime));

		if (CheckUtils.isEmpty(appVersionList)) {
			return null;
		}

		return appVersionList.get(0);
	}

	/**
	 * 功能描述: 发布设备App版本
	 *
	 * @param appVersion 设备版本
	 * @return java.lang.Boolean
	 * @author: hanyx
	 * @createTime: 2019/10/9 14:51
	 */
	@Override
	public boolean publishDeviceApp(DeviceAppVersion appVersion) {
		if (CheckUtils.isEmpty(appVersion)) {
			return false;
		}

		appVersion.setState(DeviceCenterConstant.STATE_PUBLISHED);
		appVersion.setPublishTime(LocalDateTime.now());

		return updateById(appVersion);
	}

}
