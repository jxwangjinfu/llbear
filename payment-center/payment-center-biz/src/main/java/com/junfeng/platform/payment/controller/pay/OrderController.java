package com.junfeng.platform.payment.controller.pay;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.junfeng.platform.payment.api.entity.PaymentMchChannelRelation;
import com.junfeng.platform.payment.api.entity.PaymentMchInfo;
import com.junfeng.platform.payment.api.entity.PaymentOrderRequestRecord;
import com.junfeng.platform.payment.api.entity.PaymentRefundOrder;
import com.junfeng.platform.payment.common.BeanMapper;
import com.junfeng.platform.payment.common.cache.PayQuerySuccessResultCache;
import com.junfeng.platform.payment.common.cache.RefundQuerySuccessResultCache;
import com.junfeng.platform.payment.common.httpresp.RequestResultCode;
import com.junfeng.platform.payment.common.lock.LockType;
import com.junfeng.platform.payment.common.pay.responsibilitychain.HandlerChain;
import com.junfeng.platform.payment.common.type.*;
import com.junfeng.platform.payment.common.validated.AddVo;
import com.junfeng.platform.payment.controller.pay.vo.*;
import com.junfeng.platform.payment.controller.pay.vo.mch.MchPayAccount;
import com.junfeng.platform.payment.controller.pay.vo.mch.MchPayType;
import com.junfeng.platform.payment.controller.pay.vo.type.OrderQueryResultCodeEnum;
import com.junfeng.platform.payment.controller.pay.vo.type.RefundQueryResultCodeEnum;
import com.junfeng.platform.payment.service.PaymentMchChannelRelationService;
import com.junfeng.platform.payment.service.PaymentMchInfoService;
import com.junfeng.platform.payment.service.PaymentOrderRequestRecordService;
import com.junfeng.platform.payment.service.PaymentRefundOrderService;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.*;
import com.junfeng.platform.payment.service.responsibilitychain.barcodepay.model.BarcodePayHandleParams;
import com.junfeng.platform.payment.service.responsibilitychain.miniqueryorder.MiniOrderQueryRequestHandler;
import com.junfeng.platform.payment.service.responsibilitychain.miniqueryorder.model.MiniOrderQueryHandleParam;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.MiniOrderRefundAddRecordHandler;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.MiniOrderRefundRequestHandler;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.MiniOrderRefundResultHandler;
import com.junfeng.platform.payment.service.responsibilitychain.minirefund.model.MiniOrderRefundHandleParam;
import com.junfeng.platform.payment.service.responsibilitychain.queryorder.QueryOrderBankQueryRequestHandler;
import com.junfeng.platform.payment.service.responsibilitychain.queryorder.QueryOrderNotifyHandler;
import com.junfeng.platform.payment.service.responsibilitychain.queryorder.model.QueryOrderHandleParam;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.RefundOrderRefundAddRecordHandler;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.RefundOrderRefundRequestHandler;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.RefundOrderResultHandler;
import com.junfeng.platform.payment.service.responsibilitychain.refundorder.model.RefundOrderHandleParam;
import com.junfeng.platform.payment.service.responsibilitychain.refundquery.RefundQueryBankQueryRequestHandler;
import com.junfeng.platform.payment.service.responsibilitychain.refundquery.model.RefundQueryOrderHandleParam;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.UnifiedOrderAddRecordHandler;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.UnifiedOrderUnionPayHandler;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model.MiniPayRequest;
import com.junfeng.platform.payment.service.responsibilitychain.unifiedorder.model.UnifiedOrderHandleParams;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/pay/order")
public class OrderController {

	private final PaymentMchInfoService paymentMchInfoService;

	private final PaymentMchChannelRelationService payMchChannelRelationService;

	private final PaymentOrderRequestRecordService payOrderRequestRecordService;

	private final PaymentRefundOrderService payRefundOrderService;

	/**
	 * 生成中心支付订单
	 */

	private BarcodePayAddRecordHandler barCodePayAddRecordHandler;
	/**
	 * 调用银行微信支付
	 */

	private BarcodePayWeChatPayHandler barCodePayWeChatPayHandler;
	/**
	 * 处理支付结果
	 */

	private BarcodePayResultHandler barcodePayResultHandler;
	/**
	 * 调用银行支付宝支付
	 */

	private BarcodePayAliPayHandler barCodePayAliPayHandler;

	/**
	 * 调用银联的云闪付支付
	 */

	private BarcodePayUnionPayHandler barcodePayUnionPayHandler;
	/**
	 * 银行支付成功后的通知处理
	 */

	private BarcodePayNotifyHandler barcodePayNotifyHandler;
	/**
	 * 查询银行订单处理
	 */

	private QueryOrderBankQueryRequestHandler queryOrderBankQueryRequestHandler;
	/**
	 * 查询银行支付成功后的通知处理
	 */

	private QueryOrderNotifyHandler queryOrderNotifyHandler;
	/**
	 * 支付成功结果缓存
	 */

	private PayQuerySuccessResultCache payQuerySuccessResultCache;
	/**
	 * 退款成功结果缓存
	 */

	private RefundQuerySuccessResultCache refundQuerySuccessResultCache;

	/**
	 * 添加退款记录责
	 */

	private RefundOrderRefundAddRecordHandler refundOrderRefundAddRecordHandler;

	/**
	 * 退款查询请求责任链
	 */

