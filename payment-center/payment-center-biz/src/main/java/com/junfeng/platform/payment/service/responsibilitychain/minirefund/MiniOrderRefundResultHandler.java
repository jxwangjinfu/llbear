package com.junfeng.platform.payment.service.responsibilitychain.minirefund;

import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.service.bank.BankTradeService;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.model.MiniOrderRefundHandleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiniOrderRefundResultHandler extends AbstractHandler<MiniOrderRefundHandleParam>{

    @Autowired
    private BankTradeService bankTradeService;

    @Override
    public MiniOrderRefundHandleParam handleRequest(MiniOrderRefundHandleParam requestParam) throws Exception {
        if (requestParam.getRefundSuccess()) {
            bankTradeService.refundOrderSuccessResult(requestParam.getPayRefundOrder(),
                    requestParam.getPayOrderRequestRecord());
        } else {
            bankTradeService.refundOrderFailResult(requestParam.getPayRefundOrder(),
                    requestParam.getPayOrderRequestRecord());
        }
        return requestParam;
    }

}
