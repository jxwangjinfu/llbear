package com.junfeng.platform.payment.bank.jxbank.model.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

/**
 * 银行返回结果封装Bean基类
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年8月18日 下午12:48:05
 * @version 1.0
 */
@Data
public class BaseBean {
    private Integer retcode;//类型：Int(10) 示例值：0 描述：0/-1 错误码， 0 表示成功
    private String retmsg;//返回信息，如非空，为错误原因
    private String sign; //C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法
    @XStreamAlias("nonce_str")
    private String nonceStr; //5K8264ILTKCH16CQ2502SI8ZNMTM67VS 系统返回的随机字符串
    @XStreamAlias("order_no")
    private String orderNo; //1000100001201608251140038097 平台订单号
}
