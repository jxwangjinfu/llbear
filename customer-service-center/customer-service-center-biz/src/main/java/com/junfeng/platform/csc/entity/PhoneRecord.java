package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 话机接听记录表
 *
 * @author lw
 * @date 2019-09-29 10:38:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_phone_record")
public class PhoneRecord extends Model<PhoneRecord> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Long id;
    /**
   * 
   */
    private Long userCode;
    /**
   * 
   */
    private Long phoneCode;
    /**
   * 话机状态 0：开始 1：结束
   */
    private Integer phoneType;
    /**
   * 
   */
    private LocalDateTime createTime;
  
}
