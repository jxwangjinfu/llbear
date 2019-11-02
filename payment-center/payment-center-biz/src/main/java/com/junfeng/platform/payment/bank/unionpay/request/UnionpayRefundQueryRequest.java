package com.junfeng.platform.payment.bank.unionpay.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundQueryParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundQueryResult;


public class UnionpayRefundQueryRequest extends UnionpayBaseRequest{

    public UnionpayRefundQueryRequest(UnionpayRefundQueryParam param) {
        this.merchantCode = param.getMerchantCode();
        this.merchantOrderId = param.getMerchantOrderId();
        this.terminalCode = param.getTerminalCode();
        this.uri = "/query";
    }

    @Override
    public Map<String, String> getParam() {
        return new HashMap<String,String>();
    }

    /**
     * 获得请求返回值
     *
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws URISyntaxException
     * @throws IOException
     * @author:xionghui
     * @createTime:2018年8月28日 下午7:30:11
     */
    public UnionpayRefundQueryResult doRequestForObject()
            throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, IOException {
        String result = doRequest();
        return JSONObject.parseObject(result, UnionpayRefundQueryResult.class);
    }

}
