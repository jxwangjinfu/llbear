package com.junfeng.platform.payment.bank.unionpaymini.request;


import java.io.IOException;

import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.common.AsJsonMapper;

/**
 * 小程序支付结果查询请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年10月29日 下午3:29:16
 * @version 1.0
 */
@Component
public class UnionpayMiniQueryResultRequest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 小程序支付查询请求
     *
     * @param obj
     * @return
     * @throws IOException
     * @author:xionghui
     * @createTime:2018年10月29日 下午4:00:53
     */
    public UnionpayMiniQueryResult miniQueryResult(String obj) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(obj, headers);
        String result = restTemplate.postForObject(UnionpayMiniConstant.HOST, formEntity, String.class);
        return AsJsonMapper.fromJson(result, UnionpayMiniQueryResult.class);
    }

}
