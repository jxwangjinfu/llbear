package com.junfeng.platform.payment.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 支付订单请求记录
 *
 * @author wangk
 * @date 2019-09-19 11:03:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("payment_order_request_record")
public class PaymentOrderRequestRecord extends Model<PaymentOrderRequestRecord> {
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
   * 支付中心商户ID
   */
    private Long payMchId;
    /**
   * 业务系统商户ID
            为了兼容，类型为字符
   */
    private String appShopId;
    /**
   * 业务系统支付单号
   */
    private String mchOrderNo;
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
   * 三位货币代码,人民币:cny
   */
    private String feeType;
    /**
   * 支付状态,0-订单生成,1-支付成功 9-订单已关闭
   */
    private Integer state;
    /**
   * 客户端IP
   */
    private String clientIp;
    /**
   * 设备
   */
    private String device;
    /**
   * 商品标题
   */
    private String subject;
    /**
   * 商品描述信息
   */
    private String body;
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
   * 订单失效时间
   */
    private LocalDateTime expireTime;
    /**
   * 订单支付成功时间
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
    /**
   * 退款状态
            0 未退款
            1 已退款
   */
    private Integer refundState;

}
