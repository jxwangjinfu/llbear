package com.junfeng.platform.payment.bank.abcbank.utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 农业银行加密工具
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月14日 上午11:15:29
 * @version 1.0
 */
public class AbcBankSignUtils {

    /**
     * 对平台交易参数进行加密方法
     *
     * @param map
     *            交易参数
     * @param bankPublicKey
     *            银行公钥
     * @return
     * @author:xiongh
     * @throws Exception
     * @createTime:2019年3月14日 上午11:20:06
     */
    public static byte[] encrypt(Map<String, String> map, String bankPublicKey) throws Exception {
        String jsonString = JSON.toJSONString(map);
        PublicKey publicKey = RSAUtil.string2PublicKey(bankPublicKey);
        return RSAUtil.BigpublicEncrypt(jsonString.getBytes(), publicKey);
    }

    /**
     * 解密
     *
     * @param recvMsg
     *            返回的结果
     * @param mchPrivateKey
     *            商户私钥解密
     * @return
     * @author:xiongh
     * @throws Exception
     * @createTime:2019年3月14日 下午3:43:17
     */
    public static String decrypt(byte[] recvMsg, String mchPrivateKey) throws Exception {
        PrivateKey privateKey = RSAUtil.string2PrivateKey(mchPrivateKey);
        String result = new String(RSAUtil.BigPrivateDecrypt(recvMsg, privateKey));
        return result;
    }

    /**
     * 对平台参数做签名
     *
     * @param content
     * @param mchPrivateKey
     * @return
     * @author:xiongh
     * @throws Exception
     * @createTime:2019年3月16日 下午1:25:50
     */
    public static String sign(String content,String mchPrivateKey) throws Exception {
        PrivateKey privateKey = RSAUtil.string2PrivateKey(mchPrivateKey);
        String result = RSAUtil.sign2(content.getBytes(), privateKey);
        return result;
    }

}
