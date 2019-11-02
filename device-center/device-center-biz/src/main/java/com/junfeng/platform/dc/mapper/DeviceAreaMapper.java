package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceArea;
import com.pig4cloud.pig.common.core.vo.DictVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备区域表
 *
 * @author hanyx
 * @date 2019-10-31 13:59:24
 */
public interface DeviceAreaMapper extends BaseMapper<DeviceArea> {
	/**
	 * 简单分页查询
	 *
	 * @param page       分页对象
	 * @param deviceArea 设备区域表
	 * @return 分页列表
	 */
	IPage<DeviceArea> getDeviceAreaPage(Page page, @Param("deviceArea") DeviceArea deviceArea);

	/**
	 * 功能描述: 查找区域类型列表
	 *
	 * @param type 区域类型
	 * @return java.util.List<com.pig4cloud.pig.common.core.vo.DictVo>
	 * @author: hanyx
	 * @createTime: 2019/11/1 15:25
	 */
	List<DictVo> getDictList(@Param("type") Integer type);

}
