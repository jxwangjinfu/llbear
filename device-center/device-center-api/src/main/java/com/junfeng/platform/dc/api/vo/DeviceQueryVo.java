package com.junfeng.platform.dc.api.vo;

import lombok.Data;

/**
 * 设备查询vo
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/8 17:03
 * @projectName pig
 */
@Data
public class DeviceQueryVo {

	private Long id;
	/**
	 * 设备号
	 */
	private String devCode;
	/**
	 * 设备型号编码
	 */
	private String deviceTypeCode;
	/**
	 * 收银台平台
	 * android 默认
	 * windows
	 */
	private String platform;
}
