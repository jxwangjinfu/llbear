package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceArea;
import com.pig4cloud.pig.common.core.vo.DictVo;

import java.util.List;

/**
 * 设备区域表
 *
 * @author hanyx
 * @date 2019-10-31 13:59:24
 */
public interface DeviceAreaService extends IService<DeviceArea> {

	/**
	 * 简单分页查询
	 *
	 * @param page       分页对象
	 * @param deviceArea 设备区域表
	 * @return 分页列表
	 */
	IPage<DeviceArea> getDeviceAreaPage(Page<DeviceArea> page, DeviceArea deviceArea);

	/**
	 * 功能描述: 根据主键查找区域
	 *
	 * @param code 区域行政编码
	 * @return com.junfeng.platform.dc.entity.DeviceArea
	 * @author: hanyx
	 * @createTime: 2019/11/1 11:02
	 */
	DeviceArea getDeviceAreaByCode(Integer code);

	/**
	 * 功能描述: 查询行政编码是否已存在
	 *
	 * @param code 行政编码
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/31 16:59
	 */
	boolean isCodeExist(Integer code);

	/**
	 * 功能描述: 查找区域类型列表
	 *
	 * @param type 区域类型
	 * @return java.util.List<com.pig4cloud.pig.common.core.vo.DictVo>
	 * @author: hanyx
	 * @createTime: 2019/11/1 15:25
	 */
	List<DictVo> getDictList(Integer type);


}
