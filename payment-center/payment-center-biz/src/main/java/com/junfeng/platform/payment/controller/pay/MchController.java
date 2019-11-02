package com.junfeng.platform.payment.controller.pay;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.junfeng.platform.payment.common.type.PaymentMchInfoState;
import com.junfeng.platform.payment.api.entity.PaymentMchInfo;
import com.junfeng.platform.payment.service.PaymentMchInfoService;
import com.pig4cloud.pig.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 中心商户Controller 给业务系统调用
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/pay/mch")
public class MchController {
	private final PaymentMchInfoService payMchInfoService;

	/**
	 * 申请开户
	 */
	@PostMapping(value = "/open")
	public R<Long> openMch(@RequestBody @Valid PaymentMchInfo mchInfo)
		throws Exception {
		log.info("openMch,vo={}", mchInfo.toString());

		PaymentMchInfo payMchInfo = payMchInfoService.getOne(Wrappers.<PaymentMchInfo>lambdaQuery().eq(PaymentMchInfo::getAppShopId, mchInfo.getAppShopId()));

		Assert.isNull(payMchInfo, "商户已申请开通！");

		mchInfo.setState(PaymentMchInfoState.AUDITED.getValue());

		payMchInfoService.save(mchInfo);

		return R.ok(mchInfo.getId());
	}

	/**
	 * 申请修改账户
	 */
	@PostMapping(value = "/update")
	public R updateMch(@RequestBody @Valid PaymentMchInfo info) {

		Assert.notNull(info.getId(), "支付中心商户号不能为空!");
		PaymentMchInfo mchInfo = payMchInfoService.getById(info.getId());

		Assert.notNull(mchInfo, "商户未开通支付");

		mchInfo.setState(PaymentMchInfoState.AUDITED.getValue());

		payMchInfoService.updateById(mchInfo);

		return R.ok();
	}

	/**
	 * 查询支付中心商户信息
	 */
	@GetMapping(value = "/get/{id}")
	public R<PaymentMchInfo> getMch(@PathVariable Integer id) {

		return R.ok(payMchInfoService.getById(id));

	}
}
