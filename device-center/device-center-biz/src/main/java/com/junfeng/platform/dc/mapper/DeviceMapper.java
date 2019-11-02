/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import com.junfeng.platform.dc.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备表
 *
 * @author fuh
 * @date 2019-09-16 17:38:17
 */
public interface DeviceMapper extends BaseMapper<Device> {
	/**
	 * 设备表简单分页查询
	 *
	 * @param page   分页对象
	 * @param device 设备表
	 * @return 分页设备
	 */
	IPage<Device> getDevicePage(Page<?> page, @Param("device") Device device);

	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param page           分页对象
	 * @param deviceDeployVo 查询对象
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.dc.entity.Device>
	 * @author: hanyx
	 * @createTime: 2019/10/28 14:55
	 */
	IPage<DeviceResult> getDevicePageByDeployInfo(Page<?> page, @Param("deviceDeployVo") DeviceDeployVo deviceDeployVo);

	/**
	 * 功能描述: 按设备及其部署信息查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return java.util.List<com.junfeng.platform.dc.entity.Device>
	 * @author: hanyx
	 * @createTime: 2019/10/28 16:38
	 */
	List<Device> getDeviceListByDeployInfo(@Param("deviceDeployVo") DeviceDeployVo deviceDeployVo);

	/**
	 * 功能描述: 根据设备类型查找其中没有广告的设备id列表
	 *
	 * @param devTypeCode 设备类型编码
	 * @return java.util.List<java.lang.Long>
	 * @author: hanyx
	 * @createTime: 2019/10/12 11:19
	 */
	List<Long> getNonAdIdListByDeviceType(@Param("devTypeCode") String devTypeCode);

	/**
	 * 功能描述: 根据设备类型查找其中没有配置的设备id列表
	 *
	 * @param devTypeCode 设备类型编码
	 * @return java.util.List<java.lang.Long>
	 * @author: hanyx
	 * @createTime: 2019/10/12 11:19
	 */
	List<Long> getNonConfigIdListByDeviceType(@Param("devTypeCode") String devTypeCode);

}
