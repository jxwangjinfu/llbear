package com.junfeng.platform.dc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: hanyx
 * @CreateTime: 2019-09-25 10:53:56
 * @Description:
 */
@Data
public class DeviceTypeVo {
	/**
	 * 设备型号编码
	 */
	@NotBlank(message = "设备类型编码不能为空")
	private String devTypeCode;
	/**
	 * 是否在线，“1”在线，“0”离线
	 */
	private String isOnline;
}
