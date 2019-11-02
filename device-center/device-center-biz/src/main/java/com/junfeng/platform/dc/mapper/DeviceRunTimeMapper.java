package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceRunTime;
import org.apache.ibatis.annotations.Param;

/**
 * 设备运行时间
 *
 * @author hanyx
 * @date 2019-10-25 15:29:06
 */
public interface DeviceRunTimeMapper extends BaseMapper<DeviceRunTime> {

	/**
	 * 简单分页查询
	 *
	 * @param page          分页对象
	 * @param deviceRunTime 设备运行时间
	 * @return 分页列表
	 */
	IPage<DeviceRunTime> getDeviceRunTimePage(Page page, @Param("deviceRunTime") DeviceRunTime deviceRunTime);


}
