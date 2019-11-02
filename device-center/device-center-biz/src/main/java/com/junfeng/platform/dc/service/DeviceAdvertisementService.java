package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceAdvertisement;

/**
 * 设备广告表
 *
 * @author hanyx
 * @date 2019-10-11 15:00:24
 */
public interface DeviceAdvertisementService extends IService<DeviceAdvertisement> {

	/**
	 * 简单分页查询
	 *
	 * @param page                分页对象
	 * @param deviceAdvertisement 设备广告
	 * @return 分页设备广告
	 */
	IPage<DeviceAdvertisement> getDeviceAdvertisementPage(Page<DeviceAdvertisement> page, DeviceAdvertisement deviceAdvertisement);

	/**
	 * 功能描述: 判断deviceId的设备是否已经存在广告
	 *
	 * @param deviceId 设备id
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/11 16:58
	 */
	boolean isExist(Long deviceId);

	/**
	 * 功能描述: 根据设备id查询广告
	 *
	 * @param deviceId 设备id
	 * @return com.junfeng.platform.dc.entity.DeviceAdvertisement
	 * @author: hanyx
	 * @createTime: 2019/10/11 17:24
	 */
	DeviceAdvertisement getAdvertisementByDeviceId(Long deviceId);

}
