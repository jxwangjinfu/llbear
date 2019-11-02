package com.junfeng.platform.dc.util;

import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.DeviceConfig;
import com.junfeng.platform.dc.entity.DeviceIssue;
import com.pig4cloud.pig.common.core.util.R;

import java.time.LocalDateTime;

/**
 * 设备中心工具类
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/14 16:41
 * @projectName pig
 */
public final class DeviceUtils {

	private DeviceUtils() {
	}

	/**
	 * 功能描述: 创建一个设备配置对象
	 *
	 * @param id     设备id
	 * @param config 设备配置
	 * @return com.junfeng.platform.dc.entity.DeviceConfig
	 * @author: hanyx
	 * @createTime: 2019/10/14 16:13
	 */
	public static DeviceConfig createDeviceConfig(Long id, String config) {
		DeviceConfig deviceConfig = new DeviceConfig();
		deviceConfig.setDeviceId(id);
		deviceConfig.setConfig(config);
		deviceConfig.setCreateTime(LocalDateTime.now());
		deviceConfig.setUpdateTime(LocalDateTime.now());
		deviceConfig.setCreateBy(ContextHolderUtil.getUsername());
		deviceConfig.setUpdateBy(ContextHolderUtil.getUsername());
		deviceConfig.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return deviceConfig;
	}

	/**
	 * 功能描述: 验证设备故障数据合法性
	 *
	 * @param deviceIssue 设备故障
	 * @return com.pig4cloud.pig.common.core.util.R
	 * @author: hanyx
	 * @createTime: 2019/10/17 14:57
	 */
	public static R validateDeviceIssue(DeviceIssue deviceIssue) {
		if (!CheckUtils.isEmpty(deviceIssue.getDeviceCode()) && !CheckUtils.isCode(deviceIssue.getDeviceCode())) {
			return R.failed("设备编码不合法");
		}

		if (!CheckUtils.isEmpty(deviceIssue.getIpAddress()) && !CheckUtils.isIP(deviceIssue.getIpAddress())) {
			return R.failed("IP地址不合法");
		}

		if (!CheckUtils.isPhoneNumber(deviceIssue.getLinkPhone())) {
			return R.failed("联系电话不合法");
		}

		return R.ok();
	}
}
