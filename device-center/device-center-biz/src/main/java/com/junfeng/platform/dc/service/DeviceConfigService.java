package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceConfig;

import java.util.List;

/**
 * 设备配置表
 *
 * @author hanyx
 * @date 2019-10-10 16:37:14
 */
public interface DeviceConfigService extends IService<DeviceConfig> {

	/**
	 * 简单分页查询
	 *
	 * @param page         分页对象
	 * @param deviceConfig 设备配置
	 * @return 分页设备配置
	 */
	IPage<DeviceConfig> getDeviceConfigPage(Page<DeviceConfig> page, DeviceConfig deviceConfig);

	/**
	 * 功能描述: 根据设备id查询配置
	 *
	 * @param deviceId 设备id
	 * @return com.junfeng.platform.dc.entity.DeviceConfig
	 * @author: hanyx
	 * @createTime: 2019/10/11 9:54
	 */
	DeviceConfig getDeviceConfigByDeviceId(Long deviceId);

	/**
	 * 功能描述: 判断deviceId的设备是否已经存在配置
	 *
	 * @param deviceId 设备id
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/11 16:58
	 */
	boolean isExist(Long deviceId);

}
