package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceRunTime;

/**
 * 设备运行时间
 *
 * @author hanyx
 * @date 2019-10-25 15:29:06
 */
public interface DeviceRunTimeService extends IService<DeviceRunTime> {

	/**
	 * 简单分页查询
	 *
	 * @param page          分页对象
	 * @param deviceRunTime 设备运行时间
	 * @return 分页列表
	 */
	IPage<DeviceRunTime> getDeviceRunTimePage(Page<DeviceRunTime> page, DeviceRunTime deviceRunTime);

	/**
	 * 功能描述: 查询该设备当天是否存在运行时间记录
	 *
	 * @param deviceRunTime 设备运行时间信息
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/25 15:41
	 */
	boolean isExist(DeviceRunTime deviceRunTime);

}
