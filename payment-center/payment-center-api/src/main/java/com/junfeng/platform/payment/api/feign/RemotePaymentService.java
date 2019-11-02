package com.junfeng.platform.payment.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.junfeng.platform.payment.api.ApiConstant;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.feign.factory.RemotePaymentServiceFallbackFactory;
import com.junfeng.platform.payment.api.vo.PaymentPayVo;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;

/**
 * 描述
 *
 * @author wangk
 * @version 1.0
 * @createDate 2019-10-09 17:08
 * @projectName pig
 */
@FeignClient(contextId = "remotePaymentService", value = ServiceNameConstants.PAYMENT_CENTER_SERVICE, fallbackFactory = RemotePaymentServiceFallbackFactory.class)
public interface RemotePaymentService {

	@GetMapping(ApiConstant.API_REMOTE_PAYMENT_CREATE + "/{mchOrderNo}")
	R<PaymentOrderRequestRecord> remotePaymentCreate(@PathVariable("mchOrderNo") String mchOrderNo, @RequestHeader(SecurityConstants.FROM) String from);


	@PostMapping(ApiConstant.API_REMOTE_PAYMENT_REFUND)
	R<PaymentOrderRequestRecord> remotePaymentRefund(@RequestBody PaymentPayVo payment, @RequestHeader(SecurityConstants.FROM) String from);
}
