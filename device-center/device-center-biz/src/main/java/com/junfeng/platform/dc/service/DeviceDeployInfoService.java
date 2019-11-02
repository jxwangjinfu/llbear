package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceDeployInfo;

/**
 * 设备部署信息
 *
 * @author hanyx
 * @date 2019-10-25 11:07:42
 */
public interface DeviceDeployInfoService extends IService<DeviceDeployInfo> {

	/**
	 * 简单分页查询
	 *
	 * @param page             分页对象
	 * @param deviceDeployInfo 设备部署信息
	 * @return 分页列表
	 */
	IPage<DeviceDeployInfo> getDeviceDeployInfoPage(Page<DeviceDeployInfo> page, DeviceDeployInfo deviceDeployInfo);

	/**
	 * 功能描述: 根据设备id查询部署信息
	 *
	 * @param deviceId 设备id
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/25 11:32
	 */
	boolean isExist(Long deviceId);


}
