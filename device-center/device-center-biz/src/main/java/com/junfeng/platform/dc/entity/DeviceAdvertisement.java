package com.junfeng.platform.dc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 设备广告表
 *
 * @author hanyx
 * @date 2019-10-11 15:00:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_advertisement")
public class DeviceAdvertisement extends Model<DeviceAdvertisement> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 设备id
	 */
	@NotNull(message = "设备id不能为空")
	private Long deviceId;
	/**
	 * 广告地址
	 */
	@NotNull(message = "广告地址不能为空")
	private String advertisementUrl;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;

}
