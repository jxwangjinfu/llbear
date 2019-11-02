package com.junfeng.platform.dc.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备类型
 *
 * @author fuh
 * @date 2019-08-27 15:03:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_type")
public class DeviceType extends Model<DeviceType> {
    private static final long serialVersionUID = 1L;

    /**
    *
    */
    @TableId
    private Long id;
    /**
     * 类型编码
     */
    @NotNull(message="类型编码不能为空")
    private String code;
    /**
     * 类型名称
     */
    @NotNull(message="类型名称不能为空")
    private String name;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
	/**
	 *
	 */
	private Long parentId;
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
