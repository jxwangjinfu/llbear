package com.junfeng.platform.payment.bank.citicbankmini.model.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
public class BaseBean {

    private String status;//0表示成功，非0表示失败此字段是通信标识，非交易标识，交易是否成功需要查看 result_code 来判断
    /**
     *
     */
    private String message;//返回信息，如非空，为错误原因
    /**
     *
     */
    @XStreamAlias("result_code")
    private String resultCode;//0表示成功，非0表示失败
    /**
     *
     */
    @XStreamAlias("err_code")
    private String errCode;//
    /**
     * 错误结果信息描述
     */
    @XStreamAlias("err_msg")
    private String errMsg;//结果信息描述
    /**
     * 签名
     */
    private String sign; //C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法
    /**
     * 随机数
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;


    /**
     *商户ID
     */
    @XStreamAlias("mch_id")
    private String mchId;

//    @XStreamAlias("bank_type")
//    private String bankType;
//
//    @XStreamAlias("fee_type")
//    private String feeType;

}
