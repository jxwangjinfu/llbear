package com.junfeng.platform.payment.bank.postbank.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.postbank.model.PostBankQueryOrderParam;
import com.junfeng.platform.payment.bank.postbank.model.PostBankQueryOrderResult;

/**
 * 邮政银行b扫c，查询订单请求
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午3:08:36
 * @version 1.0
 */
public class PostBankQueryOrderRequest extends BasePostBankRequest {

    private PostBankQueryOrderParam param;

    /**
     * 实例化
     *
     * @param param
     * @return
     * @author:xiongh
     * @createTime:2019年3月4日 下午3:33:32
     */
    public static PostBankQueryOrderRequest getInstance(PostBankQueryOrderParam param) {
        return new PostBankQueryOrderRequest(param);
    }

    private PostBankQueryOrderRequest(PostBankQueryOrderParam param)
    {
        this.appId = param.getAppId();
        this.key = param.getKey();
        this.mchId = param.getMchId();
        this.uri = "/query";
        this.param = param;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>(1);
        map.put("reqsn", param.getOutTradeNo());
        return map;
    }

    /**
     * 获得返回结果
     *
     * @return
     * @throws Exception
     * @author:xiongh
     * @createTime:2019年3月4日 下午3:35:50
     */
    public PostBankQueryOrderResult doRequestForObject() throws Exception {
        String requestResult = doRequest();
        PostBankQueryOrderResult result = JSONObject.parseObject(requestResult, PostBankQueryOrderResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        PostBankQueryOrderParam param = new PostBankQueryOrderParam();
        param.setAppId("00000051");
        param.setKey("allinpay888");
        param.setMchId("990581007426001");
        param.setOutTradeNo("wx1234778554456678111");

        PostBankQueryOrderRequest request = PostBankQueryOrderRequest.getInstance(param);
        PostBankQueryOrderResult result = request.doRequestForObject();
        System.out.println(result.toString());

    }

}
