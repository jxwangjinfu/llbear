package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.DeviceAdvertisement;
import com.junfeng.platform.dc.service.DeviceAdvertisementService;
import com.junfeng.platform.dc.mapper.DeviceAdvertisementMapper;
import org.springframework.stereotype.Service;

/**
 * 设备广告表
 *
 * @author hanyx
 * @date 2019-10-11 15:00:24
 */
@Service("deviceAdvertisementService")
public class DeviceAdvertisementServiceImpl extends ServiceImpl<DeviceAdvertisementMapper, DeviceAdvertisement> implements DeviceAdvertisementService {

	/**
	 * 简单分页查询
	 *
	 * @param page                分页对象
	 * @param deviceAdvertisement 设备广告
	 * @return 分页设备广告
	 */
	@Override
	public IPage<DeviceAdvertisement> getDeviceAdvertisementPage(Page<DeviceAdvertisement> page, DeviceAdvertisement deviceAdvertisement) {
		return baseMapper.getDeviceAdvertisementPage(page, deviceAdvertisement);
	}

	/**
	 * 功能描述: 判断deviceId的设备是否已经存在广告
	 *
	 * @param deviceId 设备id
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/11 16:58
	 */
	@Override
	public boolean isExist(Long deviceId) {
		return getAdvertisementByDeviceId(deviceId) != null;
	}

	/**
	 * 功能描述: 根据设备id查询广告
	 *
	 * @param deviceId 设备id
	 * @return com.junfeng.platform.dc.entity.DeviceAdvertisement
	 * @author: hanyx
	 * @createTime: 2019/10/11 17:24
	 */
	@Override
	public DeviceAdvertisement getAdvertisementByDeviceId(Long deviceId) {
		return getOne(Wrappers.<DeviceAdvertisement>lambdaQuery()
			.eq(DeviceAdvertisement::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(DeviceAdvertisement::getDeviceId, deviceId));
	}

}
