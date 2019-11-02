package com.junfeng.platform.payment.controller.pay;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.api.ApiConstant;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.api.vo.PaymentPayVo;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.PaymentOrderResponseRecordService;
import com.junfeng.platform.payment.service.UserPaymentService;
import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-09 17:02
 * @projectName pig
 */

@RestController
@Slf4j
@AllArgsConstructor
public class UserPaymentController {

    private final PaymentOrderRequestRecordService paymentOrderRequestRecordService;
    private final PaymentOrderResponseRecordService paymentOrderResponseRecordService;

    private final RemoteTradeOrderService remoteTradeOrderService;

	private final UserPaymentService userPaymentService;


	/**
     * 创建支付单
     *
     * @return
     * @Author 2fx0one
     * @Description
     * @Date 2019-10-09 23:20
     * @Param nll
     **/
    @Inner
    @GetMapping(ApiConstant.API_REMOTE_PAYMENT_CREATE + "/{mchOrderNo}")
    public R<PaymentOrderRequestRecord> create(@PathVariable("mchOrderNo") String mchOrderNo) {

        R<Order> r = remoteTradeOrderService.remoteGetOrderInfo(mchOrderNo, SecurityConstants.FROM_IN);
        Assert.isTrue(r.getCode() == CommonConstants.SUCCESS, "商品订单不存在！");
        Order order = r.getData();
        Assert.notNull(order, "商品订单不存在！");

        Assert.isTrue("1".equals(order.getState()), "订单不是待支付状态。无法发起支付！");

        double fee = order.getTotalMoney() + order.getDeliverMoney();

        // TODO 构造假数据
        PaymentOrderRequestRecord requestRecord = new PaymentOrderRequestRecord().setMchOrderNo(order.getOrderNo())
                .setPayMchId(order.getSellerId()).setAmount((long)fee).setSubject("店家ID+ " + order.getSellerId())
                .setBody("店家付款码" + 4567).setNotifyUrl("http://www.test.notify");

        PaymentOrderRequestRecord paymentOrder = paymentOrderRequestRecordService.createPaymentOrder(requestRecord);
        return R.ok(paymentOrder);
    }

    /**
     * 回调通知
     *
     * @return a
     * @Author 2fx0one
     * @Description
     * @Date 2019-10-09 23:21
     * @Param nul
     **/
    @ApiOperation(value = "回调支付成功通知，本来是三方支付调用的通知，这里可以手动调用测试用!!")
    @PostMapping(ApiConstant.API_PAYMENT_NOTIFY)
    public R notify(@RequestBody PaymentPayVo payment) {

        PaymentOrderRequestRecord record = paymentOrderRequestRecordService
                .getOne(Wrappers.<PaymentOrderRequestRecord> lambdaQuery().eq(PaymentOrderRequestRecord::getPayOrderNo,
                        payment.getPayOrderNo()));

        Assert.notNull(record, "不存在该支付订单！");

        // TODO 检查订单状态 ,0-订单生成
        Assert.isTrue(record.getState().equals(0), "该订单已经完成通知！");

        PaymentOrderResponseRecord paymentOrderResponseRecord = new PaymentOrderResponseRecord().setPayOrderNo(payment.getPayOrderNo());
        paymentOrderResponseRecord.setPayOrderNo(record.getPayOrderNo()).setTradeOrderNo("tradeNo1234")
			.setAmount(9900L)
                .setPaymentModeCode(record.getPaymentModeCode());

        paymentOrderResponseRecordService.notifyPaySuccess(record, paymentOrderResponseRecord);
        return R.ok("通知成功");
    }


//    @ApiOperation(value = "支付退款成功通知")
    @Inner
    @PostMapping(ApiConstant.API_REMOTE_PAYMENT_REFUND)
    public R<PaymentOrderRequestRecord> paymentRefund(@RequestBody PaymentPayVo payment) {

		PaymentOrderRequestRecord record = paymentOrderRequestRecordService.getOne(
			Wrappers.<PaymentOrderRequestRecord>lambdaQuery()
			.eq(PaymentOrderRequestRecord::getPayOrderNo, payment.getPayOrderNo())
		);

		// TODO 检查订单状态 ,1-支付成功
		Assert.isTrue(record.getState().equals(1), "该订单必须完成支付才能退款！");

		R<Order> r = userPaymentService.paymentRefund(record);

		return R.ok(record);
    }

}
