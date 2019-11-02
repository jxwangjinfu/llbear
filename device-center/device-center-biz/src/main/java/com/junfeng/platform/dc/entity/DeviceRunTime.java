package com.junfeng.platform.dc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 设备运行时间
 *
 * @author hanyx
 * @date 2019-10-25 15:29:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_run_time")
public class DeviceRunTime extends Model<DeviceRunTime> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 设备id
	 */
	@NotNull
	private Long deviceId;
	/**
	 * 日期
	 * 格式为:20160101
	 */
	@NotNull
	private Integer dayNumber;
	/**
	 * 月份
	 * 格式为:201601
	 */
	private Integer monthNumber;
	/**
	 * 年份
	 * 格式为：2016
	 */
	private Integer yearNumber;
	/**
	 * 在线时长
	 */
	private Double onlineTime;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 0-正常，1-删除
	 */
	@TableLogic
	private String delFlag;

}
