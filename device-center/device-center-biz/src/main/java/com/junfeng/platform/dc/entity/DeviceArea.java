package com.junfeng.platform.dc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 设备区域表
 *
 * @author hanyx
 * @date 2019-10-31 13:59:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dc_device_area")
public class DeviceArea extends Model<DeviceArea> {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "ID", type = IdType.INPUT)
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 直接父区域id
	 */
	@ApiModelProperty(value = "直接父区域id")
	private String parentId;
	/**
	 * 所有祖辈区域id集
	 */
	@ApiModelProperty(value = "所有祖辈区域id集")
	private String parentIds;
	/**
	 * 区域名称
	 */
	@ApiModelProperty(value = "区域名称")
	private String name;
	/**
	 * 排序方式
	 */
	@ApiModelProperty(value = "排序方式")
	private Integer sort;
	/**
	 * 区域编码
	 */
	@NotNull(message = "区域编码不能为空")
	@ApiModelProperty(value = "区域编码")
	private Integer code;
	/**
	 * 区域类型
	 */
	@ApiModelProperty(value = "区域类型")
	private Integer type;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.UPDATE)
	@ApiModelProperty(value = "更新者")
	private String updateBy;
	/**
	 * 0-正常，1-删除
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "0-正常，1-删除")
	private String delFlag;

}
