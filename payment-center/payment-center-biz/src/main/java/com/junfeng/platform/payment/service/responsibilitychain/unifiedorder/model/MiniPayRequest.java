package com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 云闪付小程序支付所需参数
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年10月24日 下午4:23:02
 * @version 1.0
 */
@Data
public class MiniPayRequest {
    @JSONField(name="package")
    private String miniPackage;
    private String appid;
    private String paySign;
    private String signType;
    private String noncestr;
    private String timestamp;
    private String outTradeNo;
}
