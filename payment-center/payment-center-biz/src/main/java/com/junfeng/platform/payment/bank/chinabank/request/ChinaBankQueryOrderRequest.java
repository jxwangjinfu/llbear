package com.junfeng.platform.payment.bank.chinabank.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankTranTypeEnum;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankCallbackResult;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankQueryOrderParam;

/**
 *
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午5:45:34
 * @version 1.0
 */
public class ChinaBankQueryOrderRequest extends BaseChinaBankRequest {

    private ChinaBankQueryOrderParam param;

    public ChinaBankQueryOrderRequest(ChinaBankQueryOrderParam param) {
        this.param = param;
        this.mchId = param.getMchId();
        this.outTradeNo = param.getOutTradeNo();
        this.posNo = param.getPosNo();
        this.key = param.getKey();
        this.tranType = ChinaBankTranTypeEnum.QUERY.getValue();
    }

    public Map<String, String> getParams() {
        Map<String,String> map = new HashMap<>();
        map.put("orgSysTrace", param.getOrgSysTrace());
        return map;
    }

    public ChinaBankCallbackResult doPostForObject() throws Exception {
        String requestResult = doRequest();
        ChinaBankCallbackResult result = JSON.parseObject(requestResult, ChinaBankCallbackResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        ChinaBankQueryOrderParam param = new ChinaBankQueryOrderParam();
        param.setMchId("104110070110225");
        param.setPosNo("11000221");
        param.setKey("1AC$@!BGT98aw#C");
        param.setOutTradeNo("alipay1422444552");
        param.setOrgSysTrace("1100022120190506142500000340");

        ChinaBankQueryOrderRequest request = new ChinaBankQueryOrderRequest(param);
        ChinaBankCallbackResult result = request.doPostForObject();
        System.out.println(result.toString());
    }

}
