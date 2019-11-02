package com.junfeng.platform.payment.bank.jxbank.request;

import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.jxbank.model.JxBankOrderQueryResult;
import com.junfeng.platform.payment.common.AsXmlMapMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 江西银行查询订单请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月21日 上午10:29:44
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class JxBankQueryOrderRequest extends JxBankBaseRequest {

    public JxBankQueryOrderRequest(String mchId, String key, String payOrderNo) {
        this.uri = "orderquery";
        this.mchId = mchId;
        this.key = key;
        this.payOrderNo = payOrderNo;
    }

    /**
     * 商户的订单号
     */
    private String payOrderNo;

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("out_trade_no", payOrderNo);
        return map;
    }
    /**
     * 返回对象
     * @return
     * @throws Exception
     * @author:chenyx
     * @createTime:2018年8月22日 上午10:35:07
     */
    public JxBankOrderQueryResult doRequestForObject() throws Exception {
        String reqStr = doRequest();
        JxBankOrderQueryResult obj = AsXmlMapMapper.fromXml("JxBankOrderQueryResult",reqStr, JxBankOrderQueryResult.class);
        return obj;
    }
}
