package com.junfeng.platform.csc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 话机当前状态表
 *
 * @author lw
 * @date 2019-09-29 10:38:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("csc_phone_state")
public class PhoneState extends Model<PhoneState> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId(type = IdType.INPUT)
    private Long phoneCode;
    /**
   * 占用状态
   */
    private Integer state;

}
