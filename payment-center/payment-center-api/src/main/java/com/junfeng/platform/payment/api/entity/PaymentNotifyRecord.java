package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 支付成功通知记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("payment_notify_record")
public class PaymentNotifyRecord extends Model<PaymentNotifyRecord> {
private static final long serialVersionUID = 1L;

    /**
   *
   */
    @TableId
    private Long id;
    /**
   * 支付中心商户ID
   */
    private Long payMchId;
    /**
   * 支付方式编码
            微信公众号 WX_JSAPI
            微信条码 WX_BARCODE
            支付宝 ALIPAY_JSAPI
            支付宝条码 ALIPAY_BARCODE
   */
    private String paymentModeCode;
    /**
   * 支付通道编码
            如：江西银行 JXYH
   */
    private String payChannelCode;
    /**
   * 支付中心订单号
   */
    private String payOrderNo;
    /**
   * 业务系统支付单号
   */
    private String mchOrderNo;
    /**
   * 第三方交易单号
            如：
            微信，支付宝交易单号
   */
    private String tradeOrderNo;
    /**
   * 支付金额,单位分
   */
    private Long amount;
    /**
   * 通知地址
   */
    private String notifyUrl;
    /**
   * 通知次数
   */
    private Integer notifyCount;
    /**
   * 通知状态
            0 未成功
            1 成功
   */
    private Integer state;
    /**
   * 最后一次通知时间
   */
    private LocalDateTime lastNotifyTime;
    /**
   *
   */
    private LocalDateTime notifySuccessTime;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;

}
