package com.junfeng.platform.payment.service.bank;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentOrderResponseRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.PaymentOrderResponseRecordService;
import com.junfeng.platform.payment.service.PaymentRefundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 调用银行接口服务类
 *
 * @projectName:payment-center
 * @author:xionghui
 * @date:2018年8月20日 上午11:02:20
 * @version 1.0
 */
@Service
public class BankTradeService {

    // private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PaymentOrderRequestRecordService payOrderRequestRecordService;
    @Autowired
    private PaymentOrderResponseRecordService payOrderResponseRecordService;
    @Autowired
    private PaymentRefundOrderService payRefundOrderService;

    /**
     * 条码支付结果入库
     *
     * @param payOrderRequestRecord
     * @param payOrderResponseRecord
     * @author:chenyx
     * @createTime:2018年8月22日 上午10:46:16
     */
    @Async
    @Transactional
    public void barcodePaySuccessResult(PaymentOrderRequestRecord payOrderRequestRecord,
										PaymentOrderResponseRecord payOrderResponseRecord) {

    	//根据payOrderNo判断是否存在记录
		int count = payOrderResponseRecordService.count
			(Wrappers.<PaymentOrderResponseRecord>lambdaQuery()
				.eq(PaymentOrderResponseRecord::getPayOrderNo, payOrderResponseRecord.getPayOrderNo())
			);
		if (count==0) {
            payOrderRequestRecordService.updateById(payOrderRequestRecord);
            payOrderResponseRecordService.save(payOrderResponseRecord);
        }
    }

    /**
     * 支付失败
     *
     * @param payOrderRequestRecord
     * @param payOrderResponseRecord
     * @author:chenyx
     * @createTime:2018年8月22日 上午10:54:27
     */
    @Transactional
    public void barcodePayFailResult(PaymentOrderRequestRecord payOrderRequestRecord,
            PaymentOrderResponseRecord payOrderResponseRecord) {
        payOrderRequestRecordService.updateById(payOrderRequestRecord);
    }

    /**
     * 关闭订单
     *
     * @param payOrderRequestRecord
     * @author:chenyx
     * @createTime:2018年8月22日 下午3:23:14
     */
    @Transactional
    public void barcodeCloseOrder(PaymentOrderRequestRecord payOrderRequestRecord) {

    }

    /**
     * 退款成功
     *
     * @param payRefundOrder
     * @param payOrderRequestRecord
     * @author:xionghui
     * @createTime:2018年9月7日 上午11:16:33
     */
    @Transactional
    public void refundOrderSuccessResult(PaymentRefundOrder payRefundOrder, PaymentOrderRequestRecord payOrderRequestRecord) {
        payRefundOrderService.updateById(payRefundOrder);
        payOrderRequestRecordService.updateById(payOrderRequestRecord);
    }

    /**
     * 退款失败
     *
     * @param payRefundOrder
     * @param payOrderRequestRecord
     * @author:xionghui
     * @createTime:2018年9月7日 上午11:18:43
     */
    @Transactional
    public void refundOrderFailResult(PaymentRefundOrder payRefundOrder, PaymentOrderRequestRecord payOrderRequestRecord) {

    }

}
