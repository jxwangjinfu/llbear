package com.junfeng.platform.mc.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 会员地址
 *
 * @author daiysh
 * @date 2019-09-23 09:32:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mc_member_address")
public class MemberAddress extends Model<MemberAddress> {
private static final long serialVersionUID = 1L;

    /**
   * id
   */
    @TableId
    private Long id;
    /**
   * 会员ID
   */
    private Long memberId;
    /**
   * 收货人姓名
   */
    private String customerName;
    /**
   * 收货人手机
   */
    private String customerPhone;
    /**
   * 地址所属省区域代码
   */
    private String province;
    /**
   * 地址所属市区域代码
   */
    private String city;
    /**
   * 地址所属区县区域代码
   */
    private String area;
    /**
   * 地址标签
   */
    private String arealabel;
    /**
   * 详细地址
   */
    private String address;
    /**
   * 邮编
   */
    private String postcode;
    /**
   * 是否默认地址 0:否 1:是
   */
    private String isDefault;
    /**
   * 排序
   */
    private Integer sortNum;
    /**
   * 备注
   */
    private String remark;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 创建者
   */
    private String createBy;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;
    /**
   * 更新者
   */
    private String updateBy;
    /**
   * 删除标记
   */
    private String delFlag;

}
