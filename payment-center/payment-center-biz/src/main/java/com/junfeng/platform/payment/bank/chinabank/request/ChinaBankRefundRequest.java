package com.junfeng.platform.payment.bank.chinabank.request;

import com.alibaba.fastjson.JSON;
import com.junfeng.platform.payment.bank.chinabank.common.ChinaBankTranTypeEnum;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankCallbackResult;
import com.junfeng.platform.payment.bank.chinabank.model.ChinaBankRefundParam;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 中国银行退款请求
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年4月4日 下午6:32:23
 * @version 1.0
 */
public class ChinaBankRefundRequest extends BaseChinaBankRequest {

    private ChinaBankRefundParam param;

    public ChinaBankRefundRequest(ChinaBankRefundParam param)
    {
        this.param = param;
        this.mchId = param.getMchId();
        this.outTradeNo = param.getOutTradeNo();
        this.posNo = param.getPosNo();
        this.key = param.getKey();
        this.tranType = ChinaBankTranTypeEnum.REFUND.getValue();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> map = new HashMap<>();
        map.put("orgSysTrace", param.getOrgSysTrace());
        map.put("VFTradeNo", param.getVFTradeNo());
        map.put("orgTxnTime", param.getOrgTxnTime());
        map.put("txnAmt",String.format("%012d", param.getTxnAmt()));
        return map;
    }

    public ChinaBankCallbackResult doPostForObject() throws Exception {
        String requestResult = doRequest();
        ChinaBankCallbackResult result = JSON.parseObject(requestResult, ChinaBankCallbackResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        ChinaBankRefundParam param = new ChinaBankRefundParam();
        param.setMchId("104110070110225");
        param.setPosNo("11000221");
        param.setKey("1AC$@!BGT98aw#C");
        param.setOutTradeNo("alipay1422444552");
        param.setOrgSysTrace("1100022120190506142");
        param.setOrgTxnTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        param.setVFTradeNo("alipay142244111");
        param.setTxnAmt(2L);

        ChinaBankRefundRequest request = new ChinaBankRefundRequest(param);
        ChinaBankCallbackResult result = request.doPostForObject();
        System.out.println(result.toString());
    }

}
