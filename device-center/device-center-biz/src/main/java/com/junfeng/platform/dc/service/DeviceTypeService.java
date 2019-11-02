package com.junfeng.platform.dc.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceType;
import com.pig4cloud.pig.common.core.vo.DictVo;

/**
 * 设备类型
 *
 * @author fuh
 * @date 2019-08-27 15:03:12
 */
public interface DeviceTypeService extends IService<DeviceType> {

	/**
	 * 简单分页查询
	 *
	 * @param page       分页对象
	 * @param deviceType 设备类型
	 * @return 分页设备类型
	 */
	IPage<DeviceType> getDeviceTypePage(Page<DeviceType> page, DeviceType deviceType);


	List<DictVo> getDictList();

	/**
	 * 功能描述: 判断code编码的设备是否存在
	 *
	 * @param code 设备编码
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/15 14:08
	 */
	boolean isExist(String code);
}
