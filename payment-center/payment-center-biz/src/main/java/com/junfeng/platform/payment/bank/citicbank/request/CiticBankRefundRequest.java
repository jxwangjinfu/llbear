package com.junfeng.platform.payment.bank.citicbank.request;

import com.junfeng.platform.payment.common.PrintStackTraceUtils;
import com.junfeng.platform.payment.common.httpresp.RequestResultCode;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.citicbank.model.CiticBankRefundParam;
import com.junfeng.platform.payment.bank.citicbank.model.CiticBankRefundResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.junfeng.platform.payment.common.AsXmlMapMapper;

/**
 * 中信银行退款请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年11月22日 上午9:41:01
 * @version 1.0
 */
public class CiticBankRefundRequest extends CiticBankBaseRequest{

    private final static Logger LOGGER = LogManager.getLogger();

    private CiticBankRefundParam param;

    /**
     * @param param
     */
    public CiticBankRefundRequest(CiticBankRefundParam param) {
        this.param = param;
        this.service = "unified.trade.refund";
        this.mchId = param.getMchId();
        this.key = param.getKey();
        this.outTradeNo = param.getOutTradeNo();
    }



    @Override
    public Map<String, String> getParams() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("out_refund_no", param.getOutRefundNo());
        map.put("total_fee", param.getTotalFee().toString());
        map.put("refund_fee", param.getRefundFee().toString());
        map.put("op_user_id", param.getMchId());
        return map;
    }

    public CiticBankRefundResult doRequestForObject() throws Exception {
        CiticBankRefundResult result = new CiticBankRefundResult();
        try{
            String requestResult = doRequest();
            result = AsXmlMapMapper.fromXml("CiticBankRefundResult",requestResult,CiticBankRefundResult.class);
        }catch (Exception e){
            LOGGER.error(PrintStackTraceUtils.getErrorInfoFromException(e));
            result.setErrCode(String.valueOf(RequestResultCode.BANK_RETURN_ERROR.getErrorCode()));
            result.setErrCode(RequestResultCode.BANK_RETURN_ERROR.getErrorMessage());
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        CiticBankRefundParam param = new CiticBankRefundParam();
        String key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC9qghJcbAA2kedx/xtkL5zBOZdmBRS2njrEPqUFC2XZG52soNsUpO2MROWii2uVBVwijftODf8qfyRSBpHgkl8x0SpUJoenPAuQMRWfDz+HZqfLqp/oH97um/xBvLIGNR1NnVi3o6sq3BOyUd+PlUZShtoTcJh0I/qFr4nmDdzLntxEPqs3LjvD82AI8f29Sy2WgyGKmqImtyjeFa9+hAjccVndaYpLTSUCc/MIJ4biYOlzKrZK418vZeukHRA2sr52cB7JQx+Cwlm9z+UuNxHntUlOodcYeR+hxVOVoE3qFmmXRYCMbsgITXJ6/uYo5aPTdU+a+OTdal4hljpGzxTAgMBAAECggEBAK8joj9f0i0XY+aZU8pBTPydW7S1yJ6xy9MVfK7c6zTPswm80otmvawSKYpr0r8WOi4QFJDjxx90QRxPPqUl9IJa6FaJbagzN78eY6+RTilCYRP5Xcw5Az/X66LgzbRk6VvLjB5ecdWVAkJer7wuBxsxSZnJpGP62cpKchHSB/I9MrPVHvYjwIXs33jUfptQX3BumLwXEiLPj6A7nCybPGo9tZUIbDGCFLcBPa0N4ViEJRIUInecyv1GHs7+MBSBCgPabq6Zc8AaM359xSXx7fWwebMgZxPdA6O8xD0VPEfEypui1/k6tg3BpxH+Qj4ongeIsvs5uq0aRlbRPiywhlECgYEA9brfEt4KfgkhWIE4Hky2J6JLGiHkZlqhYvNPg324bxvhPjkeVKWxD2nMMwz7pjC2ck51KIbdMeMeWtuDzLAnTbUC7YkjxrEswld4PKUK8gIFj9kWYsmSzR7AlWDkF+0jUdTYO+ZmjjXg+ngZJKb0vlxfVHIbiPef40VS5hwVHPUCgYEAxZdMd6t6w8dTHsC4evk13NRPV3bCk4XYKlKvV5YDK7rURTaFrPYR6rMlM5NUlvgXpgv7HKWTToDWTiejwmGmEbFIAxJSneywCQstEQyZvNvPMv20k3yeAbzr5xwiDuFR4MW6RZz6u9mGsanoEu8qqP1opGY6nq9ofIxsy+wApycCgYEAlNQKubWr6ywCe0VARPG3PYgJLpmqaOepaXHuDbi+VOUw5evYV/JGrAwq9hx96ekFB3RHdzU/kTB6uRLM39Ms6t6gGFgASB4U+tvZYfmgCHV7AuW7rjdh50Qh5hdZ7rfctXpRQO5J3QX67hiBOfa4svblchLuZGV9/DuHewE1NGUCgYBHVv8m3eTvNtJN8ispSz0lRAp5e8l41gRcE4p3RxRR/5yQeMT+08FYSeIOow0RjPWM4BqEF0v+NgoqIanAjt0U2jsz0LO/jStpuH1HAJOpWnuO34cJER9DZWCPCttJIBUd4IYGiE0G8dzt3HiMDYoLhaL4aGiVzqMdBN5rKgR6VQKBgQCXgaZ2Af2ZGaXeD22LEWzAWLBsNqLwGPHDLIW6fWPVfU5WNXitw6Kor/yX3xOZn+XIh0J0DXNFeCOa1tP+RWVRUiYKRtR0ts70Qur3c1XmGxPBR1NTfbIXfYYFz22mBAOw74e4B+RGf9BGHUJV9QVdw6+PKySsiVBFGczLnQhIWw==";
        param.setMchId("401520330698");
        param.setKey(key);
        param.setOutTradeNo("123456789745112222");
        param.setOutRefundNo("12324erwrdf11111111");
        param.setTotalFee(Long.valueOf(1));
        param.setRefundFee(Long.valueOf(1));

        CiticBankRefundRequest request = new CiticBankRefundRequest(param);
        CiticBankRefundResult result = request.doRequestForObject();
        System.out.print(result.toString());

    }

}
