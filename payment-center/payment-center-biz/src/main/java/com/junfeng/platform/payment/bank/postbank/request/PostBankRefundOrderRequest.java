package com.junfeng.platform.payment.bank.postbank.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.postbank.model.PostBankRefundOrderParam;
import com.junfeng.platform.payment.bank.postbank.model.PostBankRefundOrderResult;

/**
 * 邮政银行b扫c
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午3:47:21
 * @version 1.0
 */
public class PostBankRefundOrderRequest extends BasePostBankRequest{

    private PostBankRefundOrderParam param;

    public static PostBankRefundOrderRequest getInstance(PostBankRefundOrderParam param) {
        return new PostBankRefundOrderRequest(param);
    }

    private PostBankRefundOrderRequest(PostBankRefundOrderParam param) {
        this.appId = param.getAppId();
        this.key = param.getKey();
        this.mchId = param.getMchId();
        this.uri = "/refund";
        this.param = param;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String,String> map = new HashMap<>(3);
        map.put("trxamt", param.getRefundAmount());
        map.put("reqsn", param.getRefundOrderNo());
        map.put("oldreqsn", param.getOutTradeNo());
        return map;
    }

    public PostBankRefundOrderResult doRequestForObject() throws Exception {
        String requestResult = doRequest();
        PostBankRefundOrderResult result = JSONObject.parseObject(requestResult, PostBankRefundOrderResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        PostBankRefundOrderParam param = new PostBankRefundOrderParam();
        param.setAppId("00000051");
        param.setKey("allinpay888");
        param.setMchId("990581007426001");
        param.setOutTradeNo("wx1234778554456678111");
        param.setRefundAmount("1");
        param.setRefundOrderNo("wx123456785221111");

        PostBankRefundOrderRequest request = PostBankRefundOrderRequest.getInstance(param);
        PostBankRefundOrderResult result = request.doRequestForObject();
        System.out.println(result.toString());
    }

}
