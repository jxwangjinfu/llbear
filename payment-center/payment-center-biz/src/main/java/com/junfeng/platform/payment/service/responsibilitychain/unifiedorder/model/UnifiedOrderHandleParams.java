package com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model;

import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import lombok.Data;

/**
 * 统一下单责任链参数
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年10月24日 下午4:16:13
 * @version 1.0
 */
@Data
public class UnifiedOrderHandleParams {

    //支付描述
    private String body;
    //银行商户账号
    private String payChannelAccount;
    //随机数
    private String nonceStr;
    //请求支付终端IP
    private String spbillCreateIp;
    //总金额
    private String totalFee;
    //支付类型
    private String paymentModeCode;

    // 支付金额
    private Long amount;
    /**
     * 业务系统 支付单号
     */
    private String mchOrderNo;
    /**
     * 支付中心商户ID
     */
    private Long payMchId;
    /**
     * 商户密钥KEY
     */
    private String payChannelKey;

    /**
     * 业务商铺ID
     */
    private String appShopId;

    /**
     * 支付通道编码
     */
    private String payChannelCode;
    /**
     * 回调URL
     */
    private String notifyUrl;
    /**
     * 支付中心订单号
     */
    private String payOrderNo;
    /**
     * 是否支付成功
     */
    private Boolean paySuccess;

    /**
     * 用户标识
     */
    private String openId;

    /**
     * 小程序的appId
     */
    private String appId;

    /**
     * 调银行接口返回的需入库更新参数
     */
    private PaymentOrderRequestRecord payOrderRequestRecord;

    /**
     * 调银行接口返回的需入库的调用记录
     */
    private PaymentOrderResponseRecord payOrderResponseRecord;

    /**
     * 小程序支付所需要的参数
     */
    private MiniPayRequest miniPayRequest;

    /**
     * 责任链的状态
     */
    private ResponsibilityChainHandlerStateEnum handlerState;


}
