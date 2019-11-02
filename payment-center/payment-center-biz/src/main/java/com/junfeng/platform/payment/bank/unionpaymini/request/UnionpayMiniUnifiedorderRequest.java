package com.junfeng.platform.payment.bank.unionpaymini.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniPayResult;
import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniUnifiedorderParam;

/**
 * 银联统一下单请求
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年10月24日 下午7:52:53
 * @version 1.0
 */
public class UnionpayMiniUnifiedorderRequest extends UnionpayMiniBaseRequest {

    private UnionpayMiniUnifiedorderParam param;

    public void setParam(UnionpayMiniUnifiedorderParam param) {
        this.param = param;
    }

    public UnionpayMiniUnifiedorderRequest(UnionpayMiniUnifiedorderParam param) {
        this.param = param;
//        this.uri = "/pay";
        this.mid = param.getMid();
        this.tid = param.getTid();
        this.instMid = param.getInstMid();
        this.merOrderId = param.getMerOrderId();
        this.uri = UnionpayMiniConstant.HOST;
    }

    /**
     *
     * @return
     * @author:李麒
     * @createTime:2018年10月25日 下午3:11:29
     */
    @Override
    public Map<String, String> getParam() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("msgType", param.getMsgType());
        map.put("msgSrc", param.getMsgSrc());
        map.put("totalAmount", param.getTotalAmount());
        map.put("requestTimestamp", param.getRequestTimestamp());

        //map.put("notifyUrl", param.getNotifyUrl());
        map.put("tradeType", "MINI");
        map.put("subOpenId", param.getSubOpenId());
        return map;
    }

    /**
     * 请求返回值
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws URISyntaxException
     * @throws IOException
     * @author:李麒
     * @createTime:2018年10月25日 下午3:11:45
     */
    public UnionpayMiniPayResult doRequestForObject()
            throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, IOException {
        String result = doRequest();
        return JSONObject.parseObject(result, UnionpayMiniPayResult.class);
    }

}
