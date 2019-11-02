package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.entity.DeviceIssue;

import java.util.List;

/**
 * 问题表
 *
 * @author hanyx
 * @date 2019-10-08 14:28:13
 */
public interface DeviceIssueService extends IService<DeviceIssue> {

	/**
	 * 简单分页查询
	 *
	 * @param page        分页对象
	 * @param deviceIssue 问题表
	 * @return 问题列表
	 */
	IPage<DeviceIssue> getDeviceIssuePage(Page<DeviceIssue> page, DeviceIssue deviceIssue);

	/**
	 * 功能描述: 根据设备id查询问题
	 *
	 * @param deviceId 设备id
	 * @return com.junfeng.platform.dc.entity.DeviceConfig
	 * @author: hanyx
	 * @createTime: 2019/10/11 9:54
	 */
	List<DeviceIssue> getDeviceIssueListByDeviceId(Long deviceId);

	/**
	 * 功能描述: 查询设备故障是否存在
	 *
	 * @param deviceIssue 设备故障信息
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/17 11:05
	 */
	boolean isExist(DeviceIssue deviceIssue);

	/**
	 * 功能描述: 保存设备故障
	 *
	 * @param deviceIssue 设备故障信息
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/17 11:40
	 */
	boolean saveDeviceIssue(DeviceIssue deviceIssue);

}