	private RefundQueryBankQueryRequestHandler refundQueryBankQueryRequestHandler;

	/**
	 * 调用银行退款请求
	 */

	private RefundOrderRefundRequestHandler refundOrderRefundRequestHandler;

	/**
	 * 银行退款成功后处理
	 */

	private RefundOrderResultHandler refundOrderResultHandler;

	/**
	 * 统一下单责任链
	 */

	private UnifiedOrderAddRecordHandler unifiedOrderAddRecordHandler;

	private UnifiedOrderUnionPayHandler unifiedOrderUnionPayHandler;

	private MiniOrderQueryRequestHandler miniOrderQueryRequestHandler;

	private MiniOrderRefundAddRecordHandler miniOrderRefundAddRecordHandler;

	private MiniOrderRefundRequestHandler miniOrderRefundRequestHandler;

	private MiniOrderRefundResultHandler miniOrderRefundResultHandler;

	private RedissonClient redissonClient;

	/**
	 * 统一下单接口
	 */
	@PostMapping(value = "/unifiedorder")
	public R<MiniPayRequest> unifiedorder(@RequestBody @Valid UnifiedOrderPayVo vo) {
		log.info(vo.toString());

		PaymentMchInfo mchinfo = paymentMchInfoService.getById(vo.getPayMchId());

		Assert.notNull(mchinfo, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		// 判断商户状态
		Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(mchinfo.getState()),
			RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());

		// 校验通过之后再查出已启用的支付通道
		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());
		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		// 小程序支付账号存在extendsJson字段中
		List<MchPayType> mchPayList = JSON.parseArray(payMchChannelRelation.getExtendJson(), MchPayType.class);
		Assert.notEmpty(mchPayList, RequestResultCode.PAY_MCH_MINI_ACCOUNT_NO_EXIST.getErrorMessage());

		MchPayAccount payAccount = new MchPayAccount();
		for (MchPayType mchPayType : mchPayList) {
			if (MchPayTypeEnum.MINI_PROGRAMME.getValue().equals(mchPayType.getType())) {
				payAccount = mchPayType.getData();
			}
		}

		Assert.notNull(payAccount, RequestResultCode.PAY_MCH_MINI_ACCOUNT_NO_EXIST.getErrorMessage());

		// 根据业务系统支付单号查询支付订单 判断业务系统支付订单号是否存在
		// 存在则返回错误 此业务系统支付单号已被使用
		Assert.isFalse(payOrderRequestRecordService.isMchOrderNo(vo.getMchOrderNo()),
			RequestResultCode.PAY_ORDER_PAY_MCH_ORDERNO_IS_USED.getErrorMessage());

		RLock lock = null;
		try {
			lock = redissonClient.getFairLock(LockType.UNIFIEDORDER.getValue() + vo.getMchOrderNo());

			lock.lock();

			// 通过责任链模式处理
			UnifiedOrderHandleParams chainParam = new UnifiedOrderHandleParams();
			// 支付通道编码
			chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
			// 银行商户账号
			chainParam.setPayChannelAccount(payAccount.getAccount());
			// 业务系统商铺ID
			chainParam.setAppShopId(mchinfo.getAppShopId());
			// 客户端IP
			chainParam.setSpbillCreateIp(vo.getClientIp());
			// 银行商户密钥
			chainParam.setPayChannelKey(payAccount.getPayKey());
			// 支付中心商户ID
			chainParam.setPayMchId(vo.getPayMchId());
			// 业务系统支付订单号
			chainParam.setMchOrderNo(vo.getMchOrderNo());
			// 支付方式
			chainParam.setPaymentModeCode(vo.getPaymentModeCode());
			// 支付金额
			chainParam.setAmount(vo.getAmount());
			// 商品描述
			chainParam.setBody(vo.getBody());
			// 回调通知URL
			chainParam.setNotifyUrl(vo.getNotifyUrl());
			// 用户标识
			chainParam.setOpenId(vo.getOpenId());
			// 小程序appId
			chainParam.setAppId(vo.getAppId());
			chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);// 获得所需参数，责任链设置为成功
			// 责任链顺序
			// 1、unifiedOrderAddRecordHandler生成中心支付订单
			// 2、调用银行下单接口
			HandlerChain<UnifiedOrderHandleParams> handlerChain = new HandlerChain<UnifiedOrderHandleParams>();
			handlerChain.addHandler(unifiedOrderAddRecordHandler).addHandler(unifiedOrderUnionPayHandler);
			handlerChain.doHandler(chainParam);

			// 异常处理
			Assert.isFalse(ResponsibilityChainHandlerStateEnum.EXCEPTION.equals(chainParam.getHandlerState()),
				RequestResultCode.PAY_ORDER_MINI_PAY_FAILED.getErrorMessage());

