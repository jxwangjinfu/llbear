package com.junfeng.platform.payment.bank.jxbank.request;

import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.jxbank.common.JxBankPayTypeEnum;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankBarcodeParam;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankMicroPayResult;
import com.junfeng.platform.payment.common.AsXmlMapMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 江西银行微信刷卡支付参数
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月20日 上午10:39:53
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class JxBankWeChatPayBarcodeRequest extends JxBankBaseRequest {
    private JxBankBarcodeParam param;
    /**
     * 构造函数
     *
     * @param url
     * @param key
     * @param spbillCreateIp
     */
    public JxBankWeChatPayBarcodeRequest(JxBankBarcodeParam param) {
        this.uri="micropay";
        this.param = param;
        this.mchId = param.getMchId();
        this.key = param.getKey();
    }


    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("body", param.getBody());
        params.put("auth_code", param.getAuthCode());
        params.put("out_trade_no", param.getOutTradeNo());
        params.put("spbill_create_ip", param.getSpbillCreateIp());
        params.put("total_fee", param.getTotalFee());
        params.put("trade_type", JxBankPayTypeEnum.JXBANK_WXPAY_MICROPAY.getValue());
        return params;
    }

    /**
     * 返回对象
     * @return
     * @throws Exception
     * @author:chenyx
     * @createTime:2018年8月22日 上午10:35:07
     */
    public JxBankMicroPayResult doRequestForObject() throws Exception {
        String reqStr = doRequest();
        JxBankMicroPayResult obj = AsXmlMapMapper.fromXml("JxBankMicroPayResult",reqStr, JxBankMicroPayResult.class);
        return obj;
    }
}
