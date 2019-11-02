package com.junfeng.platform.payment.bank.unionpay.request;

import com.junfeng.platform.payment.common.PrintStackTraceUtils;
import com.junfeng.platform.payment.common.httpresp.RequestResultCode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayCancelOrderParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayCancelOrderResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 银联撤销请求接口
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月6日 上午9:37:25
 * @version 1.0
 */
public class UnionpayCancelOrderRequest extends UnionpayBaseRequest {

    private final static Logger LOGGER = LogManager.getLogger();

    private UnionpayCancelOrderParam param;

    public static UnionpayCancelOrderRequest getInstance(UnionpayCancelOrderParam param) {
        return new UnionpayCancelOrderRequest(param);
    }

    private UnionpayCancelOrderRequest(UnionpayCancelOrderParam param) {
        this.param = param;
        this.terminalCode = param.getTerminalCode();
        this.merchantCode = param.getMerchantCode();
        this.uri = "/voidpayment";
    }

    @Override
    public Map<String, String> getParam() {
        Map<String, String> map = new HashMap<>(1);
        map.put("originalOrderId", param.getOriginalOrderId());
        return map;
    }

    /**
     * 返回结果转化为实体类
     *
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws URISyntaxException
     * @throws IOException
     * @author:xiongh
     * @createTime:2019年3月6日 上午9:39:58
     */
    public UnionpayCancelOrderResult doRequestForObject() {
        UnionpayCancelOrderResult result = new UnionpayCancelOrderResult();
        try {
            String requestResult = doRequest();
            result = JSONObject.parseObject(requestResult, UnionpayCancelOrderResult.class);
        } catch (Exception e) {
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }

        return result;
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException,
            IOException {
        UnionpayCancelOrderParam param = new UnionpayCancelOrderParam();
        param.setMerchantCode("123456789900081");
        param.setTerminalCode("00810001");
        param.setOriginalOrderId("20190306144556000017201003");
        // param.setOriginalOrderId("20190306113204000017183219");

        UnionpayCancelOrderRequest request = UnionpayCancelOrderRequest.getInstance(param);
        UnionpayCancelOrderResult result = request.doRequestForObject();
        System.out.println(result.toString());
    }

}
