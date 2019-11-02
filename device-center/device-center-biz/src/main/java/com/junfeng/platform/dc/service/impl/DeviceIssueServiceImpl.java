package com.junfeng.platform.dc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.junfeng.platform.dc.constant.DeviceCenterConstant;
import com.junfeng.platform.dc.entity.DeviceIssue;
import com.junfeng.platform.dc.service.DeviceIssueService;
import com.junfeng.platform.dc.mapper.DeviceIssueMapper;
import com.junfeng.platform.dc.util.CheckUtils;
import com.junfeng.platform.dc.util.ContextHolderUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 问题表
 *
 * @author hanyx
 * @date 2019-10-08 14:28:13
 */
@Service("deviceIssueService")
public class DeviceIssueServiceImpl extends ServiceImpl<DeviceIssueMapper, DeviceIssue> implements DeviceIssueService {

	/**
	 * 问题表简单分页查询
	 *
	 * @param deviceIssue 问题
	 * @return 问题列表
	 */
	@Override
	public IPage<DeviceIssue> getDeviceIssuePage(Page<DeviceIssue> page, DeviceIssue deviceIssue) {
		return baseMapper.getDeviceIssuePage(page, deviceIssue);
	}

	/**
	 * 功能描述: 根据设备id查询问题
	 *
	 * @param deviceId 设备id
	 * @return com.junfeng.platform.dc.entity.DeviceConfig
	 * @author: hanyx
	 * @createTime: 2019/10/11 9:54
	 */
	@Override
	public List<DeviceIssue> getDeviceIssueListByDeviceId(Long deviceId) {
		return list(Wrappers.<DeviceIssue>lambdaQuery()
			.eq(DeviceIssue::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE)
			.eq(DeviceIssue::getDeviceId, deviceId));
	}

	/**
	 * 功能描述: 查询设备故障是否存在
	 *
	 * @param deviceIssue 设备故障信息
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/17 11:05
	 */
	@Override
	public boolean isExist(DeviceIssue deviceIssue) {
		if (CheckUtils.isEmpty(deviceIssue)) {
			return false;
		}

		List<DeviceIssue> deviceIssueList = list(Wrappers.<DeviceIssue>lambdaQuery()
			.eq(DeviceIssue::getDeviceId, deviceIssue.getDeviceId())
			.eq(DeviceIssue::getIssueDesc, deviceIssue.getIssueDesc())
			.eq(DeviceIssue::getLinkPhone, deviceIssue.getLinkPhone())
			.eq(DeviceIssue::getDelFlag, DeviceCenterConstant.DEL_FLAG_FALSE));

		return !CheckUtils.isEmpty(deviceIssueList) && (deviceIssueList.size() > 0);
	}

	/**
	 * 功能描述: 保存设备故障
	 *
	 * @param deviceIssue 设备故障信息
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/17 11:40
	 */
	@Override
	public boolean saveDeviceIssue(DeviceIssue deviceIssue) {
		if (CheckUtils.isEmpty(deviceIssue)) {
			return false;
		}

		deviceIssue.setCreateTime(LocalDateTime.now());
		deviceIssue.setUpdateTime(LocalDateTime.now());
		deviceIssue.setCreateBy(ContextHolderUtil.getUsername());
		deviceIssue.setUpdateBy(ContextHolderUtil.getUsername());
		deviceIssue.setDelFlag(DeviceCenterConstant.DEL_FLAG_FALSE);

		return save(deviceIssue);
	}

}
