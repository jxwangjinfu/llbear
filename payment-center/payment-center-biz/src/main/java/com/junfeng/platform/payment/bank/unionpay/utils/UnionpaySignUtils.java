package com.junfeng.platform.payment.bank.unionpay.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junfeng.platform.payment.bank.unionpay.common.UnionpayConstant;
import com.junfeng.platform.payment.common.RandomUtils;

public class UnionpaySignUtils {

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * 随机数
     */
    public static String nonce = RandomUtils.generateLowerString(8);

    private static String testSHA256(InputStream is) {
        try {
            return DigestUtils.sha256Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] hmacSHA256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data);
    }

    /**
     * 获得签名
     *
     * @param map
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @author:xionghui
     * @createTime:2018年8月28日 上午11:06:53
     */
    public static String getAuth(Map<String, String> map) throws InvalidKeyException, NoSuchAlgorithmException {
        // 去除值为空的键值
        //map = removeValueIsNull(map);
        // 将map转化为json字符串

        String body = JSONObject.toJSONString(map);

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");



        String timestamp = timeFormat.format(new Date());


        byte[] data = body.getBytes();
        InputStream is = new ByteArrayInputStream(data);
        String bodyDigest = testSHA256(is);
        String str1_C = UnionpayConstant.APPID + timestamp + nonce + bodyDigest;

        byte[] localSignature = hmacSHA256(str1_C.getBytes(), UnionpayConstant.APPKEY.getBytes());
        String localSignatureStr = Base64.encodeBase64String(localSignature);
        LOGGER.info("signature:\n" + localSignatureStr);

        return ("OPEN-BODY-SIG AppId=" + "\"" + UnionpayConstant.APPID + "\"" + ", Timestamp=" + "\"" + timestamp + "\""
                + ", Nonce=" + "\"" + nonce + "\"" + ", Signature=" + "\"" + localSignatureStr + "\"");

    }

    /**
     * 从map中去除值为空的键值
     *
     * @param map
     * @return
     * @author:xionghui
     * @createTime:2018年8月28日 上午11:01:40
     */
    public static Map<String, String> removeValueIsNull(Map<String, String> map) {
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            if(StringUtils.isBlank(map.get(key))) {
                iterator.remove();
                map.remove(key);
            }
        }
        return map;
    }
}
