package com.junfeng.platform.payment.bank.unionpaymini.common;

public enum UnionpayMiniStateEnum {

    SUCCESS("SUCCESS","下单成功"),
    CITICMINISUCCESS("0","中信银行小程序下单成功"),
    INTERNAL_ERROR("INTERNAL_ERROR","内部错误"),
    BAD_REQUEST("BAD_REQUEST","请求报文有错"),
    NO_SERVICE("NO_SERVICE","没有能处理请求msgtype的服务"),
    TIMEOUT("TIMEOUT","处理超时"),
    NO_ORDER("NO_ORDER","找不到请求的原始订单"),
    OPERATION_NOT_ALLOWED("OPERATION_NOT_ALLOWED","当前不允许此操作"),
    DUP_ORDER("DUP_ORDER","重复订单"),
    NET_ERROR("NET_ERROR","网络错误"),
    NO_MERCHANT("NO_MERCHANT","找不到请求指定的商户"),
    ORDER_PROCESSING("ORDER_PROCESSING","订单正在处理中，不允许并发操作。"),
    INACTIVE_MERCHANT("INACTIVE_MERCHANT","商户被置为inactive状态"),
    ABNORMAL_REQUEST_TIME("ABNORMAL_REQUEST_TIME","请求时间异常"),
    TXN_DISCARDED("TXN_DISCARDED","请求开始处理时间延迟过大，交易被丢弃。"),
    BAD_SIGN("BAD_SIGN","签名错误"),
    INVALID_MSGSRC("INVALID_MSGSRC","商户来源错误"),
    INVALID_ORDER("INVALID_ORDER","订单信息异常"),
    NO_CROSS_DAY_TRADING("NO_CROSS_DAY_TRADING","不允许跨日交易"),
    DENIED_IP("DENIED_IP","不允许此IP交易"),
    INVLID_MERCHANT_CONFIG("INVLID_MERCHANT_CONFIG","错误的商户配置"),
    INVALID_RESPONSE("INVALID_RESPONSE","无效的应答报文"),
    WAIT_BUYER_PAY("WAIT_BUYER_PAY","交易创建，等待买家付款"),
    TRADE_SUCCESS("TRADE_SUCCESS","支付成功");

    private String state;
    private String description;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param state
     * @param description
     */
    private UnionpayMiniStateEnum(String state, String description) {
        this.state = state;
        this.description = description;
    }



}
