package com.junfeng.platform.payment.bank.constructionbank.request;

import com.junfeng.platform.payment.common.PrintStackTraceUtils;
import com.junfeng.platform.payment.common.httpresp.RequestResultCode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankBarcodeParam;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankBarcodeResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 建行b扫c支付请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月26日 上午10:47:04
 * @version 1.0
 */
public class ConstructionBankBarcodeRequest extends ConstructionBankBaseRequest {

    private final static Logger LOGGER = LogManager.getLogger();

    private ConstructionBankBarcodeParam param;

    /**
     * @param param
     */
    public ConstructionBankBarcodeRequest(ConstructionBankBarcodeParam param) {
        this.param = param;
        this.mchId = param.getMchId();
        this.posId = param.getPosId();
        this.outTradeNo = param.getOutTradeNo();
        this.key = param.getKey();
    }

    @Override
    public Map<String, String> getParams() {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<String, String>();
        map.put("QRCODE", param.getQrCode());
        map.put("AMOUNT", param.getAmount().toString());
        map.put("TXCODE", "PAY100");
        map.put("PROINFO", param.getBody());
        return map;
    }

    public ConstructionBankBarcodeResult doRequestForObject() {
        ConstructionBankBarcodeResult result = new ConstructionBankBarcodeResult();
        try {
            String requestResult = doRequest();
//            String requestResult = "{\"RESULT\":\"N\",\n" + "\t\"ORDERID\":\"201907010933166387875172\",\n"
//                    + "\t\"AMOUNT\":\"\",\n" + "\t\"WAITTIME\":\"\",\n" + "\t\"ERRCODE\":\"ZTLAP101AL03\",\n"
//                    + "\t\"ERRMSG\":\"未知错误，@@20000~~Service Currently Unavailable~~aop.ACQ.SYSTEM_ERROR~~系统异常@@\",\n"
//                    + "\t\"SIGN\":\"\"}";
            result = JSONObject.parseObject(requestResult, ConstructionBankBarcodeResult.class);
        } catch (Exception e) {
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }
        return result;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
         ConstructionBankBarcodeParam param = new ConstructionBankBarcodeParam();
         param.setMchId("105910200228382");
         param.setPosId("010899025");
         param.setKey("be24b49ef36be2aa791c169d020111");
         param.setOutTradeNo("123241221277777");
         param.setAmount(0.01);
         param.setQrCode("");

         ConstructionBankBarcodeRequest request = new ConstructionBankBarcodeRequest(param);
         ConstructionBankBarcodeResult result = request.doRequestForObject();

         System.out.print(result.toString());
        // // String key =
        // //
        // "30819d300d06092a864886f70d010101050003818b0030818702818100ac787da981f6121dd4bc77f4e5b2175a86b8309639eefb2d0e72e107ed0908610ca86e5fc3929cb3a48323dbe36bcab405b0cd5036127a9f343c65b86f0f50eaa39a255ef516a5e6660beec3c98f7c17de52b7d220e192c8e46a0b1bb96ed56b6f17df4befd5f8fcc9572b5dcf98f0cc3fd3a11bbe24b49ef36be2aa791c169d020111";
        // // key = key.substring(key.length()-30, key.length());
        // // System.out.println(key.length());
//        ConstructionBankBarcodeResult result = doRequestForObject();
//        System.out.println("errorCode:" + result.getErrCode() + "errorMsg:" + result.getErrMsg());
    }

}
