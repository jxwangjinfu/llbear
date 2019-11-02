package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.DeviceConfig;
import com.junfeng.platform.dc.service.DeviceConfigService;
import com.junfeng.platform.dc.mapper.DeviceConfigMapper;
import org.springframework.stereotype.Service;


/**
 * 设备配置表
 *
 * @author hanyx
 * @date 2019-10-10 16:37:14
 */
@Service("deviceConfigService")
public class DeviceConfigServiceImpl extends ServiceImpl<DeviceConfigMapper, DeviceConfig> implements DeviceConfigService {

	/**
	 * 设备配置简单分页查询
	 *
	 * @param deviceConfig 设备配置
	 * @return 分页设备配置
	 */
	@Override
	public IPage<DeviceConfig> getDeviceConfigPage(Page<DeviceConfig> page, DeviceConfig deviceConfig) {
		return baseMapper.getDeviceConfigPage(page, deviceConfig);
	}

	/**
	 * 功能描述: 根据设备id查询配置
	 *
	 * @param deviceId 设备id
	 * @return com.junfeng.platform.dc.entity.DeviceConfig
	 * @author: hanyx
	 * @createTime: 2019/10/11 9:54
	 */
	@Override
	public DeviceConfig getDeviceConfigByDeviceId(Long deviceId) {
		return getOne(Wrappers.<DeviceConfig>lambdaQuery()
			.eq(DeviceConfig::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(DeviceConfig::getDeviceId, deviceId));
	}

	/**
	 * 功能描述: 判断deviceId的设备是否已经存在配置
	 *
	 * @param deviceId 设备id
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/11 16:58
	 */
	@Override
	public boolean isExist(Long deviceId) {
		return getDeviceConfigByDeviceId(deviceId) != null;
	}

}
