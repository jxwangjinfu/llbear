package com.junfeng.platform.dc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 问题表
 *
 * @author hanyx
 * @date 2019-10-14 10:45:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_issue")
public class DeviceIssue extends Model<DeviceIssue> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
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
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 修改人
	 */
	private String updateBy;
	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;

}