			// 设置返回结果
			return R.ok(chainParam.getMiniPayRequest());
		} finally {
			if (lock != null) {
				lock.unlock();
			}
		}
	}

	/**
	 * 小程序支付查询结果
	 *
	 * @param vo
	 * @param bindingResult
	 * @return
	 * @throws JsonProcessingException
	 * @author:xionghui
	 * @createTime:2018年10月29日 下午3:21:01
	 */
	@PostMapping(value = "/mini_query_result")
	public R<MiniOrderQueryResult> miniPayQuery(@RequestBody @Valid MiniOrderQueryResultVo vo) {
		log.info("vo={}", vo.toString());

		// 获得中心支付订单号
		PaymentOrderRequestRecord payOrderRequestRecord = payOrderRequestRecordService.getOne(
			Wrappers.<PaymentOrderRequestRecord>lambdaQuery()
				.eq(PaymentOrderRequestRecord::getAppShopId, vo.getPayMchId())
				.eq(PaymentOrderRequestRecord::getMchOrderNo, vo.getMchOrderNo()));
		if (payOrderRequestRecord == null || payOrderRequestRecord.getState() == null) {
			// 说明支付请求不成功，判断通道是否正常开启
			// 判断商户是否正常
			PaymentMchInfo mchInfo = paymentMchInfoService.getById(vo.getPayMchId());
			Assert.notNull(mchInfo, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

			Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(mchInfo.getState()),
				RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());

			// 判断支付通道配置是否正常
			PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
				.getEnableByPayMchId(vo.getPayMchId());

			Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

			return R.failed(RequestResultCode.PAY_MCH_QUERY_ORDER_NUM_NO_EXIST.getErrorMessage());
		}
		MiniOrderQueryResult miniOrderQueryResult = new MiniOrderQueryResult();
		if (OrderPayState.SUCCESS.getValue().equals(payOrderRequestRecord.getState())) {
			// 数据库中查询订单已经成功支付
			miniOrderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			miniOrderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
			if (OrderPayRefundState.REFUNDED.getValue().equals(payOrderRequestRecord.getRefundState())) {
				// 已退款，返回值设置为已退款
				miniOrderQueryResult.setResultState(OrderQueryResultCodeEnum.REFUND.getValue());
				miniOrderQueryResult.setResultMessage(OrderQueryResultCodeEnum.REFUND.getDescription());
			} else {
				// 未退款订单，取原始付款状态值
				miniOrderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
				miniOrderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
			}
			// payQuerySuccessResultCache.put(vo.getMchOrderNo(), orderQueryResult);
			log.info("io result={}", miniOrderQueryResult.toString());
			return R.ok(miniOrderQueryResult);
		}

		if (OrderPayState.CLOSE.getValue().equals(payOrderRequestRecord.getState())) {
			// 数据库中查询订单已经关闭
			miniOrderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			miniOrderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
			miniOrderQueryResult.setResultState(OrderQueryResultCodeEnum.CLOSE.getValue());
			miniOrderQueryResult.setResultMessage(OrderQueryResultCodeEnum.CLOSE.getDescription());
			return R.ok(miniOrderQueryResult);
		}
		// 本地未成功支付，后面需要调用银行接口查询
		Assert.notNull(paymentMchInfoService.getById(vo.getPayMchId()),
			RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		// 校验通过之后再查出已启用的支付通道
		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());
		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		// 小程序支付账号存在extendsJson字段中
		List<MchPayType> mchPayList = JSON.parseArray(payMchChannelRelation.getExtendJson(), MchPayType.class);
		if (CollectionUtil.isEmpty(mchPayList)) {
			return R.failed(RequestResultCode.PAY_MCH_MINI_ACCOUNT_NO_EXIST.getErrorMessage());
		}

		MchPayAccount payAccount = new MchPayAccount();
		for (MchPayType mchPayType : mchPayList) {
			if (MchPayTypeEnum.MINI_PROGRAMME.getValue().equals(mchPayType.getType())) {
				payAccount = mchPayType.getData();
			}
		}
		Assert.notNull(payAccount, RequestResultCode.PAY_MCH_MINI_ACCOUNT_NO_EXIST.getErrorMessage());

		// 1.设置责任链参数
		MiniOrderQueryHandleParam chainParam = new MiniOrderQueryHandleParam();
		chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
		chainParam.setPayChannelKey(payAccount.getPayKey());
		// 银行商户账号
		chainParam.setPayChannelAccount(payAccount.getAccount());
		chainParam.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
		chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);
		chainParam.setPayOrderRequestRecord(payOrderRequestRecord);
		chainParam.setPayMchId(vo.getPayMchId());
		chainParam.setMiniOrderQueryResult(miniOrderQueryResult);

		HandlerChain<MiniOrderQueryHandleParam> handlerChain = new HandlerChain<MiniOrderQueryHandleParam>();
		handlerChain.addHandler(miniOrderQueryRequestHandler);
		handlerChain.doHandler(chainParam);

		return R.ok(chainParam.getMiniOrderQueryResult());
		// ResultFactory.postSuccess(chainParam.getMiniOrderQueryResult());

	}

	/**
	 * 小程序退款接口
	 *
	 * @param vo
	 * @return
	 * @throws JsonProcessingException
	 * @author:xionghui
	 * @createTime:2018年10月29日 下午7:44:44
	 */
	@PostMapping(value = "/mini/refund")
	public Object miniRefund(@RequestBody @Validated MiniOrderRefundResultVo vo) {

		log.info("OrderRefundVo={}", vo.toString());

		// 判断商户状态
		PaymentMchInfo mchInfo = paymentMchInfoService.getById(vo.getPayMchId());
		Assert.notNull(mchInfo, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(mchInfo.getState()),
			RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());

		// 获得中心支付订单
		PaymentOrderRequestRecord payOrderRequestRecord = payOrderRequestRecordService.getOne(Wrappers
			.<PaymentOrderRequestRecord>lambdaQuery().eq(PaymentOrderRequestRecord::getPayMchId, vo.getPayMchId())
			.eq(PaymentOrderRequestRecord::getPayOrderNo, vo.getMchOrderNo()));

		// 所查询的支付订单不存在
		Assert.notNull(payOrderRequestRecord, RequestResultCode.PAY_MCH_QUERY_ORDER_NUM_NO_EXIST.getErrorMessage());

		// 状态未支付成功
		Assert.isTrue(OrderPayState.SUCCESS.getValue().equals(payOrderRequestRecord.getState()),
			RequestResultCode.PAY_MCH_REFUND_ORDER_NUM_NO_PAY.getErrorMessage());

		// 已退款
		Assert.isFalse(OrderPayRefundState.REFUNDED.getValue().equals(payOrderRequestRecord.getRefundState()),
			RequestResultCode.PAY_MCH_REFUND_ORDER_NUM_IS_REFUND.getErrorMessage());

		if (payOrderRequestRecord.getPaySuccessTime() == null
			|| payOrderRequestRecord.getPaySuccessTime().until(LocalDateTime.now(), ChronoUnit.DAYS) == 0) {
			// 只有当天支付的订单能够退款
			return R.failed(RequestResultCode.PAY_MCH_REFUND_ORDER_NUM_IS_OUT_OF_DATE.getErrorMessage());
		}

		// 校验通过之后再查出已启用的支付通道

		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());

		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		Assert.isTrue(payMchChannelRelation.getPayChannelCode().equals(payOrderRequestRecord.getPayChannelCode()),
			RequestResultCode.PAY_MCH_CHANNEL_RELATION_CHANGE.getErrorMessage());

		// 小程序支付账号存在extendsJson字段中
		List<MchPayType> mchPayList = JSON.parseArray(payMchChannelRelation.getExtendJson(), MchPayType.class);

		Assert.notEmpty(mchPayList, RequestResultCode.PAY_MCH_MINI_ACCOUNT_NO_EXIST.getErrorMessage());

		MchPayAccount payAccount = new MchPayAccount();
		for (MchPayType mchPayType : mchPayList) {
			if (MchPayTypeEnum.MINI_PROGRAMME.getValue().equals(mchPayType.getType())) {
				payAccount = mchPayType.getData();
			}
		}

		MiniOrderRefundResult miniOrderRefundResult = new MiniOrderRefundResult();

		RLock lock = null;
		try {
			lock = redissonClient.getFairLock(LockType.REFUND_ORDER.getValue() + vo.getMchOrderNo());
			lock.lock();
			// 1.设置责任链参数
			MiniOrderRefundHandleParam chainParam = new MiniOrderRefundHandleParam();
			chainParam.setPayMchId(vo.getPayMchId());
			chainParam.setMchOrderNo(vo.getMchOrderNo());
			chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
			chainParam.setPayChannelKey(payAccount.getPayKey());
			chainParam.setPayChannelAccount(payAccount.getAccount());
			chainParam.setPayAmount(payOrderRequestRecord.getAmount());
			chainParam.setRefundAmount(payOrderRequestRecord.getAmount());
			chainParam.setAppShopId(payOrderRequestRecord.getAppShopId());
			chainParam.setPaymentModeCode(payOrderRequestRecord.getPaymentModeCode());
			chainParam.setBody(payOrderRequestRecord.getBody());
			chainParam.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			chainParam.setMiniOrderRefundResult(miniOrderRefundResult);
			chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);
			chainParam.setPayOrderRequestRecord(payOrderRequestRecord);
			// 2.添加退款记录到支付中心
			// 3.请求银行退款接口
			// ４.更新退款记录
			HandlerChain<MiniOrderRefundHandleParam> handlerChain = new HandlerChain<MiniOrderRefundHandleParam>();
			handlerChain.addHandler(miniOrderRefundAddRecordHandler).addHandler(miniOrderRefundRequestHandler)
				.addHandler(miniOrderRefundResultHandler);
			handlerChain.doHandler(chainParam);

			return R.ok(chainParam.getMiniOrderRefundResult());

		} finally {
			if (lock != null) {
				lock.unlock();
			}
		}

	}

	/**
	 * 条码支付（被扫）接口
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author:李麒
	 * @createTime:2018年8月16日 下午7:29:15
	 */
	@PostMapping(value = "/barcode")
	public Object payBarcode(@RequestBody @Validated({AddVo.class}) BarcodePayVo vo) {

		log.info("vo={}", vo.toString());

		// 判断商户状态
		PaymentMchInfo obj = paymentMchInfoService.getById(vo.getPayMchId());
		Assert.notNull(obj, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(obj.getState()),
			RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());
		// 校验通过之后再查出已启用的支付通道

		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());

		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		// 支付账号有扩展字段存在extendsJson字段中
		MchPayAccount payAccount = new MchPayAccount();
		if (StringUtils.isNotBlank(payMchChannelRelation.getExtendJson())) {
			List<MchPayType> mchPayList = JSON.parseArray(payMchChannelRelation.getExtendJson(), MchPayType.class);

			if (CollectionUtils.isNotEmpty(mchPayList)) {
				for (MchPayType mchPayType : mchPayList) {
					if (MchPayTypeEnum.POS_PAY.getValue().equals(mchPayType.getType())) {
						payAccount = mchPayType.getData();
					}
				}
			}
		}

		// 临时禁用建行通道的龙支付和云闪付扫码
		// if (StringUtils.equals(payMchChannelRelation.getPayChannelCode(), PayChannelCode.CCB.getValue())) {
		// if (StringUtils.equals(PaymentModeCode.UNIONPAY_BARCODE.getValue(), vo.getPaymentModeCode())) {
		// OrderQueryResult orderQueryResult = new OrderQueryResult();
		// orderQueryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
		// orderQueryResult.setResultMessage(RequestResultCode.PAY_CHANNEL_UNSUPPORT_THIS_PAY_MODE
		// .getErrorMessage());
		// payQuerySuccessResultCache.put(vo.getMchOrderNo(), orderQueryResult);
		// return ResultFactory.preConditionFail(new RequestResult(
		// RequestResultCode.PAY_CHANNEL_UNSUPPORT_THIS_PAY_MODE));
		// }
		// }

		if (payOrderRequestRecordService.isMchOrderNo(vo.getMchOrderNo())) {
			// 根据业务系统支付单号查询支付订单 判断业务系统支付订单号是否存在
			// 存在则返回错误 此业务系统支付单号已被使用
			return R.failed(RequestResultCode.PAY_ORDER_PAY_MCH_ORDERNO_IS_USED.getErrorMessage());
		}

		RLock lock = null;
		try {
			lock = redissonClient.getFairLock(LockType.PAY_BARCODE.getValue() + vo.getPayBarcode());
			lock.lock();

			// 通过责任链模式处理
			BarcodePayHandleParams chainParam = new BarcodePayHandleParams();
			// 支付通道编码
			chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
			// 银行商户账号
			chainParam.setPayChannelAccount(payMchChannelRelation.getPayChannelAccount());
			// 业务系统商铺ID
			chainParam.setAppShopId(obj.getAppShopId());
			// 支付条码
			chainParam.setAuthCode(vo.getPayBarcode());
			// 客户端IP
			chainParam.setSpbillCreateIp(vo.getClientIp());
			// 银行商户密钥
			chainParam.setPayChannelKey(payMchChannelRelation.getPayChannelKey());
			// 支付中心商户ID
			chainParam.setPayMchId(vo.getPayMchId());
			// 业务系统支付订单号
			chainParam.setMchOrderNo(vo.getMchOrderNo());
			// 支付方式
			chainParam.setPaymentModeCode(vo.getPaymentModeCode());
			// 支付金额
			chainParam.setAmount(vo.getAmount());
			// 商品描述
			chainParam.setBody(vo.getBody());
			// 回调通知URL
			chainParam.setNotifyUrl(vo.getNotifyUrl());
			chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);// 获得所需参数，责任链设置为成功
			// 支付通道为建设银行，这个字段存的是柜台Id:posId;农业银行，这个字段存的是银行的私钥
			if (payAccount != null) {
				chainParam.setPayChannelExtendJson(payAccount.getAccount());
				// 支付通道为农业银行，这个字段存的是用户私钥
				chainParam.setMchPrivateKey(payAccount.getPayKey());
			}
			// 责任链顺序
			// 1、BarCodePayAddRecordHandler生成中心支付订单
			// 2、BarCodePaySetPayInfoHandler设置支付信息
			// 3、BarCodePayBankPayHandler调用银行支付
			HandlerChain<BarcodePayHandleParams> handlerChain = new HandlerChain<BarcodePayHandleParams>();
			handlerChain.addHandler(barCodePayAddRecordHandler).addHandler(barCodePayWeChatPayHandler)
				.addHandler(barCodePayAliPayHandler).addHandler(barcodePayUnionPayHandler)
				.addHandler(barcodePayResultHandler).addHandler(barcodePayNotifyHandler);
			handlerChain.doHandler(chainParam);

			if (ResponsibilityChainHandlerStateEnum.EXCEPTION.equals(chainParam.getHandlerState())) {
				// 异常处理
				if (chainParam.getErrorRequestResult() == null) {
					return R.failed(RequestResultCode.PAY_ORDER_PAY_BARCODE_FAILED.getErrorMessage());
				} else {
					return R.failed(chainParam.getErrorRequestResult());
				}
			}
			// 设置返回结果
			return R.ok();
		} finally {
			if (lock != null) {
				lock.unlock();
			}
		}
	}

	/**
	 * 查询支付结果接口
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author:李麒
	 * @createTime:2018年8月17日 下午2:09:19
	 */
	@PostMapping(value = "/query_result")
	public R<OrderQueryResult> payQueryOrder(@RequestBody @Validated({AddVo.class}) OrderQueryResultVo vo) {

		log.info("vo={}", vo.toString());
		// 先从缓存中查询
		OrderQueryResult orderQueryResult = payQuerySuccessResultCache.get(vo.getMchOrderNo());
		if (orderQueryResult != null) {
			log.info("cache result={}", orderQueryResult.toString());
			return R.ok(orderQueryResult);
		}
		// 获得中心支付订单号
		PaymentOrderRequestRecord payOrderRequestRecord = payOrderRequestRecordService.getOne(
			Wrappers.<PaymentOrderRequestRecord>lambdaQuery()
				.eq(PaymentOrderRequestRecord::getPayMchId, vo.getPayMchId())
				.eq(PaymentOrderRequestRecord::getMchOrderNo, vo.getMchOrderNo()));

		if (payOrderRequestRecord == null || payOrderRequestRecord.getState() == null) {
			// 说明支付请求不成功，判断通道是否正常开启
			// 判断商户是否正常
			PaymentMchInfo mchInfo = paymentMchInfoService.getById(vo.getPayMchId());

			Assert.notNull(mchInfo, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

			Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(mchInfo.getState()),
				RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());
			// 判断支付通道配置是否正常
			PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
				.getEnableByPayMchId(vo.getPayMchId());
			Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

			return R.failed(RequestResultCode.PAY_MCH_QUERY_ORDER_NUM_NO_EXIST.getErrorMessage());
		}
		orderQueryResult = new OrderQueryResult();
		if (OrderPayState.SUCCESS.getValue().equals(payOrderRequestRecord.getState())) {
			// 数据库中查询订单已经成功支付
			orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
			orderQueryResult.setPaySuccessTime(payOrderRequestRecord.getPaySuccessTime());
			if (OrderPayRefundState.REFUNDED.getValue().equals(payOrderRequestRecord.getRefundState())) {
				// 已退款，返回值设置为已退款
				orderQueryResult.setResultState(OrderQueryResultCodeEnum.REFUND.getValue());
				orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.REFUND.getDescription());
			} else {
				// 未退款订单，取原始付款状态值
				orderQueryResult.setResultState(OrderQueryResultCodeEnum.SUCCESS.getValue());
				orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.SUCCESS.getDescription());
			}
			payQuerySuccessResultCache.put(vo.getMchOrderNo(), orderQueryResult);
			log.info("io result={}", orderQueryResult.toString());
			return R.ok(orderQueryResult);
		}
		// 支付失败
		if (OrderPayState.ERROR.getValue().equals(payOrderRequestRecord.getState())) {
			orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
			orderQueryResult.setResultState(OrderQueryResultCodeEnum.UNPAY.getValue());
			orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.UNPAY.getDescription());
		}

		if (OrderPayState.CLOSE.getValue().equals(payOrderRequestRecord.getState())) {
			// 数据库中查询订单已经关闭
			orderQueryResult.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			orderQueryResult.setTradeOrderNo(payOrderRequestRecord.getTradeOrderNo());
			orderQueryResult.setResultState(OrderQueryResultCodeEnum.CLOSE.getValue());
			orderQueryResult.setResultMessage(OrderQueryResultCodeEnum.CLOSE.getDescription());
			return R.ok(orderQueryResult);
		}
		// 本地未成功支付，后面需要调用银行接口查询
		PaymentMchInfo obj = paymentMchInfoService.getById(vo.getPayMchId());
		Assert.notNull(obj, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		// 校验通过之后再查出已启用的支付通道
		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());

		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		// 支付账号有扩展字段存在extendsJson字段中
		MchPayAccount payAccount = new MchPayAccount();
		if (StringUtils.isNotBlank(payMchChannelRelation.getExtendJson())) {
			List<MchPayType> mchPayList = JSON.parseArray(payMchChannelRelation.getExtendJson(), MchPayType.class);

			if (CollectionUtils.isNotEmpty(mchPayList)) {
				for (MchPayType mchPayType : mchPayList) {
					if (MchPayTypeEnum.POS_PAY.getValue().equals(mchPayType.getType())) {
						payAccount = mchPayType.getData();
					}
				}
			}
		}

		// 1.设置责任链参数
		QueryOrderHandleParam chainParam = new QueryOrderHandleParam();
		chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
		chainParam.setPayChannelKey(payMchChannelRelation.getPayChannelKey());
		chainParam.setPayChannelAccount(payMchChannelRelation.getPayChannelAccount());
		// 支付通道为建设银行，这个字段存的是柜台Id:posId;支付通道为邮政银行,该字段存的是银行分配的appid,农业银行商户私钥
		if (payAccount != null) {
			chainParam.setPayChannelExtendJson(payAccount.getAccount());
			chainParam.setMchPrivateKey(payAccount.getPayKey());
		}
		chainParam.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
		chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);
		chainParam.setPayOrderRequestRecord(payOrderRequestRecord);
		chainParam.setPayMchId(vo.getPayMchId());
		chainParam.setOrderQueryResult(orderQueryResult);
		chainParam.setPaymentModeCode(payOrderRequestRecord.getPaymentModeCode());
		// 2、添加责任链
		HandlerChain<QueryOrderHandleParam> handlerChain = new HandlerChain<QueryOrderHandleParam>();
		handlerChain.addHandler(queryOrderBankQueryRequestHandler).addHandler(queryOrderNotifyHandler);
		handlerChain.doHandler(chainParam);

		// 2接收返回参数，封装成我们的结果格式返回
		return R.ok(orderQueryResult);
	}

	/**
	 * 订单退款
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author:李麒
	 * @createTime:2018年9月7日 上午9:51:20
	 */
	@PostMapping(value = "/refund")
	public R<RefundOrderResult> refundOrder(@RequestBody @Validated({AddVo.class}) OrderRefundVo vo)
		throws Exception {
		log.info("OrderRefundVo={}", vo.toString());

		// 判断商户状态
		PaymentMchInfo obj = paymentMchInfoService.getById(vo.getPayMchId());
		Assert.notNull(obj, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(obj.getState()), RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());

		// 获得中心支付订单
		PaymentOrderRequestRecord payOrderRequestRecord = payOrderRequestRecordService.getOne(
			Wrappers.<PaymentOrderRequestRecord>lambdaQuery()
				.eq(PaymentOrderRequestRecord::getPayMchId, vo.getPayMchId())
				.eq(PaymentOrderRequestRecord::getMchOrderNo, vo.getMchOrderNo())
		);

		// 所查询的支付订单不存在
		Assert.notNull(payOrderRequestRecord, RequestResultCode.PAY_MCH_QUERY_ORDER_NUM_NO_EXIST.getErrorMessage());

		// 状态未支付成功
		Assert.isTrue(OrderPayState.SUCCESS.getValue().equals(payOrderRequestRecord.getState()), RequestResultCode.PAY_MCH_REFUND_ORDER_NUM_NO_PAY.getErrorMessage());

		// 已退款
		Assert.isFalse(OrderPayRefundState.REFUNDED.getValue().equals(payOrderRequestRecord.getRefundState()), RequestResultCode.PAY_MCH_REFUND_ORDER_NUM_IS_REFUND.getErrorMessage());


		// 只有当天支付的订单能够退款
		if (payOrderRequestRecord.getCreateTime() == null || payOrderRequestRecord.getCreateTime().until(LocalDateTime.now(), ChronoUnit.DAYS) == 0) {
			return R.failed(RequestResultCode.PAY_MCH_REFUND_ORDER_NUM_IS_OUT_OF_DATE.getErrorMessage());
		}

		// 校验通过之后再查出已启用的支付通道

		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());

		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		// 商户当前启用的支付通道不是付款时使用的支付通道
		Assert.isTrue(payMchChannelRelation.getPayChannelCode().equals(payOrderRequestRecord.getPayChannelCode()), RequestResultCode.PAY_MCH_CHANNEL_RELATION_CHANGE.getErrorMessage());
