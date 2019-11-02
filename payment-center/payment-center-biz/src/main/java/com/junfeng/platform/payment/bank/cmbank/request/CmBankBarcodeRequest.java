package com.junfeng.platform.payment.bank.cmbank.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.cmbank.model.CmBankBarcodeParam;
import com.junfeng.platform.payment.bank.cmbank.model.CmBankMircoPayResult;
import com.junfeng.platform.payment.common.AsXmlMapMapper;

/**
 * 招商银行刷卡支付
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 上午11:05:17
 * @version 1.0
 */
public class CmBankBarcodeRequest extends CmBankBaseRequest {

    /**
     * 刷卡支付参数
     */
    private CmBankBarcodeParam param;

    /**
     * 构造函数
     *
     */
    public CmBankBarcodeRequest(CmBankBarcodeParam param) {
        this.service = "unified.trade.micropay";
        this.mchId = param.getMchId();
        this.outTradeNo = param.getOutTradeNo();
        this.key = param.getKey();
        this.param = param;
    }

    /**
     * 构造函数
     */
    public CmBankBarcodeRequest() {

    }

    @Override
    public Map<String, String> getParams() {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<String, String>();
        map.put("body", param.getBody());
        map.put("total_fee", String.valueOf(param.getTotalFee()));
        map.put("mch_create_ip", param.getMchCreateIp());
        map.put("auth_code", param.getAuthCode());
        return map;
    }

    public CmBankMircoPayResult doPostForObject() throws IOException, URISyntaxException {
        String requestResult = doRequest();
        CmBankMircoPayResult result = AsXmlMapMapper.fromXml("CmBankMircoPayResult",requestResult, CmBankMircoPayResult.class);
        return result;
    }

}
