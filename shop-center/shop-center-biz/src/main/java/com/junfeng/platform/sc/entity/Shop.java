package com.junfeng.platform.sc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 店铺信息
 *
 * @author lw
 * @date 2019-10-21 13:49:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sc_shop")
public class Shop extends Model<Shop> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Long id;
    /**
   * 系统来源
   */
    @TableField(fill = FieldFill.INSERT)
    private String clientId;
    /**
   * 店铺名称
   */
    private String shopName;
    /**
   * 店铺状态
   */
    private String state;
    /**
   * 店铺类型
   */
    private Long typeCode;
    /**
   * 省份编码
   */
    private String provinceCode;
    /**
   * 城市编码
   */
    private String cityCode;
    /**
   * 区域编码
   */
    private String areaCode;
    /**
   * 地址详情
   */
    private String address;
    /**
   * 经度
   */
    private Float longitude;
    /**
   * 纬度
   */
    private Float latitude;
    /**
   * '0-正常，1-删除'
   */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private String delFlag;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 创建人
   */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
   * 最后变更时间
   */
    private LocalDateTime updateTime;
    /**
   * 更新人员
   */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
