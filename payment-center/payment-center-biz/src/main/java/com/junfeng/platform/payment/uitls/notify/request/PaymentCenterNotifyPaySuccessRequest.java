package com.junfeng.platform.payment.uitls.notify.request;

import com.junfeng.platform.payment.uitls.notify.model.PaymentCenterNotifyPaySuccess;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知业务系统支付成功请求
 *
 * @projectName:tobacco-cloud-common
 * @author:chenyx
 * @date:2018年8月16日 下午2:49:48
 * @version 1.0
 */
public class PaymentCenterNotifyPaySuccessRequest extends PaymentCenterBaseRequest {

    private PaymentCenterNotifyPaySuccess paySuccessMessage;

    public PaymentCenterNotifyPaySuccessRequest(String notifyUrl, PaymentCenterNotifyPaySuccess paySuccessMessage) {
        this.paySuccessMessage = paySuccessMessage;
        this.payMchId = paySuccessMessage.getPayMchId();
        this.url = notifyUrl;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("mchOrderNo", paySuccessMessage.getMchOrderNo());
        param.put("tradeOrderNo", paySuccessMessage.getTradeOrderNo());
        param.put("payOrderNo", paySuccessMessage.getPayOrderNo());
        if(paySuccessMessage.getPaySuccessTimestamp() != null){
            param.put("paySuccessTimestamp", String.valueOf(paySuccessMessage.getPaySuccessTimestamp()));
        }
        param.put("paymentModeCode", paySuccessMessage.getPaymentModeCode());
        return param;
    }

}
