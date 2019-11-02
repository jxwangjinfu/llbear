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
 * 设备部署信息
 *
 * @author hanyx
 * @date 2019-10-25 11:07:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_deploy_info")
public class DeviceDeployInfo extends Model<DeviceDeployInfo> {
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
	 * 设备型号编码
	 */
	@NotNull
	private String deviceTypeCode;
	/**
	 * SN号
	 */
	private String sn;
	/**
	 * 联系人
	 */
	private String linkPerson;
	/**
	 * 联系方式
	 */
	@NotNull
	private String linkPhone;
	/**
	 * 使用单位
	 */
	private String useDepartment;
	/**
	 * 部署区域
	 */
	private String deployArea;

	/**
	 * 部署区域行政编码
	 */
	@NotNull
	private String deployAreaCode;
	/**
	 * 其它属性,json格式
	 */
	private String otherPropertyJson;
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
	 * 逻辑删除标志
	 */
	@TableLogic
	private String delFlag;

}
