package com.junfeng.platform.payment.bank.constructionbank.request;

import java.util.LinkedHashMap;
import java.util.Map;

import com.junfeng.platform.payment.bank.constructionbank.common.ConstructionBankConstant;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankRefundParam;
import com.junfeng.platform.payment.bank.constructionbank.model.ConstructionBankRefundResult;
import com.junfeng.platform.payment.bank.constructionbank.untils.SocketUtil;
import com.junfeng.platform.payment.bank.constructionbank.untils.TanslateUtil;
import com.junfeng.platform.payment.common.AsXmlMapMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 建设银行b扫c退款请求
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2018年11月28日 上午10:44:48
 * @version 1.0
 */
public class ConstructionBankRefundRequest {

    private final static Logger LOGGER = LogManager.getLogger();

    ConstructionBankRefundParam param;

    public ConstructionBankRefundRequest(ConstructionBankRefundParam param)
    {
        this.param = param;
    }

    /**
     * 设置参数
     *
     * @return
     * @author:xiongh
     * @createTime:2018年11月28日 上午11:23:58
     */
    private String getParam() {
        Map<String, String> payParams = new LinkedHashMap<String, String>();
        payParams.put("REQUEST_SN", param.getRefundNo());
        payParams.put("CUST_ID", ConstructionBankConstant.BIG_MCH_ID);
        payParams.put("USER_ID", ConstructionBankConstant.BIG_MCH_OPERA);
        payParams.put("PASSWORD", ConstructionBankConstant.MCH_OPERA_TRADE_PASSWORD);
        payParams.put("TX_CODE", "5W1024");
        payParams.put("LANGUAGE", "CN");
        Map<String, String> txInfo = new LinkedHashMap<String, String>();
        txInfo.put("MONEY", param.getAmount().toString());
        txInfo.put("ORDER", param.getOutTradeNo());
        txInfo.put("Mrch_No", param.getMrchNo());
        payParams.put("TX_INFO", TanslateUtil.toSimpleXml(txInfo));
        String requestParam = TanslateUtil.toXml(payParams);
        return requestParam;
    }

    public ConstructionBankRefundResult doRequestForObject() {
        SocketUtil client = new SocketUtil(ConstructionBankConstant.SOCKET_IP, Integer.valueOf(ConstructionBankConstant.SOCKET_PORT));
        client.send(getParam());
        String result = client.recieve();
        ConstructionBankRefundResult requestResult = AsXmlMapMapper.fromXml("ConstructionBankRefundResult", result,
                ConstructionBankRefundResult.class);
        client.close();
        return requestResult;
    }

    public static void main(String[] args) {
        ConstructionBankRefundParam param = new ConstructionBankRefundParam();
        param.setAmount(0.01);
        param.setOutTradeNo("123241221277777");
        param.setRefundNo("12548");
        param.setMrchNo("105910200228382");

        ConstructionBankRefundRequest request = new ConstructionBankRefundRequest(param);
        ConstructionBankRefundResult result = request.doRequestForObject();
        System.out.println(result.toString());
    }

}
