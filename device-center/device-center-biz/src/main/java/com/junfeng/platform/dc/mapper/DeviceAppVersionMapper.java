package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceAppVersion;
import org.apache.ibatis.annotations.Param;

/**
 * 设备APP版本管理
 *
 * @author hanyx
 * @date 2019-10-09 10:53:18
 */
public interface DeviceAppVersionMapper extends BaseMapper<DeviceAppVersion> {
	/**
	 * 设备APP版本简单分页查询
	 *
	 * @param deviceAppVersion 设备APP版本
	 * @return 设备APP版本列表
	 */
	IPage<DeviceAppVersion> getDeviceAppVersionPage(Page page, @Param("deviceAppVersion") DeviceAppVersion deviceAppVersion);


}
