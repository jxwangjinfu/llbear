package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceAdvertisement;
import org.apache.ibatis.annotations.Param;

/**
 * 设备广告表
 *
 * @author hanyx
 * @date 2019-10-11 15:00:24
 */
public interface DeviceAdvertisementMapper extends BaseMapper<DeviceAdvertisement> {

	/**
	 * 设备广告简单分页查询
	 *
	 * @param deviceAdvertisement 设备广告
	 * @return 分页设备广告
	 */
	IPage<DeviceAdvertisement> getDeviceAdvertisementPage(Page page, @Param("deviceAdvertisement") DeviceAdvertisement deviceAdvertisement);


}
