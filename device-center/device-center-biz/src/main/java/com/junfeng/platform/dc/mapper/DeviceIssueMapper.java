package com.junfeng.platform.dc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.entity.DeviceIssue;
import org.apache.ibatis.annotations.Param;

/**
 * 问题表
 *
 * @author hanyx
 * @date 2019-10-08 14:28:13
 */
public interface DeviceIssueMapper extends BaseMapper<DeviceIssue> {

	/**
	 * 问题表简单分页查询
	 *
	 * @param deviceIssue 问题
	 * @return 问题列表
	 */
	IPage<DeviceIssue> getDeviceIssuePage(Page page, @Param("deviceIssue") DeviceIssue deviceIssue);


}
