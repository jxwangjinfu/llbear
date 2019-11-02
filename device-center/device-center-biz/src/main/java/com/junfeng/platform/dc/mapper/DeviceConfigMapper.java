package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 设备配置表
 *
 * @author hanyx
 * @date 2019-10-10 16:37:14
 */
public interface DeviceConfigMapper extends BaseMapper<DeviceConfig> {

	/**
	 * 设备配置简单分页查询
	 *
	 * @param deviceConfig 设备配置
	 * @return 分页设备配置
	 */
	IPage<DeviceConfig> getDeviceConfigPage(Page page, @Param("deviceConfig") DeviceConfig deviceConfig);


}
