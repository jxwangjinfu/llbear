package com.junfeng.platform.payment.bank.abcbank.request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.abcbank.common.AbcBankCallbackResultCodeEnum;
import com.junfeng.platform.payment.bank.abcbank.common.AbcBankServiceCodeEnum;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankQueryOrderParam;
import com.junfeng.platform.payment.bank.abcbank.model.AbcBankQueryOrderResult;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.alibaba.fastjson.JSON;

/**
 * 农业银行查询请求
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 下午3:57:55
 * @version 1.0
 */
public class AbcBankQueryOrderRequest extends BaseAbcBankRequest {

    private AbcBankQueryOrderParam param;

    public AbcBankQueryOrderRequest(AbcBankQueryOrderParam param) {
        this.param = param;
        this.mchPrivateKey = param.getMchPrivateKey();
        this.transType = AbcBankServiceCodeEnum.QUERY.getValue();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>();
        // map.put("qr_code", param.getAuthCode());
        map.put("mer_id", param.getMchId());
        map.put("term_id", param.getTerminateCode());
        map.put("out_trade_no", param.getOutTradeNo());
        // map.put("order_amt", param.getAmount());
        map.put("trade_date", DateFormatUtils.format(new Date(), "yyyyMMdd"));
        map.put("trade_time", DateFormatUtils.format(new Date(), "HHmmss"));
        return map;
    }

    /**
     * 查询返回结果
     *
     * @return
     * @author:xiongh
     * @throws Exception
     * @createTime:2019年3月14日 下午4:18:32
     */
    public AbcBankQueryOrderResult doPostForObject() throws Exception {
        AbcBankQueryOrderResult result = new AbcBankQueryOrderResult();
        String requstResult = getRecvMessage();
        if (StringUtils.equals(AbcBankCallbackResultCodeEnum.FAIL.getValue(), requstResult)) {
            result.setReturnCode(AbcBankCallbackResultCodeEnum.FAIL.getValue());
            result.setReturnMsg(AbcBankCallbackResultCodeEnum.FAIL.getDescription());
        } else {
            result = JSON.parseObject(requstResult, AbcBankQueryOrderResult.class);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        AbcBankQueryOrderParam param = new AbcBankQueryOrderParam();
        String bankPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgYbfWxWs4vIbRWEyGZtHMOqBX\r\n"
                + "YHNX5zIn72m72tFRc5s3go6ev3apYHh5XjciBo/GMF/kf6NaCD8o1OkBeHw/C7ZE\r\n"
                + "kQXWp4tusIBsD626KCQOp//UUxovrXiBEF1Rbgmq5H0VDap1pzL0bFUrpAliHGDl\r\n" + "IW8+8l3Vgt48gejQIQIDAQAB";
        String mchPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBht9bFazi8htFY\n"
                + "TIZm0cw6oFdgc1fnMifvabva0VFzmzeCjp6/dqlgeHleNyIGj8YwX+R/o1oIPyjU\n"
                + "6QF4fD8LtkSRBdani26wgGwPrbooJA6n/9RTGi+teIEQXVFuCarkfRUNqnWnMvRs\n"
                + "VSukCWIcYOUhbz7yXdWC3jyB6NAhAgMBAAECgYAB1nzT0jlTUh29YmSmGaGH1Jgn\n"
                + "yCwqMrUJbh3pEC3b4BZEQZO+z/TdJgTbZy/8jNSy8bT3mlrV2khXAHGXGblavvMU\n"
                + "EAQwO9yuXzfaPz+Q00zKpo43sd3GYMR6OjzYTLGpaXjA3j6+K48f83d83KP4DiOK\n"
                + "vtLhMoIQ2wieTr990QJBANGdBNng4ys2pOWNbBNpum+ITIHkYwuCeuckmjiQU3OS\n"
                + "kA77cwYV7WjnvrwwFWN/ioBTUi1U6Uy3ty/PuaHX4dUCQQDD36Lg/KRidBHApXY8\n"
                + "aib3zTPiUTdNOjjZzp6sfAVLREcaTrnkQuutUdp4xd6lJ8XY3eP1QNjA1FqgRkyM\n"
                + "9c8dAkBvtd3y+/41do7U49TYV72LWTlk9EGwzZ4Qd5pVUN2Yt2BdC7cWZDM9eFxX\n"
                + "UMzlVQCMOVuPWzRmtXZEXdFRportAkBWwFvBTeP4fvPF4QKm61Tq94V9PP9DQwAe\n"
                + "53Y7aopltY3QRwHp6QG8t74NM4XSm/TyggQOgXP+o46n4brRy6S5AkEAnUXLbBBA\n"
                + "S+Sa7jHRXjAUgas8eEGLO5zG5T8aEk2f0QnZE0oAAgHH6PcnrthZRqM6RCEt32ww\n" + "d5Fqo3DFkRFFdA==";
        param.setBankPublicKey(bankPublicKey);
        param.setMchPrivateKey(mchPrivateKey);
        param.setMchId("103360550727546");
        param.setTerminateCode("12059590");
        param.setAmount("1");
        param.setOutTradeNo("ali1234455778855");
        AbcBankQueryOrderRequest request = new AbcBankQueryOrderRequest(param);
        AbcBankQueryOrderResult result = request.doPostForObject();
        System.out.println(result.toString());
    }

}
