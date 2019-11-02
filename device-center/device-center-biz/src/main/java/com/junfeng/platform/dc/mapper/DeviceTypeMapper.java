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
import com.junfeng.platform.dc.entity.DeviceType;
import com.pig4cloud.pig.common.core.vo.DictVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 设备类型
 *
 * @author fuh
 * @date 2019-08-27 15:03:12
 */
public interface DeviceTypeMapper extends BaseMapper<DeviceType> {
    /**
     * 设备类型简单分页查询
     * 
     * @param deviceType
     *            设备类型
     * @return
     */
    IPage<DeviceType> getDeviceTypePage(Page<?> page, @Param("deviceType") DeviceType deviceType);

    List<DictVo> selectDictList();

}
