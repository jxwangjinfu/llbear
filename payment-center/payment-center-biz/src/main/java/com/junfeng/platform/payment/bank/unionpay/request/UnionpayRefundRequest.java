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
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundParam;
import com.junfeng.platform.payment.bank.unionpay.model.UnionpayRefundResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 银联退款请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月6日 上午10:54:11
 * @version 1.0
 */
public class UnionpayRefundRequest extends UnionpayBaseRequest {

    private final static Logger LOGGER = LogManager.getLogger();

    private UnionpayRefundParam param;

    public void setParam(UnionpayRefundParam param) {
        this.param = param;
    }

    public UnionpayRefundRequest(UnionpayRefundParam param) {
        this.param = param;
        this.uri = "/refund";
        this.merchantCode = param.getMerchantCode();
        this.terminalCode = param.getTerminalCode();
    }

    @Override
    public Map<String, String> getParam() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("refundRequestId", this.param.getRefundRequestId());
        map.put("transactionAmount", String.valueOf(this.param.getTransactionAmount()));
        map.put("merchantOrderId", param.getMerchantOrderId());
        return map;
    }

    /**
     * 银联退款返回值
     *
     * @return
     * @author:xionghui
     * @throws IOException
     * @throws URISyntaxException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @createTime:2018年9月6日 上午11:34:23
     */
    public UnionpayRefundResult doRequestForObject() {
        UnionpayRefundResult result = new UnionpayRefundResult();
        try {
            String requestResult = doRequest();
            result = JSONObject.parseObject(requestResult, UnionpayRefundResult.class);
        } catch (Exception e) {
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }
        return result;
    }

}
