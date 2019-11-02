package com.junfeng.platform.payment.bank.cmbank.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;



public class CmBankSignUtils {

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
