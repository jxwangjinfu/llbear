package com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model;

import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.common.httpresp.RequestResult;
import lombok.Data;

/**
 * 银行的条码支付责任链参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月19日 下午3:12:44
 * @version 1.0
 */
@Data
public class BarcodePayHandleParams {

    //支付描述
    private String body;
    //银行商户账号
    private String payChannelAccount;
    //微信或者支付宝的支付码
    private String authCode;
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
     * 支付通道为建设银行，这个字段存的是柜台Id:posId
     */
    private String payChannelExtendJson;

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
     * 调银行接口返回的需入库更新参数
     */
    private PaymentOrderRequestRecord payOrderRequestRecord;

    /**
     * 调银行接口返回的需入库的调用记录
     */
    private PaymentOrderResponseRecord payOrderResponseRecord;

    /**
     * 责任链的状态
     */
    private ResponsibilityChainHandlerStateEnum handlerState;

    /**
     * 错误请求结果
     */
    private RequestResult errorRequestResult;

    /**
     * 农业银行用户私钥
     */
    private String mchPrivateKey;


}
