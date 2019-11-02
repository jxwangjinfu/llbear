package com.junfeng.platform.dc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/10 18:00
 * @projectName pig
 */
@Data
public class DeviceConfigVo {

	@NotNull(message = "config不能够为空")
	@Size(message = "config最长度不能超过255", max = 255)
	private String config;
}
