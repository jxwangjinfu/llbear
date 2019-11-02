package com.junfeng.platform.dc.api.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 描述
 *
 * @author hanyx
 * @version 1.0
 * @createDate 2019/10/17 9:41
 * @projectName pig
 */
@Data
public class DeviceIssueVo {

	/**
	 * 设备Id
	 */
	@NotNull(message = "设备id不能为空")
	private Long deviceId;
	/**
	 * 设备编码
	 */
	private String deviceCode;
	/**
	 * IP地址
	 */
	private String ipAddress;
	/**
	 * 问题描述
	 */
	@NotNull(message = "问题描述不能为空")
	private String issueDesc;
	/**
	 * 问题状态
	 */
	private String issueState;
	/**
	 * 联系人
	 */
	private String linkPerson;
	/**
	 * 问题类型
	 */
	private String issueType;
	/**
	 * 联系电话
	 */
	@NotNull(message = "联系电话不能为空")
	private String linkPhone;
}
