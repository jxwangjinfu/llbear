package com.junfeng.platform.payment.bank.jxbank.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.jxbank.model.JxBankRefundOrderParam;
import com.junfeng.platform.payment.bank.jxbank.model.JxBankRefundOrderResult;
import com.junfeng.platform.payment.common.AsXmlMapMapper;

/**
 * 江西银行退款请求
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月21日 下午4:55:22
 * @version 1.0
 */
public class JxBankRefundRequest extends JxBankBaseRequest {

    private JxBankRefundOrderParam param;

    public JxBankRefundRequest(JxBankRefundOrderParam param)
    {
        this.uri = "refundorder";
        this.key = param.getKey();
        this.mchId = param.getMchId();
        this.param = param;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("out_trade_no", param.getPayOrderNo());
        map.put("out_refund_no", param.getRefundOrderNo());
        map.put("refund_fee", param.getRefundFee().toString());
        map.put("total_fee", param.getRefundFee().toString());
        return map;
    }

    public JxBankRefundOrderResult doRequestForObject() throws IOException, URISyntaxException {
        String requestResult = doRequest();
        JxBankRefundOrderResult obj = AsXmlMapMapper.fromXml("JxBankRefundOrderResult",requestResult, JxBankRefundOrderResult.class);
        return obj;
    }

}
