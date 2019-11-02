package com.junfeng.platform.payment.service.responsibilitychain.minirefund;

import com.junfeng.platform.payment.common.RandomUtils;
import com.junfeng.platform.payment.common.pay.responsibilitychain.AbstractHandler;
import com.junfeng.platform.payment.common.type.OrderRefundStateEnum;
import com.junfeng.platform.payment.common.type.ResponsibilityChainHandlerStateEnum;
import com.junfeng.platform.payment.controller.pay.vo.type.RefundResultCodeEnum;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.PaymentRefundOrderService;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.model.MiniOrderRefundHandleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiniOrderRefundAddRecordHandler extends AbstractHandler<MiniOrderRefundHandleParam> {

	@Autowired
	private PaymentRefundOrderService payRefundOrderService;

	@Override
	public MiniOrderRefundHandleParam handleRequest(MiniOrderRefundHandleParam requestParam) throws Exception {
		// TODO Auto-generated method stub
		ResponsibilityChainHandlerStateEnum handleState = requestParam.getHandlerState();
		if (ResponsibilityChainHandlerStateEnum.EXCEPTION.equals(handleState)) {
			return requestParam;
		}

		if (handleState.equals(ResponsibilityChainHandlerStateEnum.SUCCESS)) {
			// 生成退款订单
			PaymentRefundOrder obj = new PaymentRefundOrder();
			obj.setPayOrderNo(requestParam.getPayOrderNo());
			obj.setAppShopId(requestParam.getAppShopId());
			obj.setMchOrderNo(requestParam.getMchOrderNo());
			obj.setPayAmount(requestParam.getPayAmount());
			obj.setRefundAmount(requestParam.getRefundAmount());
			obj.setPayChannelCode(requestParam.getPayChannelCode());
			obj.setPayMchId(requestParam.getPayMchId());
			obj.setPayChannelCode(requestParam.getPayChannelCode());
			obj.setPaymentModeCode(requestParam.getPaymentModeCode());
			obj.setRefundOrderNo(RandomUtils.getFixDateTimeAndLengthNumber(10));
			obj.setRemark(requestParam.getBody());
            obj.setNotifyUrl(requestParam.getNotifyUrl());
            obj.setState(OrderRefundStateEnum.CREATE.getValue());

			payRefundOrderService.save(obj);

			requestParam.setPayRefundOrder(obj);
			requestParam.setRefundOrderNo(obj.getRefundOrderNo());
			requestParam.getMiniOrderRefundResult().setRefundOrderNo(obj.getRefundOrderNo());
			requestParam.getMiniOrderRefundResult().setResultState(RefundResultCodeEnum.CREATE.getValue());
			requestParam.getMiniOrderRefundResult().setResultMessage(RefundResultCodeEnum.CREATE.getDescription());
		}

		return requestParam;
	}

}
