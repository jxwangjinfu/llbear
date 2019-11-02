package com.junfeng.platform.payment.api.feign.fallback;

import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.feign.RemotePaymentService;
import com.junfeng.platform.payment.api.vo.PaymentPayVo;
import com.pig4cloud.pig.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemotePaymentServiceFallbackImpl implements RemotePaymentService {
    @Setter
    private Throwable cause;

	@Override
	public R<PaymentOrderRequestRecord> remotePaymentCreate(String mchOrderNo, String from) {
        log.error("feign 创建支付单失败", cause);
		return R.failed("feign 创建支付单失败 " + cause.getMessage());
	}

	@Override
    public R<PaymentOrderRequestRecord> remotePaymentRefund(PaymentPayVo payment, String from) {
        log.error("feign 支付单退款失败", cause);
		return R.failed("通知支付中心退款失败！ 支付单退款失败! "+ cause.getMessage());
    }

}
