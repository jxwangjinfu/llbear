package com.junfeng.platform.dc.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 按设备及其部署信息分页查询接口请求参数
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/28 13:55
 * @projectName pig
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeviceDeployVo extends PageVo {

	/**
	 * 设备类型编码
	 */
	private String deviceTypeCode;
	/**
	 * 设备厂商
	 */
	private String manufacturer;

	/**
	 * 开始时间
	 */
	private LocalDateTime beginTime;

	/**
	 * 结束时间
	 */
	private LocalDateTime endTime;


	/**
	 * 部署区域
	 */
	private String deployArea;

	/**
	 * 使用单位
	 */
	private String useDepartment;

	/**
	 * SN号
	 */
	private String sn;


}
