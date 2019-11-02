package com.junfeng.platform.payment.bank.jxbank.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;



public class JxBankSignUtils {

    /**
     * 各家银行使用不同的签名方式
     *
     * @param map
     * @return
     * @author:xionghui
     * @createTime:2018年8月17日 下午2:53:40
     */
//    public static String createSign(Map<String, String> map)  {
//        String type = map.get("type");
//        String sign = "";
        // 招行生成签名
//        if (type.equals(BankTypeEnum.CMBANK.getValue())) {
//            sign = createSign(map, BankConstant.CM_BANK_KEY);
//            // 江西银行签名
//        } else if (type.equals(BankTypeEnum.JXBANK.getValue())) {
//            sign = createSign(map, BankConstant.JX_BANK_KEY);
//            // 中信银行签名
//        } else if (type.equals(BankTypeEnum.CCBANK.getValue())) {
//            sign = createSign(map, BankConstant.CC_BANK_KEY);
//            // 建设银行签名
//        } else if (type.equals(BankTypeEnum.ECBANK.getValue())) {
//            sign = createEcBankSign(map, BankConstant.EC_BANK_KEY);
//        }
//        return createSign(map, JxBankConstant.JX_BANK_KEY);
//    }

//    /**
//     * 建设银行加密
//     *
//     * @param map
//     * @param ecBankKey
//     * @return
//     * @author:xionghui
//     * @throws UnsupportedEncodingException
//     * @throws InvalidAlgorithmParameterException
//     * @throws NoSuchProviderException
//     * @throws BadPaddingException
//     * @throws IllegalBlockSizeException
//     * @throws ShortBufferException
//     * @throws NoSuchPaddingException
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeyException
//     * @createTime:2018年8月17日 下午3:52:12
//     */
//    private static String createEcBankSign(Map<String, String> map, String ecBankKey)
//            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, ShortBufferException,
//            IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidAlgorithmParameterException,
//            UnsupportedEncodingException {
//        // 拼接参数
//        StringBuffer params = new StringBuffer();
//        for (Entry<String, String> o : map.entrySet()) {
//            if (!"sign".equals(o.getKey())) {
//                params.append(o.getKey() + "=" + o.getValue() + "&");
//            }
//        }
//        String result = params.deleteCharAt(params.length() - 1).toString();
//
//        // 创建加密对象，向构造函数传入密钥
//        MCipherEncryptor ccbEncryptor = new MCipherEncryptor(ecBankKey);
//        // 加密
//        result = ccbEncryptor.doEncrypt(result);
//        return result;
//    }

    /**
     * 生成签名
     *
     * @param map
     * @param bankKey
     * @return
     * @author:xionghui
     * @createTime:2018年8月17日 下午3:01:10
     */
    public static String createSign(Map<String, String> map, String bankKey) {
        // 参数排序
        map = sortParams(map);
        String params = "";
        // 拼接参数
        for (Entry<String, String> entry : map.entrySet()) {
            // type只作为判断哪家银行，不作为传递参数
            if (StringUtils.isNotEmpty(entry.getValue())) {
                if (!entry.getKey().equals("type")) {
                    params += entry.getKey() + "=" + entry.getValue() + "&";
                }
            }

        }

        params += "key=";

        return MD5.sign(params, bankKey, "utf-8");
    }

    /**
     * 参数按ASCII字母大小排序
     *
     * @param map
     * @return
     * @author:xionghui
     * @createTime:2018年8月17日 下午3:02:55
     */
    private static Map<String, String> sortParams(Map<String, String> map) {
        if (map == null) {
            return null;
        }

        Map<String, String> m = new TreeMap<String, String>();
        for (Entry<String, String> o : map.entrySet()) {
            m.put(o.getKey(), o.getValue());
        }
        return m;
    }

    /**
     * 校验返回签名是否与加密签名一致
     *
     * @param sign
     * @param params
     * @return
     * @author:xionghui
     * @createTime:2018年8月18日 上午10:27:25
     */
    public boolean checkSign(String sign, Map<String, String> params) {
        return params.containsKey("sign") && params.get("sign").equals(sign);
    }

}
