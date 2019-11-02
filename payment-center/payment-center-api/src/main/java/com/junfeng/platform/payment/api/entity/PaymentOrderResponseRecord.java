package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 支付订单回调记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("payment_order_response_record")
@Accessors(chain = true)
public class PaymentOrderResponseRecord extends Model<PaymentOrderResponseRecord> {
private static final long serialVersionUID = 1L;

    /**
   * 流水号
   */
    @TableId
    private Long id;
    /**
   * 支付中心订单号
   */
    private String payOrderNo;
    /**
   * 第三方交易单号
            如：
            微信，支付宝交易单号
   */
    private String tradeOrderNo;
    /**
   * 支付方式编码
            微信公众号 WX_JSAPI
            微信条码 WX_BARCODE
            支付宝 ALIPAY_JSAPI
            支付宝打条码 ALIPAY_BARCODE
   */
    private String paymentModeCode;
    /**
   * 支付金额,单位分
   */
    private Long amount;
    /**
   *
   */
    private String bankType;
    /**
   * 三位货币代码,人民币:cny
   */
    private String feeType;
    /**
   * 通道账户商户ID
   */
    private String payChannelAccount;
    /**
   *
   */
    private String openId;
    /**
   *
   */
    private String attach;
    /**
   *
   */
    private LocalDateTime paySuccessTime;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;

}
