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
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayPayMode;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayBarcodeParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayMircoPayResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnionpayBarcodeRequest extends UnionpayBaseRequest {

    private final static Logger LOGGER = LogManager.getLogger();

    private UnionpayBarcodeParam param;

    public void setParam(UnionpayBarcodeParam param) {
        this.param = param;
    }

    public UnionpayBarcodeRequest(UnionpayBarcodeParam param) {
        this.param = param;
        this.uri = "/pay";
        this.merchantCode = param.getMerchantCode();
        this.terminalCode = param.getTerminalCode();
        this.merchantOrderId = param.getMerchantOrderId();
    }

    /**
     * @return
     * @author:xionghui
     * @createTime:2018年8月28日 下午3:18:27
     */
    @Override
    public Map<String, String> getParam() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("merchantRemark", param.getMerchantRemark());
        map.put("payCode", param.getPayCode());
        map.put("payMode", UnionpayPayMode.CODE_SCAN.getValue());
        map.put("transactionAmount", param.getTransactionAmount());
        map.put("orderDesc", param.getBody());
        map.put("transactionCurrencyCode", "156");
        return map;
    }

    /**
     * 请求返回值
     *
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws URISyntaxException
     * @throws IOException
     * @author:xionghui
     * @createTime:2018年8月28日 下午3:02:45
     */
    public UnionpayMircoPayResult doRequestForObject() {
        UnionpayMircoPayResult result = new UnionpayMircoPayResult();
        try {
            String requestResult = doRequest();
            result = JSONObject.parseObject(requestResult, UnionpayMircoPayResult.class);
        } catch (Exception e) {
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }
        return result;
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException,
            IOException {
        UnionpayBarcodeParam param = new UnionpayBarcodeParam();
        param.setMerchantCode("898369153310250");
        param.setTerminalCode("9C984201");
        param.setMerchantOrderId("wx_12345671122");
        param.setMerchantRemark("测试撤销");
        param.setPayCode("134640496968170720");
        param.setTransactionAmount("1");
        param.setBody("测试12457");

        UnionpayBarcodeRequest request = new UnionpayBarcodeRequest(param);

        UnionpayMircoPayResult result = request.doRequestForObject();

        System.out.println(result.toString());

    }

}
