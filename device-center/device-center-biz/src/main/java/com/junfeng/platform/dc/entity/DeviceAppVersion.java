package com.junfeng.platform.dc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 设备APP版本管理
 *
 * @author hanyx
 * @date 2019-10-12 16:57:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_app_version")
public class DeviceAppVersion extends Model<DeviceAppVersion> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 设备类型编码
	 */
	@NotNull(message = "设备类型编码不能为空")
	private String deviceTypeCode;
	/**
	 * 版本名称
	 */
	private String versionName;
	/**
	 * 当前版本
	 */
	@NotNull(message = "版本号不能为空")
	private Integer versionCode;
	/**
	 * APP文件名
	 */
	@NotNull(message = "文件名不能为空")
	private String fileName;
	/**
	 * 下载URL
	 */
	@NotNull(message = "下载地址不能为空")
	private String downloadUrl;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 记录生成时间
	 */
	private LocalDateTime createTime;
	/**
	 * 发布状态：
	 * 0 未发布，
	 * 1 已发布
	 */
	private Integer state;
	/**
	 * 平台
	 * android
	 * windows
	 */
	private String platform;
	/**
	 * 发布时间
	 */
	private LocalDateTime publishTime;
	/**
	 * 是否强制更新标识
	 * true 强制更新
	 * false 可选更新
	 */
	private Integer forceUpdate;
	/**
	 * 最后一个强制更新的版本号
	 */
	private Integer lastForceUpdateVersionCode;
	/**
	 * 版本文件MD5码
	 */
	private String md5;
	/**
	 * 最新补丁版本号
	 */
	private Integer patchVersionCode;
	/**
	 * 最新补丁版本下载地址
	 */
	private String patchDownloadUrl;
	/**
	 * 补丁版本MD5
	 */
	private String patchMd5;
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
