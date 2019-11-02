package com.junfeng.platform.tc.api.feign.fallback;

import com.junfeng.platform.tc.api.entity.Order;
import com.junfeng.platform.tc.api.feign.RemoteTradeOrderService;
import com.junfeng.platform.tc.api.vo.OrderRequest;
import com.junfeng.platform.tc.api.vo.PaymentNotify;
import com.pig4cloud.pig.common.core.util.R;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
@Accessors(chain = true)
public class RemoteTradeOrderServiceFallbackImpl implements RemoteTradeOrderService {
    @Setter
    private Throwable cause;

    @Override
    public R<Order> remoteGetOrderInfo(String orderNo, String from) {
        log.error("feign 创建支付单 失败", cause);
        return R.failed("feign 创建支付单 失败");
    }

    @Override
    public R<Order> remoteNotifyPaySuccess(PaymentNotify notify, String from) {
        log.error("feign 支付成功通知 失败", cause);
        return R.failed("feign 支付成功通知 失败");
    }

    @Override
    public R<Order> remoteNotifyPayRefund(PaymentNotify paymentNotify, String from) {
        log.error("feign 支付退款通知 >> 通知交易中心失败！", cause);
        return R.failed("feign 支付退款通知 >> 通知交易中心失败！");
    }

    @Override
    public R<Order> remoteMemberOrderCreate(OrderRequest request, String from) {
        log.error("feign 会员订单 创建 失败", cause);
        return R.failed("feign 会员订单 创建失败");
    }

    @Override
    public R<String> remoteMemberOrderPrepay(String orderNo, String from) {
        log.error("feign 会员支付单 创建 失败", cause);
        return R.failed("feign 会员支付单 创建失败 " + cause.getMessage());
    }

    @Override
    public R<List<Order>> remoteMemberOrderList(String memberId, String state, String from) {
        log.error("feign 会员订单列表 失败", cause);
        return R.failed("feign 会员订单列表 获取失败");
    }

    @Override
    public R<Order> remoteOrderCancel(String memberId, String orderId, String from) {
        log.error("feign 会员取消订单失败", cause);
        return R.failed("feign 会员取消订单 失败");
    }
}
