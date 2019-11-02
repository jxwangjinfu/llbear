package com.junfeng.wc.config.open;

import lombok.Data;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import redis.clients.jedis.JedisPool;

/**
 * 开发平台配置类
 *
 * @author daiysh
 * @date 2019-09-29 15:54
 **/
@Data
public class WxOpenInRedisConfigStorageMy extends WxOpenInRedisConfigStorage {
	public WxOpenInRedisConfigStorageMy(JedisPool jedisPool) {
		super(jedisPool);
	}
}
