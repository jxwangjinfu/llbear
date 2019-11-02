package com.junfeng.platform.dc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 上报设备版本信息vo
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/8 16:35
 * @projectName pig
 */
@Data
public class DeviceVersionVo {

	@NotNull(message = "devCode不能够为空")
	@Size(message = "devCode最长度不能超过45", max = 45)
	private String devCode;

	@NotNull(message = "deviceTypeCode不能够为空")
	@Size(message = "deviceTypeCode最长度不能超过45", max = 45)
	private String deviceTypeCode;

	@NotNull(message = "currentVersionCode不能够为空")
	private Integer currentVersionCode;

	private Integer patchVersionCode;

	@NotBlank(message = "currentVersionName不能够为空")
	@Size(message = "currentVersionName最长度不能超过45", max = 45)
	private String currentVersionName;

	private String platform;

}
