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
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayQueryOrderParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayQueryOrderResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 银联查询订单请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月28日 下午7:07:05
 * @version 1.0
 */
public class UnionpayQueryOrderRequest extends UnionpayBaseRequest {

    private final static Logger LOGGER = LogManager.getLogger();


    public UnionpayQueryOrderRequest(UnionpayQueryOrderParam param) {
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
    public UnionpayQueryOrderResult doRequestForObject() {
        UnionpayQueryOrderResult result = new UnionpayQueryOrderResult();
        try{
            String requestResult = doRequest();
            result = JSONObject.parseObject(requestResult, UnionpayQueryOrderResult.class);
        }catch (Exception e){
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }

        return result;
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, IOException {
        UnionpayQueryOrderParam param = new UnionpayQueryOrderParam();
        param.setMerchantCode("123456789900081");
        param.setTerminalCode("00810001");
        param.setMerchantOrderId("wx_1234567777822331346941234134572339829058568");

        UnionpayQueryOrderRequest request = new UnionpayQueryOrderRequest(param);
        UnionpayQueryOrderResult result = request.doRequestForObject();
        System.out.println(result.toString());
    }

}
