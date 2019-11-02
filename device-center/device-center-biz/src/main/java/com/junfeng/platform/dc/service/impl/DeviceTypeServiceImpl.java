package com.junfeng.platform.dc.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.entity.DeviceType;
import com.junfeng.platform.dc.mapper.DeviceTypeMapper;
import com.junfeng.platform.dc.service.DeviceTypeService;
import com.pig4cloud.pig.common.core.vo.DictVo;

/**
 * 设备类型
 *
 * @author fuh
 * @date 2019-08-27 15:03:12
 */
@Service("deviceTypeService")
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements DeviceTypeService {

	/**
	 * 设备类型简单分页查询
	 *
	 * @param deviceType 设备类型
	 * @return 分页设备类型
	 */
	@Override
	public IPage<DeviceType> getDeviceTypePage(Page<DeviceType> page, DeviceType deviceType) {
		return baseMapper.getDeviceTypePage(page, deviceType);
	}

	@Override
	public List<DictVo> getDictList() {
		return baseMapper.selectDictList();
	}

	/**
	 * 功能描述: 判断code编码的设备是否存在
	 *
	 * @param code 设备编码
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/15 14:08
	 */
	@Override
	public boolean isExist(String code) {
		return getOne(Wrappers.<DeviceType>lambdaQuery()
			.eq(DeviceType::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(DeviceType::getCode, code)) != null;
	}

}
