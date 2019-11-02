package com.junfeng.platform.dc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/11 11:03
 * @projectName pig
 */
@Data
public class DeviceConfigBatchVo {

	@NotNull(message = "devTypeCode不能够为空")
	@Size(message = "devTypeCode最长度不能超过255", max = 255)
	private String devTypeCode;

	@NotNull(message = "config不能够为空")
	@Size(message = "config最长度不能超过255", max = 255)
	private String config;
}
