package com.junfeng.platform.payment.common.cache;

import com.junfeng.platform.payment.controller.pay.vo.OrderQueryResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <pre>
 * 查询支付中心成功支付结果缓存
 * 缓存时间：30秒
 * </pre>
 * @projectName:payment-center
 * @author:chenyx
 * @date:2018年8月25日 上午11:07:40
 * @version 1.0
 */
@Component
public class PayQuerySuccessResultCache implements InitializingBean {

	@Autowired
	private CacheManager cacheManager;
	private Cache payQuerySuccessResultCache;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cacheManager, "PayQueryResultCache,cacheManager未初始化,cacheManager is null......");
		payQuerySuccessResultCache = cacheManager.getCache(CacheEnum.PAY_QUERY_SUCCESS_RESULT_KEY.getName());
	}
	/**
	 * 根据业务系统支付单号查询
	 * @return
	 */
	public OrderQueryResult get(String mchOrderNo) {
		ValueWrapper valueWrapper = payQuerySuccessResultCache.get(mchOrderNo);
		if(valueWrapper == null) {
			return null;
		}
		return (OrderQueryResult) valueWrapper.get();
	}

	/**
	 * 更新授权公众号信息
	 * @param obj
	 */
	public void put(String mchOrderNo,OrderQueryResult obj) {
	    payQuerySuccessResultCache.put(mchOrderNo, obj);
	}
}
