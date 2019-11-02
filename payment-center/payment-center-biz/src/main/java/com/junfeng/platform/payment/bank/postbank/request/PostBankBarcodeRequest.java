package com.junfeng.platform.payment.bank.postbank.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.postbank.model.PostBankBarcodeParam;
import com.junfeng.platform.payment.bank.postbank.model.PostBankBarcodeResult;

/**
 * 邮政银行b扫c请求接口
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 下午2:09:26
 * @version 1.0
 */
public class PostBankBarcodeRequest extends BasePostBankRequest {

    private PostBankBarcodeParam param;

    /**
     * 实例方法
     *
     * @param param
     * @return
     * @author:xiongh
     * @createTime:2019年3月4日 下午2:24:46
     */
    public static PostBankBarcodeRequest getInstance(PostBankBarcodeParam param) {
        return new PostBankBarcodeRequest(param);
    }

    private PostBankBarcodeRequest(PostBankBarcodeParam param) {
        this.param = param;
        this.appId = param.getAppId();
        this.key = param.getKey();
        this.mchId = param.getMchId();
        this.uri = "/scanqrpay";
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>(3);
        map.put("trxamt", param.getTotalFee());
        map.put("reqsn", param.getOutTradeNo());
        map.put("authcode", param.getAuthCode());
        map.put("body", param.getBody());
        return map;
    }

    /**
     * 获取返回结果
     *
     * @return
     * @throws Exception
     * @author:xiongh
     * @createTime:2019年3月4日 下午3:06:04
     */
    public PostBankBarcodeResult doRequestForObject() throws Exception {
        String requestResult = doRequest();
        PostBankBarcodeResult result = JSONObject.parseObject(requestResult, PostBankBarcodeResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        PostBankBarcodeParam param = new PostBankBarcodeParam();
        param.setAppId("00000051");
        param.setKey("allinpay888");
        param.setMchId("990581007426001");
        param.setOutTradeNo("wx123477855445644111454578");
        param.setAuthCode("134640810370056054");
        param.setBody("测试---1457");
        param.setTotalFee("1");

        PostBankBarcodeRequest request = PostBankBarcodeRequest.getInstance(param);
        PostBankBarcodeResult result = request.doRequestForObject();
        System.out.println(result.toString());

    }

}
