package com.junfeng.platform.payment.common.cache;

import com.junfeng.platform.payment.controller.pay.vo.RefundOrderQueryResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <pre>
 * 查询支付中心成功退款结果缓存
 * 缓存时间：30秒
 * </pre>
 * @projectName:payment-center
 * @author:李麒
 * @date:2018年9月10日 上午10:50:01
 * @version 1.0
 */
@Component
public class RefundQuerySuccessResultCache implements InitializingBean {

	@Autowired
	private CacheManager cacheManager;
	private Cache refundQuerySuccessResultCache;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cacheManager, "RefundQueryResultCache,cacheManager未初始化,cacheManager is null......");
		refundQuerySuccessResultCache = cacheManager.getCache(CacheEnum.REFUND_QUERY_SUCCESS_RESULT_KEY.getName());
	}
	/**
	 * 根据支付中心退款单号查询
	 * @return
	 */
	public RefundOrderQueryResult get(String refundOrderNo) {
		ValueWrapper valueWrapper = refundQuerySuccessResultCache.get(refundOrderNo);
		if(valueWrapper == null) {
			return null;
		}
		return (RefundOrderQueryResult) valueWrapper.get();
	}

	/**
	 *
	 * @param obj
	 */
	public void put(String refundOrderNo,RefundOrderQueryResult obj) {
	    refundQuerySuccessResultCache.put(refundOrderNo, obj);
	}
}
