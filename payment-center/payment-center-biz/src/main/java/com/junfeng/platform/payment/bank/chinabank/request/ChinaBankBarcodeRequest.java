package com.junfeng.platform.payment.bank.chinabank.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankTranTypeEnum;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankBarcodeParam;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankCallbackResult;

/**
 * 中国银行支付请求
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午4:42:43
 * @version 1.0
 */
public class ChinaBankBarcodeRequest extends BaseChinaBankRequest {

    private ChinaBankBarcodeParam param;

    public ChinaBankBarcodeRequest(ChinaBankBarcodeParam param) {
        this.param = param;
        this.mchId = param.getMchId();
        this.outTradeNo = param.getOutTradeNo();
        this.posNo = param.getPosNo();
        this.key = param.getKey();
        this.tranType = ChinaBankTranTypeEnum.PAY.getValue();
    }

    public Map<String, String> getParams() {
        Map<String,String> map = new HashMap<>();
        map.put("txnAmt", String.format("%012d", param.getAmount()));
        map.put("scanCode", param.getAuthCode());
        return map;
    }

    public ChinaBankCallbackResult doPostForObject() throws Exception {
        String requestResult = doRequest();
        ChinaBankCallbackResult result = JSON.parseObject(requestResult, ChinaBankCallbackResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        ChinaBankBarcodeParam param = new ChinaBankBarcodeParam();
        param.setMchId("104110070110225");
        param.setPosNo("11000221");
        param.setKey("1AC$@!BGT98aw#C");
        param.setOutTradeNo("alipay1422444552");
        param.setAuthCode("289515929371180417");
        param.setAmount(2L);
        ChinaBankBarcodeRequest request = new ChinaBankBarcodeRequest(param);
        ChinaBankCallbackResult result = request.doPostForObject();
        System.out.println(result.toString());
    }

}
