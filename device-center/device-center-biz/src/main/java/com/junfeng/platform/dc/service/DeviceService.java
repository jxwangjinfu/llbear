package com.junfeng.platform.dc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.junfeng.platform.dc.api.result.DeviceResult;
import com.junfeng.platform.dc.api.vo.DeviceCountVo;
import com.junfeng.platform.dc.api.vo.DeviceDeployVo;
import com.junfeng.platform.dc.api.vo.DeviceQueryVo;
import com.junfeng.platform.dc.api.vo.DeviceTypeVo;
import com.junfeng.platform.dc.entity.Device;
import com.junfeng.platform.dc.entity.DeviceIssue;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 设备表
 *
 * @author fuh
 * @date 2019-09-16 16:17:21
 */
public interface DeviceService extends IService<Device> {

	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param device 设备表
	 * @return 设备分页列表
	 */
	IPage<Device> getDevicePage(Page<Device> page, Device device);

	/**
	 * 功能描述: 按设备及其部署信息分页查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<com.junfeng.platform.dc.entity.Device>
	 * @author: hanyx
	 * @createTime: 2019/10/28 14:03
	 */
	IPage<DeviceResult> getDevicePageByDeployInfo(DeviceDeployVo deviceDeployVo);

	/**
	 * 查询某设备类型分页列表
	 *
	 * @param page      分页对象
	 * @param devTypeVo 设备类型dto
	 * @return 该设备分页列表
	 */
	IPage<Device> getDevicePageByDevTypeCode(Page<Device> page, DeviceTypeVo devTypeVo);

	/**
	 * 查询某设备平台分页列表
	 *
	 * @param page        分页对象
	 * @param devPlatform 设备平台
	 * @return 该设备分页列表
	 */
	IPage<Device> getDevicePageByDevPlatform(Page<Device> page, @PathVariable String devPlatform);

	/**
	 * 根据在线状态查询设备列表
	 *
	 * @param onlineState 设备在线状态（在线、离线）
	 * @return 设备列表
	 */
	List<Device> getDeviceListByState(@PathVariable String onlineState);

	/**
	 * 功能描述: 按设备及其部署信息查询
	 *
	 * @param deviceDeployVo 查询对象
	 * @return java.util.List<com.junfeng.platform.dc.entity.Device>
	 * @author: hanyx
	 * @createTime: 2019/10/28 16:38
	 */
	List<Device> getDeviceListByDeployInfo(DeviceDeployVo deviceDeployVo);

	/**
	 * 根据id查找设备
	 *
	 * @param id 设备id
	 * @return 设备
	 */
	Device getDeviceById(@PathVariable Long id);

	/**
	 * 根据设备号查找设备
	 *
	 * @param devCode 设备号
	 * @return 设备
	 */
	Device getDeviceByDeviceCode(@PathVariable String devCode);

	/**
	 * 功能描述: 根据ID和设备号查找设备
	 *
	 * @param id      设备id
	 * @param devCode 设备编码
	 * @return com.junfeng.platform.dc.entity.Device
	 * @author: hanyx
	 * @createTime: 2019/10/16 14:36
	 */
	Device getDeviceByIdAndDeviceCode(@PathVariable Long id, @PathVariable String devCode);

	/**
	 * 功能描述: 根据条件查询设备
	 *
	 * @param deviceVo 设备查询vo
	 * @return com.junfeng.platform.dc.entity.Device
	 * @author: hanyx
	 * @createTime: 2019/10/8 17:05
	 */
	List<Device> getDevice(DeviceQueryVo deviceVo);

	/**
	 * 功能描述: 根据设备类型查找其中没有广告的设备id列表
	 *
	 * @param devTypeCode 设备类型编码
	 * @return java.util.List<java.lang.Long>
	 * @author: hanyx
	 * @createTime: 2019/10/12 11:19
	 */
	List<Long> getNonAdIdListByDeviceType(String devTypeCode);

	/**
	 * 功能描述: 根据设备类型查找其中没有配置的设备id列表
	 *
	 * @param devTypeCode 设备类型编码
	 * @return java.util.List<java.lang.Long>
	 * @author: hanyx
	 * @createTime: 2019/10/12 11:19
	 */
	List<Long> getNonConfigIdListByDeviceType(String devTypeCode);

	/**
	 * 查询版本号为version的机型数量
	 *
	 * @param versionCode 版本号
	 * @return 设备数量
	 */
	Integer getDeviceCountByVersion(@PathVariable int versionCode);

	/**
	 * 查找设备数量
	 *
	 * @param deviceVo 待查找设备vo
	 * @return 设备数量
	 */
	Integer getDeviceCount(DeviceCountVo deviceVo);

	/**
	 * 功能描述: 更新设备的在线状态
	 *
	 * @param device 设备
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/14 16:57
	 */
	boolean updateDeviceOnlineState(Device device);

	/**
	 * 功能描述: 更新设备在线状态
	 *
	 * @return java.lang.Boolean
	 * @author: hanyx
	 * @createTime: 2019/10/14 14:23
	 */
	Boolean updateDeviceOnlineState();

	/**
	 * 功能描述: 查询该设备编码的设备是否存在
	 *
	 * @param devCode 设备编码
	 * @return boolean
	 * @author: hanyx
	 * @createTime: 2019/10/14 14:25
	 */
	boolean isExist(String devCode);

	/**
	 * 功能描述: 根据设备故障查询设备
	 *
	 * @param deviceIssue 设备故障信息
	 * @return com.junfeng.platform.dc.entity.Device
	 * @author: hanyx
	 * @createTime: 2019/10/17 14:52
	 */
	Device getDeviceByIssue(DeviceIssue deviceIssue);

}
