package com.junfeng.platform.payment.bank.jxbank.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.jxbank.utils.JxBankSignUtils;
import com.junfeng.platform.payment.bank.jxbank.utils.JxBankValidUtils;
import com.junfeng.platform.payment.bank.jxbank.utils.JxBankWebUtils;
import com.junfeng.platform.payment.common.RandomUtils;

public abstract class JxBankBaseRequest {

    protected String uri;

    /**
     * 签名
     */
    protected String sign;

    /**
     * 随机数
     */
    protected String nonceStr = RandomUtils.generateLowerString(8);

    /**
     * 商户ID
     */
    protected String mchId;

    /**
     * 加密KEY值
     */
    protected String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    /**
     * 支付的公共参数
     */
    public Map<String, String> getSharedParams() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mch_id", mchId);
        map.put("nonce_str", nonceStr);
        map.put("sign", sign);
        return map;
    }
    /**
     * 请求参数（包含签名）
     *
     * @return
     */
    public Map<String, String> getRequestParams() {
        Map<String, String> params = this.getSharedParams();
        params.putAll(this.getParams());
        String sign = getSign();
        params.put("sign", sign);
        return params;
    }
    /**
     * request参数
     *
     * @return
     * @author:xionghui
     * @createTime:2018年8月17日 下午2:16:05
     */
    public abstract Map<String, String> getParams();

    /**
     * 参数格式验证
     */
    public String valid() {
        return JxBankValidUtils.valid(this);
    }

    /**
     * 获取签名
     *
     * @return
     * @author:xionghui
     * @createTime:2018年8月17日 下午2:16:54
     */
    public String getSign() {
        Map<String, String> params = this.getSharedParams();
        params.putAll(getParams());
        return JxBankSignUtils.createSign(params,this.key);
    }

    /**
     * 根据request中指定的请求方式发送http请求
     *
     * @return String json字符串, 错误信息或者请求结果, 示例: "{"data":{}}", "{"data":"errorMsg"}"
     * @throws IOException
     * @throws URISyntaxException
     * @throws RuntimeException
     *             如果缺少必要参数,会抛出该异常
     */
    public String doRequest() throws IOException, URISyntaxException {
        String errorMsg = this.valid();
        if (errorMsg != null && !"".equals(errorMsg)) {
            throw new RuntimeException("参数校验错误: " + errorMsg);
        }
        return JxBankWebUtils.doPost(this);

    }

}
