package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客服权限
 *
 * @author lw
 * @date 2019-10-24 15:11:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_role")
public class Role extends Model<Role> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer id;
    /**
   * 权限名称
   */
    private String roleName;
  
}
