package com.junfeng.platform.payment.bank.unionpay.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.unionpay.utils.UnionpaySignUtils;
import com.junfeng.platform.payment.bank.unionpay.utils.UnionpayValidUtils;
import com.junfeng.platform.payment.bank.unionpay.utils.UnionpayWebUtils;

public abstract class UnionpayBaseRequest {

    /**
     * 商户号
     */
    protected String merchantCode;

    /**
     * 终端号
     */
    protected String terminalCode;

    /**
     * 商户订单号
     */
    protected String merchantOrderId;

    /**
     * uri
     */
    protected String uri;


    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 普通参数
     *
     * @return
     * @author:xionghui
     * @createTime:2018年8月28日 上午10:45:46
     */
    public abstract Map<String,String> getParam();

    /**
     * 公共参数
     *
     * @return
     * @author:xionghui
     * @createTime:2018年8月28日 上午10:46:27
     */
    public Map<String,String> getShareParam(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("merchantCode", merchantCode);
        map.put("terminalCode", terminalCode);
        map.put("merchantOrderId", merchantOrderId);
        return map;
    }

    /**
     * 获取签名
     *
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @author:xionghui
     * @createTime:2018年8月28日 上午11:19:18
     */
    public String getSign() throws InvalidKeyException, NoSuchAlgorithmException {
        return UnionpaySignUtils.getAuth(getRequestParam());
    }

    /**
     * 请求参数
     *
     * @return
     * @author:xionghui
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @createTime:2018年8月28日 上午11:21:29
     */
    public Map<String,String> getRequestParam() throws InvalidKeyException, NoSuchAlgorithmException{
        Map<String,String> map = getParam();
        map.putAll(getShareParam());
        //map.put("Authorization", getSign());
        return map;
    }

    /**
     * 参数格式验证
     */
    public String valid() {
        return UnionpayValidUtils.valid(this);
    }

    public String doRequest() throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, IOException {
        String errorMsg = this.valid();
        if (errorMsg != null && !"".equals(errorMsg)) {
            throw new RuntimeException("参数校验错误: " + errorMsg);
        }

        return UnionpayWebUtils.doPost(this,getSign());
    }

}
