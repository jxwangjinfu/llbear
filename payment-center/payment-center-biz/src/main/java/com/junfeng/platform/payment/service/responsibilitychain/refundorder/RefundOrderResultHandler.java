package com.junfeng.platform.payment.service.responsibilitychain.refundorder;

import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.service.bank.BankTradeService;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.model.RefundOrderHandleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退款结果回写
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年9月7日 上午10:57:49
 * @version 1.0
 */
@Service
public class RefundOrderResultHandler extends AbstractHandler<RefundOrderHandleParam> {

    @Autowired
    private BankTradeService bankTradeService;

    @Override
    public RefundOrderHandleParam handleRequest(RefundOrderHandleParam requestParam) throws Exception {
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
