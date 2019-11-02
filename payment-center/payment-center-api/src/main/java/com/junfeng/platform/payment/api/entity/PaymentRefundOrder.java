package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 退款订单
 *
 * @author wangk
 * @date 2019-09-19 11:04:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("payment_refund_order")
public class PaymentRefundOrder extends Model<PaymentRefundOrder> {
private static final long serialVersionUID = 1L;

    /**
   * 退款订单ID
   */
    @TableId
    private Long id;
    /**
   * 退款订单号
   */
    private String refundOrderNo;
    /**
   * 支付中心订单号
   */
    private String payOrderNo;
    /**
   * 业务系统商户ID            为了兼容，类型为字符
   */
    private String appShopId;
    /**
   * 支付中心商户ID
   */
    private Long payMchId;
    /**
   * 业务系统支付单号
   */
    private String mchOrderNo;
    /**
   * 第三方交易单号            如：\r\n            微信，支付宝交易单号
   */
    private String tradeOrderNo;
    /**
   * 支付方式编码           微信公众号 WX_JSAPI\r\n            微信条码 WX_BARCODE\r\n            支付宝 ALIPAY_JSAPI\r\n            支付宝条码 ALIPAY_BARCODE
   */
    private String paymentModeCode;
    /**
   * 支付通道编码            如：江西银行 JXYH
   */
    private String payChannelCode;
    /**
   * 支付金额,单位分
   */
    private Long payAmount;
    /**
   * 退款金额,单位分
   */
    private Long refundAmount;
    /**
   * 三位货币代码,人民币:cny
   */
    private String feeType;
    /**
   * 退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败,4-业务处理完成
   */
    private Integer state;
    /**
   * 退款结果:0-不确认结果,1-等待手动处理,2-确认成功,3-确认失败
   */
    private Integer refundResult;
    /**
   * 客户端IP
   */
    private String clientIp;
    /**
   * 备注
   */
    private String remark;
    /**
   * 支付通道支付错误码
   */
    private String errCode;
    /**
   * 支付通道支付错误描述
   */
    private String errMsg;
    /**
   * 通知地址
   */
    private String notifyUrl;
    /**
   * 订单退款成功时间
   */
    private LocalDateTime refundSuccessTime;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;

}
