package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.entity.DeviceDeployInfo;
import com.junfeng.platform.dc.service.DeviceDeployInfoService;
import com.junfeng.platform.dc.mapper.DeviceDeployInfoMapper;
import org.springframework.stereotype.Service;

/**
 * 设备部署信息
 *
 * @author hanyx
 * @date 2019-10-25 11:07:42
 */
@Service("deviceDeployInfoService")
public class DeviceDeployInfoServiceImpl extends ServiceImpl<DeviceDeployInfoMapper, DeviceDeployInfo> implements DeviceDeployInfoService {

	/**
	 * 简单分页查询
	 *
	 * @param page             分页对象
	 * @param deviceDeployInfo 设备部署信息
	 * @return 分页列表
	 */
	@Override
	public IPage<DeviceDeployInfo> getDeviceDeployInfoPage(Page<DeviceDeployInfo> page, DeviceDeployInfo deviceDeployInfo) {
		return baseMapper.getDeviceDeployInfoPage(page, deviceDeployInfo);
	}

	/**
	 * 功能描述: 根据设备id查询部署信息
	 *
	 * @param deviceId 设备id
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/25 11:32
	 */
	@Override
	public boolean isExist(Long deviceId) {
		return getOne(Wrappers.<DeviceDeployInfo>lambdaQuery().eq(DeviceDeployInfo::getDeviceId, deviceId)) != null;
	}

}
