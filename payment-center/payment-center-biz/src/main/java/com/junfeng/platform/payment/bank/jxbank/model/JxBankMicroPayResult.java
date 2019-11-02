package com.junfeng.platform.payment.bank.jxbank.model;

import com.junfeng.platform.payment.bank.jxbank.model.base.BaseBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 刷卡支付返回封装Bean
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月18日 下午12:41:54
 * @version 1.0
 */

@Data
@EqualsAndHashCode(callSuper=true)
@XStreamAlias(value="xml",impl=JxBankMicroPayResult.class)
public class JxBankMicroPayResult extends BaseBean {

    private String attach;//系统分配的商户号

    @XStreamAlias("bank_type")
    private String bankType;//CMC 银行类型，采用字符串类型的银行标识

    @XStreamAlias("fee_type")
    private String feeType;//货币类型，符合 人民币： CNY，其他值列表详见货币类型 ISO 4217 标准的三位字母代码，默认

    @XStreamAlias("mch_id")
    private String mchId;//银行商户号

    @XStreamAlias("out_trade_no")
    private String outTradeNo; //1217752501201407033233368018 支付中心内部的支付订单号,32 个字符内、可包含字母

    @XStreamAlias("sub_is_subscribe")
    private String subIsSubscribe; //用户是否关注公众账号， Y-关注， N-未关注，仅在公众账号类型支付有效

    @XStreamAlias("sub_openid")
    private String subOpenid; //oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 用户在商户 appid 下的唯一标识

    @XStreamAlias("time_end")
    private String timeEnd; //2014-10-30 13:35:25 订单支付时间，格式为 yyyy-MM-dd HH:mm:ss

    @XStreamAlias("total_fee")
    private Long totalFee;//100 订单总金额，单位为分

    @XStreamAlias("trade_type")
    private String tradeType; //WXPAY.MICROPAY 取值如下： WXPAY.MICROPAY,ALIPAY.MICROPAY

}
