package com.junfeng.platform.payment.config.cache;

import com.google.common.collect.Maps;
import com.junfeng.platform.payment.common.cache.CacheEnum;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Spring Cache配置
 *
 * @author chenyx
 */
@Component
public class CacheManagerConfig {

	/**
	 * redisson操作类
	 */
	@Autowired
	private RedissonClient redisson;

	/**
	 * Spring Cache配置
	 *
	 * @return
	 */
	@Bean
	public CacheManager cacheManager() {

//		redisson.getConfig().toYAML()
		Map<String, CacheConfig> cacheConfigMap = Maps.newHashMap();
		Collection<String> caches = new HashSet<String>();
		for (CacheEnum cache : CacheEnum.values()) {
			caches.add(cache.getName());
			cacheConfigMap.put(cache.getName(),
				new CacheConfig(cache.getTimeout() * 1000, cache.getTimeout() * 1000));
		}
		RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redisson);
		// 设置redisson codec为json
		cacheManager.setCodec(new JsonJacksonCodec());
		// redisson中过期时间单位 ： 毫秒
		cacheManager.setConfig(cacheConfigMap);
		cacheManager.setCacheNames(caches);
		return cacheManager;
	}


}
