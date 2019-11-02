package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 支付通道
 *
 * @author wangk
 * @date 2019-09-19 11:03:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("payment_channel")
public class PaymentChannel extends Model<PaymentChannel> {
private static final long serialVersionUID = 1L;

    /**
   * 支付通道主键ID
   */
    @TableId
    private Long id;
    /**
   * 支付通道编码
            如：江西银行 JXYH
   */
    private String payChannelCode;
    /**
   * 支付通道名称
            如：
            江西银行
   */
    private String name;
    /**
   * 状态,0-停止使用,1-使用中
   */
    private Integer state;
    /**
   * 配置参数,json字符串
   */
    private String param;
    /**
   * 备注
   */
    private String remark;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;

}
