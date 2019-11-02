package com.junfeng.platform.payment.bank.cmbank.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import com.junfeng.platform.payment.bank.cmbank.model.CmBankQueryOrderParam;
import com.junfeng.platform.payment.bank.cmbank.model.CmBankQueryOrderResult;
import com.junfeng.platform.payment.common.AsXmlMapMapper;

/**
 * 招商银行查询订单请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月23日 下午4:18:57
 * @version 1.0
 */
public class CmBankQueryOrderRequest extends CmBankBaseRequest{

    private CmBankQueryOrderParam param;

    public CmBankQueryOrderParam getParam() {
        return param;
    }

    public void setParam(CmBankQueryOrderParam param) {
        this.param = param;
    }

    public CmBankQueryOrderRequest(CmBankQueryOrderParam param) {
        this.service = "unified.trade.query";
        this.param = param;
        this.key = param.getKey();
        this.mchId = param.getMchId();
        this.outTradeNo = param.getOutTradeNo();
    }

    @Override
    public Map<String, String> getParams() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 返回结果
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @author:xionghui
     * @createTime:2018年8月23日 下午4:55:24
     */
    public CmBankQueryOrderResult doPostForObject() throws IOException, URISyntaxException {
        String result = doRequest();
        return AsXmlMapMapper.fromXml("CmBankQueryOrderResult",result, CmBankQueryOrderResult.class);
    }

}
