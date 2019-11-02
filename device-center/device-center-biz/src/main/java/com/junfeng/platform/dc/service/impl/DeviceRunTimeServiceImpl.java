package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.entity.DeviceRunTime;
import com.junfeng.platform.dc.service.DeviceRunTimeService;
import com.junfeng.platform.dc.mapper.DeviceRunTimeMapper;
import org.springframework.stereotype.Service;

/**
 * 设备运行时间
 *
 * @author hanyx
 * @date 2019-10-25 15:29:06
 */
@Service("deviceRunTimeService")
public class DeviceRunTimeServiceImpl extends ServiceImpl<DeviceRunTimeMapper, DeviceRunTime> implements DeviceRunTimeService {

	/**
	 * 简单分页查询
	 *
	 * @param page          分页对象
	 * @param deviceRunTime 设备运行时间
	 * @return 分页列表
	 */
	@Override
	public IPage<DeviceRunTime> getDeviceRunTimePage(Page<DeviceRunTime> page, DeviceRunTime deviceRunTime) {
		return baseMapper.getDeviceRunTimePage(page, deviceRunTime);
	}

	/**
	 * 功能描述: 查询该设备当天是否存在运行时间记录
	 *
	 * @param deviceRunTime 设备运行时间信息
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/25 15:41
	 */
	@Override
	public boolean isExist(DeviceRunTime deviceRunTime) {
		return getOne(Wrappers.<DeviceRunTime>lambdaQuery()
			.eq(DeviceRunTime::getDayNumber, deviceRunTime.getDayNumber())
			.eq(DeviceRunTime::getDeviceId, deviceRunTime.getDeviceId())) != null;
	}

}
