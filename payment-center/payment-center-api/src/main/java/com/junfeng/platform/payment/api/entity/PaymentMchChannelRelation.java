package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商户_支付通道关联表
 *
 * @author wangk
 * @date 2019-09-25 15:30:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("payment_mch_channel_relation")
public class PaymentMchChannelRelation extends Model<PaymentMchChannelRelation> {
private static final long serialVersionUID = 1L;

    /**
   * 主键ID
   */
    @TableId
    private Long id;
    /**
   *
   */
    private Long payMchId;
    /**
   * 支付通道编码
   */
    private String payChannelCode;
    /**
   * 通道账户商户ID
   */
    private String payChannelAccount;
    /**
   * 通道密匙
   */
    private String payChannelKey;
    /**
   * 扩展字段json格式
解决银联小程序支付商户号不同的问题
   */
    private String extendJson;
    /**
   * 通道状态,
            0 停用；
            1 启用；
   */
    private Integer state;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;

}
