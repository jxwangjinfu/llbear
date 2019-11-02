package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceAppVersion;

/**
 * 设备APP版本管理
 *
 * @author hanyx
 * @date 2019-10-09 10:53:18
 */
public interface DeviceAppVersionService extends IService<DeviceAppVersion> {

	/**
	 * 设备APP版本简单分页查询
	 *
	 * @param deviceAppVersion 设备APP版本
	 * @return 设备APP版本列表
	 */
	IPage<DeviceAppVersion> getDeviceAppVersionPage(Page<DeviceAppVersion> page, DeviceAppVersion deviceAppVersion);

	/**
	 * 功能描述: 查询某型设备的最新App
	 *
	 * @param devTypeCode  设备型号编码
	 * @param publishState 发布状态
	 * @return com.junfeng.platform.dc.entity.DeviceAppVersion
	 * @author: hanyx
	 * @createTime: 2019/10/9 15:31
	 */
	DeviceAppVersion getLatestApp(String devTypeCode, int publishState);

	/**
	 * 功能描述: 发布设备App版本
	 *
	 * @param appVersion 设备版本
	 * @return java.lang.Boolean
	 * @author: hanyx
	 * @createTime: 2019/10/9 14:51
	 */
	boolean publishDeviceApp(DeviceAppVersion appVersion);


}