//		if (!payMchChannelRelation.getPayChannelCode().equals(payOrderRequestRecord.getPayChannelCode())) {
//			return ResultFactory.preConditionFail(new RequestResult(RequestResultCode.PAY_MCH_CHANNEL_RELATION_CHANGE));
//		}

		// 支付账号有扩展字段存在extendsJson字段中
		MchPayAccount payAccount = new MchPayAccount();
		if (StringUtils.isNotBlank(payMchChannelRelation.getExtendJson())) {
			List<MchPayType> mchPayList = JSON.parseArray(payMchChannelRelation.getExtendJson(), MchPayType.class);

			if (CollectionUtils.isNotEmpty(mchPayList)) {
				for (MchPayType mchPayType : mchPayList) {
					if (MchPayTypeEnum.POS_PAY.getValue().equals(mchPayType.getType())) {
						payAccount = BeanMapper.map(mchPayType.getData(), MchPayAccount.class);
					}
				}
			}
		}

		RefundOrderResult refundOrderResult = new RefundOrderResult();

		RLock lock = null;
		try {
			lock = redissonClient.getFairLock(LockType.REFUND_ORDER.getValue() + vo.getMchOrderNo());
			lock.lock();
			// 1.设置责任链参数
			RefundOrderHandleParam chainParam = new RefundOrderHandleParam();
			chainParam.setPayMchId(vo.getPayMchId());
			chainParam.setMchOrderNo(vo.getMchOrderNo());
			chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
			chainParam.setPayChannelKey(payMchChannelRelation.getPayChannelKey());
			chainParam.setPayChannelAccount(payMchChannelRelation.getPayChannelAccount());
			chainParam.setClientIp(vo.getClientIp());
			chainParam.setPayAmount(payOrderRequestRecord.getAmount());
			chainParam.setRefundAmount(payOrderRequestRecord.getAmount());
			chainParam.setAppShopId(payOrderRequestRecord.getAppShopId());
			chainParam.setPaymentModeCode(payOrderRequestRecord.getPaymentModeCode());
			chainParam.setBody(payOrderRequestRecord.getBody());
			chainParam.setPayOrderNo(payOrderRequestRecord.getPayOrderNo());
			chainParam.setRefundOrderResult(refundOrderResult);
			chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);
			chainParam.setPayOrderRequestRecord(payOrderRequestRecord);
			// 支付通道为建设银行，这个字段存的是柜台Id:posId;支付通道为邮政银行,该字段存的是银行分配的appid
			if (payAccount != null) {
				chainParam.setPayChannelExtendJson(payAccount.getAccount());
				chainParam.setMchPrivateKey(payAccount.getPayKey());
			}
			// 2、添加责任链
			HandlerChain<RefundOrderHandleParam> handlerChain = new HandlerChain<RefundOrderHandleParam>();
			handlerChain.addHandler(refundOrderRefundAddRecordHandler).addHandler(refundOrderRefundRequestHandler)
				.addHandler(refundOrderResultHandler);

			handlerChain.doHandler(chainParam);

			return R.ok(refundOrderResult);
		} finally {
			if (lock != null) {
				lock.unlock();
			}
		}
	}

	/**
	 * 查询退款结果接口
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author:李麒
	 * @createTime:2018年9月7日 下午5:04:19
	 */
	@PostMapping(value = "/query_refund_result")
	public R<RefundOrderQueryResult> queryRefundOrderResult(@RequestBody @Validated({AddVo.class}) RefundOrderQueryResultVo vo) {
		log.info("vo={}", vo.toString());

		// 先从缓存中查询
		RefundOrderQueryResult refundOrderQueryResult = refundQuerySuccessResultCache.get(vo.getRefundOrderNo());
		if (refundOrderQueryResult != null) {
			log.info("cache result={}", refundOrderQueryResult.toString());
			return R.ok(refundOrderQueryResult);
		}

		// 获得中心支付订单号
		PaymentOrderRequestRecord payOrderRequestRecord = payOrderRequestRecordService.getOne(
			Wrappers.<PaymentOrderRequestRecord>lambdaQuery()
				.eq(PaymentOrderRequestRecord::getPayMchId, vo.getMchOrderNo())
		);
		if (payOrderRequestRecord == null || payOrderRequestRecord.getState() == null) {
			// 所查询的支付信息不存在
			return R.failed(RequestResultCode.PAY_MCH_QUERY_ORDER_NUM_NO_EXIST.getErrorMessage());
		}
		// 获得中心退款订单号
		PaymentRefundOrder refundOrder = payRefundOrderService.getOne(
			Wrappers.<PaymentRefundOrder>lambdaQuery()
				.eq(PaymentRefundOrder::getRefundOrderNo, vo.getRefundOrderNo())
				.orderByAsc(PaymentRefundOrder::getId)
		);
		if (refundOrder == null || refundOrder.getState() == null) {
			// 说明退款请求不成功，判断通道是否正常开启
			// 判断商户是否正常
			PaymentMchInfo mchInfo = paymentMchInfoService.getById(vo.getPayMchId());

			Assert.notNull(mchInfo, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

			Assert.isTrue(PayMchInfoState.ENABLE.getValue().equals(mchInfo.getState()), RequestResultCode.PAY_MCH_NO_WORK.getErrorMessage());

			// 判断支付通道配置是否正常
			PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
				.getEnableByPayMchId(vo.getPayMchId());

			Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

			return R.failed(RequestResultCode.PAY_MCH_QUERY_REFUND_ORDER_NUM_NO_EXIST.getErrorMessage());
		}
		refundOrderQueryResult = new RefundOrderQueryResult();
		if (RefundQueryResultCodeEnum.SUCCESS.getValue().equals(refundOrder.getState())) {
			// 数据库中查询订单已经成功退款
			refundOrderQueryResult.setRefundOrderNo(refundOrder.getRefundOrderNo());
			refundOrderQueryResult.setTradeOrderNo(refundOrder.getPayOrderNo());
			refundOrderQueryResult.setResultState(RefundQueryResultCodeEnum.SUCCESS.getValue());
			refundOrderQueryResult.setResultMessage(RefundQueryResultCodeEnum.SUCCESS.getDescription());
			refundQuerySuccessResultCache.put(vo.getRefundOrderNo(), refundOrderQueryResult);
			log.info("io result={}", refundOrderQueryResult.toString());
			return R.ok(refundOrderQueryResult);
		}

		if (RefundQueryResultCodeEnum.FAIL.getValue().equals(refundOrder.getState())) {
			// 数据库中查询订单已经退款失败
			refundOrderQueryResult.setRefundOrderNo(refundOrder.getRefundOrderNo());
			refundOrderQueryResult.setTradeOrderNo(refundOrder.getPayOrderNo());
			refundOrderQueryResult.setResultState(RefundQueryResultCodeEnum.FAIL.getValue());
			refundOrderQueryResult.setResultMessage(RefundQueryResultCodeEnum.FAIL.getDescription());
			return R.ok(refundOrderQueryResult);
		}
		// 本地未退款成功，后面需要调用银行接口查询
		PaymentMchInfo obj = paymentMchInfoService.getById(vo.getPayMchId());

		Assert.notNull(obj, RequestResultCode.PAY_MCH_NO_EXIST.getErrorMessage());

		// 校验通过之后再查出已启用的支付通道
		PaymentMchChannelRelation payMchChannelRelation = payMchChannelRelationService
			.getEnableByPayMchId(vo.getPayMchId());
		Assert.notNull(payMchChannelRelation, RequestResultCode.PAY_MCH_CHANNEL_RELATION_DISABLE.getErrorMessage());

		// 商户当前启用的支付通道不是付款时使用的支付通道
		Assert.isFalse(payMchChannelRelation.getPayChannelCode().equals(refundOrder.getPayChannelCode()), RequestResultCode.PAY_MCH_CHANNEL_RELATION_CHANGE.getErrorMessage());

		// 1.设置责任链参数
		RefundQueryOrderHandleParam chainParam = new RefundQueryOrderHandleParam();
		chainParam.setPayChannelCode(payMchChannelRelation.getPayChannelCode());
		chainParam.setPayChannelKey(payMchChannelRelation.getPayChannelKey());
		chainParam.setPayChannelAccount(payMchChannelRelation.getPayChannelAccount());
		chainParam.setPayOrderNo(refundOrder.getPayOrderNo());
		chainParam.setRefundOrderNo(refundOrder.getRefundOrderNo());
		chainParam.setHandlerState(ResponsibilityChainHandlerStateEnum.SUCCESS);
		chainParam.setPayOrderRequestRecord(payOrderRequestRecord);
		chainParam.setPayRefundOrder(refundOrder);
		chainParam.setPayMchId(vo.getPayMchId());
		chainParam.setRefundOrderQueryResult(refundOrderQueryResult);
		// 2、添加责任链
		HandlerChain<RefundQueryOrderHandleParam> handlerChain = new HandlerChain<RefundQueryOrderHandleParam>();
		handlerChain.addHandler(refundQueryBankQueryRequestHandler);
		handlerChain.doHandler(chainParam);

		// 2接收返回参数，封装成我们的结果格式返回
		return R.ok(refundOrderQueryResult);
	}
}
