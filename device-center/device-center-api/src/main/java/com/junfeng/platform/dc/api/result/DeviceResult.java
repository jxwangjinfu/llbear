package com.junfeng.platform.dc.api.result;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/29 10:11
 * @projectName pig
 */
@Data
public class DeviceResult {

	/**
	 * 设备id
	 */
	private Long id;
	/**
	 * 设备号/SN码
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
	 * 其它属性，json格式
	 */
	private String otherPropertyJson;
	/**
	 * 设备状态：0-创建，待使用；1-使用；2-停用；3-报废
	 */
	private String state;
	/**
	 * 设备类型编码
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
	 * 部门id
	 */
	private Long deptId;
	/**
	 * 来源：读卡器、制卡机、pos机等
	 */
	private String sourceFrom;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
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
	 * 上次在线时间
	 */
	private LocalDateTime lastOnlineTime;
	/**
	 * 设备版本号
	 */
	private Integer currentVersionCode;
	/**
	 * 补丁版本号
	 */
	private Integer patchVersionCode;
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
