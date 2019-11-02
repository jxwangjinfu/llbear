package com.junfeng.platform.payment.bank.chinabank.request;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.chinabank.utils.ChinaBankValidUtils;
import com.junfeng.platform.payment.bank.chinabank.utils.ChinaBankWebUtils;
import com.junfeng.platform.payment.bank.chinabank.utils.MD5;

public abstract class BaseChinaBankRequest {

    /**
     * 设备终端号
     */
    protected String posNo;

    /**
     * 交易类型
     */
    protected String tranType;

    /**
     * 订单号
     */
    protected String outTradeNo;

    /**
     * 商户号
     */
    protected String mchId;

    protected String key;

    public abstract Map<String,String> getParams();

    public Map<String,String> sharedParams(){
        Map<String,String> map = new HashMap<>();
        map.put("posNo", this.posNo);
        map.put("tranType", this.tranType);
        map.put("Mid", this.mchId);
        map.put("MerTradeNo", this.outTradeNo);
        return map;
    }

    /**
     * 签名
     *
     * @return
     * @author:xiongh
     * @createTime:2019年4月4日 下午4:25:44
     */
    public String getSign() {
        Map<String,String> map = sharedParams();
        map.putAll(getParams());
        String jsonValue = JSON.toJSONString(map);
        jsonValue = jsonValue + "&";
        String sign = MD5.sign(jsonValue, this.key, "UTF-8");
        return sign;
    }

    /**
     * 接口请求参数
     *
     * @return
     * @author:xiongh
     * @createTime:2019年4月4日 下午4:26:50
     */
    public Map<String,String> requestParams(){
        Map<String,String> map = sharedParams();
        map.putAll(getParams());
        return map;
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
        return ChinaBankWebUtils.doPost(this);
    }

    /**
     * 参数格式验证
     */
    public String valid() {
        return ChinaBankValidUtils.valid(this);
    }



}
