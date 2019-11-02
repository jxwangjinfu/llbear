package com.junfeng.platform.dc.api.vo;

import lombok.Data;


/**
 * @Author: hanyx
 * @CreateTime: 2019-09-25 09:45:39
 * @Description:
 */
@Data
public class DeviceCountVo {

	private Long id;
	/**
	 * 设备号
	 */
	private String devCode;
	/**
	 * 设备厂商
	 */
	private String manufacturer;
	/**
	 * 设备具体型号
	 */
	private String model;
	/**
	 * mac地址
	 */
	private String mac;
	/**
	 * 设备状态：0-创建，待使用；1-使用；2-停用；3-报废
	 */
	private String state;
	/**
	 * 设备型号编码
	 */
	private String deviceTypeCode;
	/**
	 * 设备名称
	 */
	private String devName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 设备本地IP
	 */
	private String lanIp;
	/**
	 * 是否在线
	 */
	private String isOnline;
	/**
	 * 设备版本号
	 */
	private Integer currentVersionCode;
	/**
	 * 由收银台上传的当前版本号
	 */
	private String currentVersionName;
	/**
	 * 收银台平台
	 * android 默认
	 * windows
	 */
	private String platform;
}
