package com.junfeng.platform.payment.bank.postbank.request;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.junfeng.platform.payment.bank.postbank.utils.PostBankSignUtils;
import com.junfeng.platform.payment.bank.postbank.utils.PostBankValidUtils;
import com.junfeng.platform.payment.bank.postbank.utils.PostBankWebUtils;
import com.junfeng.platform.payment.common.RandomUtils;

/**
 * 邮政银行b扫c基础接口
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 上午11:13:00
 * @version 1.0
 */
public abstract class BasePostBankRequest {

    /**
     * 商户ID
     */
    protected String mchId;

    /**
     * 加密key
     */
    protected String key;

    /**
     * 平台分配的appId
     */
    protected String appId;

    /**
     * 签名
     */
    protected String sign;

    /**
     * 地址
     */
    protected String uri;

    /**
     * 随机数
     */
    protected String nonceStr = RandomUtils.generateLowerString(8);

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 公共参数
     *
     * @return
     * @author:xiongh
     * @createTime:2019年3月4日 上午11:16:56
     */
    public Map<String, String> getSharedParams() {
        Map<String, String> sharedParams = new HashMap<>(3);
        sharedParams.put("cusid", mchId);
        sharedParams.put("appid", appId);
        sharedParams.put("randomstr", nonceStr);
        return sharedParams;
    }

    /**
     * 前台所传参数
     *
     * @return
     * @author:xiongh
     * @createTime:2019年3月4日 上午11:19:11
     */
    public abstract Map<String, String> getParams();

    /**
     * 参数签名
     *
     * @return
     * @throws Exception
     * @author:xiongh
     * @createTime:2019年3月4日 上午11:21:39
     */
    public String getSign() throws Exception {
        TreeMap<String, String> allParams = new TreeMap<>(getSharedParams());
        allParams.putAll(getParams());
        String sign = PostBankSignUtils.sign(allParams, key);
        return sign;
    }

    /**
     * 所有参数，包含签名
     *
     * @return
     * @throws Exception
     * @author:xiongh
     * @createTime:2019年3月4日 上午11:26:17
     */
    public Map<String, String> getAllParams() throws Exception {
        Map<String, String> params = getSharedParams();
        params.putAll(getParams());
        params.put("sign", getSign());
        return params;
    }

    /**
     * 参数格式验证
     */
    public String valid() {
        return PostBankValidUtils.valid(this);
    }

    /**
     * 根据request中指定的请求方式发送http请求
     *
     * @return String json字符串, 错误信息或者请求结果, 示例: "{"data":{}}", "{"data":"errorMsg"}"
     * @throws Exception
     * @throws RuntimeException
     *             如果缺少必要参数,会抛出该异常
     */
    public String doRequest() throws Exception {
        String errorMsg = this.valid();
        if (errorMsg != null && !"".equals(errorMsg)) {
            throw new RuntimeException("参数校验错误: " + errorMsg);
        }
        return PostBankWebUtils.doPost(this);

    }

}
