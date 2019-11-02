package com.junfeng.platform.payment.bank.constructionbank.request;

import com.junfeng.platform.payment.common.PrintStackTraceUtils;
import com.junfeng.platform.payment.common.httpresp.RequestResultCode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankQueryOrderParam;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankQueryOrderResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConstructionBankQueryOrderRequest extends ConstructionBankBaseRequest {

    private final static Logger LOGGER = LogManager.getLogger();

    private ConstructionBankQueryOrderParam param;

    /**
     * @param param
     */
    public ConstructionBankQueryOrderRequest(ConstructionBankQueryOrderParam param) {
        this.param = param;
        this.mchId = param.getMchId();
        this.posId = param.getPosId();
        this.key = param.getKey();
        this.outTradeNo = param.getOutTradeNo();
    }

    @Override
    public Map<String, String> getParams() {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<String, String>();
        map.put("QRYTIME", "1");
        map.put("QRCODETYPE", param.getQrCodeType());
        map.put("TXCODE", "PAY101");
        return map;
    }

    public ConstructionBankQueryOrderResult doRequestForObject() throws IOException, URISyntaxException {
        ConstructionBankQueryOrderResult result = new ConstructionBankQueryOrderResult();
        try {
            String requestResult = doRequest();
            result = JSONObject.parseObject(requestResult, ConstructionBankQueryOrderResult.class);
        } catch (Exception e) {
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }
        return result;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        ConstructionBankQueryOrderParam param = new ConstructionBankQueryOrderParam();
        param.setMchId("105000253317176");
        param.setPosId("028454099");
        param.setKey("1e1cbf11b8fa8e3ac5304423020111");
        param.setOutTradeNo("123241234");
        param.setQrCodeType("2");

        ConstructionBankQueryOrderRequest request = new ConstructionBankQueryOrderRequest(param);
        ConstructionBankQueryOrderResult result = request.doRequestForObject();

        System.out.print(result.toString());
    }

}
