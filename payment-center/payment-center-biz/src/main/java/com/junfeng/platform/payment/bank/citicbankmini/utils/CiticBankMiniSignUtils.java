package com.junfeng.platform.payment.bank.citicbankmini.utils;

import java.util.Map;

import com.junfeng.platform.payment.bank.citicbank.utils.MD5;
import com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil;
import com.junfeng.platform.payment.bank.citicbank.utils.SignUtils;
import com.junfeng.platform.payment.bank.citicbank.utils.TanslateUtil;
import org.apache.commons.codec.binary.Base64;

public class CiticBankMiniSignUtils {

    // 请求时根据不同签名方式去生成不同的sign
    public static String getSign(String signType, Map<String, String> map, String privateKey) {
        Map<String, String> params = com.junfeng.platform.payment.bank.citicbank.utils.SignUtils.paraFilter(map);
        String preStr = TanslateUtil.mapToString(params);
        if ("RSA_1_256".equals(signType)) {
            try {
                return sign(preStr, "RSA_1_256", privateKey);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            return MD5.sign(preStr, "&key=" + privateKey, "utf-8");
        }
        return null;
    }

    // 对返回参数的验证签名
    public static boolean verifySign(String sign, String signType, Map<String, String> resultMap, String privateKey)
            throws Exception {
        if ("RSA_1_256".equals(signType)) {
            Map<String, String> Reparams = com.junfeng.platform.payment.bank.citicbank.utils.SignUtils.paraFilter(resultMap);
            StringBuilder Rebuf = new StringBuilder((Reparams.size() + 1) * 10);
            com.junfeng.platform.payment.bank.citicbank.utils.SignUtils.buildPayParams(Rebuf, Reparams, false);
            String RepreStr = Rebuf.toString();
            if (verifySign(RepreStr, sign, "RSA_1_256", privateKey)) {
                return true;
            }
        } else if ("MD5".equals(signType)) {
            if (SignUtils.checkParam(resultMap, privateKey)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verifySign(String preStr, String sign, String signType, String platPublicKey)
            throws Exception {
        // 调用这个函数前需要先判断是MD5还是RSA
        // 商户的验签函数要同时支持MD5和RSA
        com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.SignatureSuite suite = null;

        if ("RSA_1_1".equals(signType)) {
            suite = com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.SignatureSuite.SHA1;
        } else if ("RSA_1_256".equals(signType)) {
            suite = com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.SignatureSuite.SHA256;
        } else {
            throw new Exception("不支持的签名方式");
        }

        boolean result = com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.verifySign(suite, preStr.getBytes("UTF8"), Base64.decodeBase64(sign.getBytes("UTF8")),
                platPublicKey);

        return result;
    }

    public static String sign(String preStr, String signType, String mchPrivateKey) throws Exception {
        com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.SignatureSuite suite = null;
        if ("RSA_1_1".equals(signType)) {
            suite = com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.SignatureSuite.SHA1;
        } else if ("RSA_1_256".equals(signType)) {
            suite = com.junfeng.platform.payment.bank.citicbank.utils.RSAUtil.SignatureSuite.SHA256;
        } else {
            throw new Exception("不支持的签名方式");
        }
        byte[] signBuf = RSAUtil.sign(suite, preStr.getBytes("UTF8"), mchPrivateKey);
        return new String(Base64.encodeBase64(signBuf), "UTF8");
    }

}
