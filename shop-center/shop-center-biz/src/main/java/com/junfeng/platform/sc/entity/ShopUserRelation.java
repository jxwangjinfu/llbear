package com.junfeng.platform.sc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 店铺用户关联信息
 *
 * @author lw
 * @date 2019-10-21 13:49:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sc_shop_user_relation")
public class ShopUserRelation extends Model<ShopUserRelation> {
private static final long serialVersionUID = 1L;

    /**
   * 店铺人员关联ID
   */
    @TableId
    private Long id;
    /**
   * 店铺ID
   */
    private Long shopCode;
    /**
   * 人员ID
   */
    private Long userCode;
    /**
   * 人员类型 0:所有者 1：店员
   */
    private String userType;
  
}
