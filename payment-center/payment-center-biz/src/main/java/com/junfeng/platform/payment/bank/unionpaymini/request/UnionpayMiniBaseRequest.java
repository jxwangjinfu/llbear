package com.junfeng.platform.payment.bank.unionpaymini.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.unionpaymini.common.UnionpayMiniConstant;
import com.junfeng.platform.payment.bank.unionpaymini.utils.UnionpayMiniSignUtils;
import com.junfeng.platform.payment.bank.unionpaymini.utils.UnionpayMiniValidUtils;
import com.junfeng.platform.payment.bank.unionpaymini.utils.UnionpayMiniWebUtils;

public abstract class UnionpayMiniBaseRequest {

    /**
    * 商户号 必传
    */
    protected String mid;

    /**
     * 终端号 必传
     */
    protected String tid;

    /**
     * 机构商户号 必传
     */
    protected String instMid;

    /**
     * 商户订单号 必传
     */
    protected String merOrderId;

    /**
     * uri
     */
    protected String uri;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getInstMid() {
        return instMid;
    }

    public void setInstMid(String instMid) {
        this.instMid = instMid;
    }

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
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
    /**
     * 普通参数
     * @return
     * @author:李麒
     * @createTime:2018年10月25日 下午2:33:28
     */
    public abstract Map<String,String> getParam();

    /**
     * 公共参数
     * @return
     * @author:李麒
     * @createTime:2018年10月25日 下午2:33:42
     */
    public Map<String,String> getShareParam(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("mid", mid);
        map.put("tid", tid);
        map.put("instMid", instMid);
        map.put("merOrderId", merOrderId);
        return map;
    }

    /**
     * 获取签名
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @author:李麒
     * @createTime:2018年10月25日 下午2:36:06
     */
    public String getSign() throws InvalidKeyException, NoSuchAlgorithmException {
        Map<String,String> map = getShareParam();
        map.putAll(getParam());
        return UnionpayMiniSignUtils.makeSign(UnionpayMiniConstant.MD5KEY, map);
    }

    /**
     * 请求参数
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @author:李麒
     * @createTime:2018年10月25日 下午2:36:19
     */
    public Map<String,String> getRequestParam() throws InvalidKeyException, NoSuchAlgorithmException{
        Map<String,String> map = getParam();
        map.putAll(getShareParam());
        //map.put("Authorization", getSign());
        map.put("sign", getSign());
        return map;
    }

    /**
     * 参数格式验证
     */
    public String valid() {
        return UnionpayMiniValidUtils.valid(this);
    }

    public String doRequest() throws InvalidKeyException, NoSuchAlgorithmException, URISyntaxException, IOException {
        String errorMsg = this.valid();
        if (errorMsg != null && !"".equals(errorMsg)) {
            throw new RuntimeException("参数校验错误: " + errorMsg);
        }

        return UnionpayMiniWebUtils.doPost(this);
    }

}
