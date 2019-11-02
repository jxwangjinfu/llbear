package com.junfeng.platform.payment.bank.unionpaymini.request;

import java.io.IOException;
import java.net.URISyntaxException;

import com.junfeng.platform.payment.bank.unionpaymini.model.UnionpayMiniRefundResult;
import com.junfeng.platform.payment.bank.unionpaymini.utils.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.common.AsJsonMapper;

@Component
public class UnionpayMiniRefundRequest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 小程序退款请求
     *
     * @param obj
     * @return
     * @author:xionghui
     * @throws IOException
     * @createTime:2018年10月29日 下午10:13:04
     */
    public UnionpayMiniRefundResult miniRefundRequest(String obj) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(obj, headers);
        String result = restTemplate.postForObject(UnionpayMiniConstant.HOST, formEntity, String.class);
        LOGGER.info("refund result:{}",result);
        return AsJsonMapper.fromJson(result, UnionpayMiniRefundResult.class);

    }

    public UnionpayMiniRefundResult getMiniRefundRequest(String obj) throws IOException, URISyntaxException {
        String result = HttpUtils.doPost(obj);
        LOGGER.info("refund result:{}",result);
        return AsJsonMapper.fromJson(result, UnionpayMiniRefundResult.class);
    }



}
